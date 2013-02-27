package Panic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePicker;

/**
 * The RightPanelController controls the right part of the !Panic ToDo-app
 * In this part you can add more details to your task.
 * A window will slide in and out when it's needed.
 * @author joseph
 *
 */
public class RightPanelController {
	
	
	private PanicController pc;
	
	private RightPanelView panel;
	
	private JLabel titleLabel;
	private JTextField title;
	
	private JLabel descriptionLabel;
	private JTextArea description;
	
	private JLabel priorityLabel;
	private JList priority;
	
	private JLabel dateLabel;
	private JDatePicker date;
	
	private JLabel categoriesLabel;
	private JComboBox categories;
	
	
	private String[] s;
	
	private Task currentTask;
	
	private JButton deleteButton;
	
	private boolean show;
	
	/**
	 * Constructor for RightPanelController
	 */
	public RightPanelController() {
		I18.getInstance().setLocale("swe");
		
		panel = new RightPanelView(this);
		
		titleLabel = new JLabel(I18.getInstance().properties.getString("title"));
		titleLabel.setForeground(new Color(0xFFFFFF));
		title = new JTextField();
		
		descriptionLabel = new JLabel(I18.getInstance().properties.getString("description"));
		descriptionLabel.setForeground(new Color(0xFFFFFF));
		description = new JTextArea();
		
		priorityLabel = new JLabel(I18.getInstance().properties.getString("priority"));
		priorityLabel.setForeground(new Color(0xFFFFFF));
		s = new String[3];
		s[0] = I18.getInstance().properties.getString("low");
		s[1] = I18.getInstance().properties.getString("medium");
		s[2] = I18.getInstance().properties.getString("high");
		priority = new JList(s);
		
		dateLabel = new JLabel(I18.getInstance().properties.getString("date"));
		dateLabel.setForeground(new Color(0xFFFFFF));
		JDateComponentFactory factory = new JDateComponentFactory();
		date = factory.createJDatePicker();
		
		categoriesLabel = new JLabel(I18.getInstance().properties.getString("categories"));
		categoriesLabel.setForeground(new Color(0xFFFFFF));
		
		//fixCategoryNameFormat( pc.getCategories());
		categories = new JComboBox(new String[]{"test1","test2"});
		
		
		DetailedViewListener dvl = new DetailedViewListener(this, title, description, priority);
		
		title.getDocument().addDocumentListener(dvl);
		description.getDocument().addDocumentListener(dvl);
		priority.addListSelectionListener(dvl);
		
		categories.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s = (String) categories.getSelectedItem();
				System.out.println("Category: " + s);
			}
			
		});
		
		date.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GregorianCalendar theDate = (GregorianCalendar) date.getModel().getValue();
				System.out.println(theDate.get(Calendar.YEAR) + theDate.get(Calendar.MONTH) + theDate.get(Calendar.DAY_OF_MONTH));
			}
			
		});
		
		deleteButton = new JButton(I18.getInstance().properties.getString("delete"));
		deleteButton.setForeground(Color.black);
		deleteButton.setBackground(Color.red);
		deleteButton.addActionListener(dvl);
		
		panel.gridAdd(0, 0, 5, titleLabel);
		panel.gridAdd(0, 1, 5, title);
		panel.gridAdd(0, 2, 10, descriptionLabel);
		panel.c.weighty = 0.5;
		panel.gridAdd(0, 3, 5, description);
		panel.c.weighty = 0;
		//panel.gridAdd(0, 4, 10, dateLabel);
		//panel.gridAdd(0, 5, 5, (JComponent) date);
		panel.gridAdd(0, 6, 10, categoriesLabel);
		panel.gridAdd(0, 7, 5, categories);
		panel.gridAdd(0, 8, 10, priorityLabel);
		panel.gridAdd(0, 9, 5, priority);
		panel.gridAdd(0, 11, 10, deleteButton);
		panel.pad(0, 10);
	}
	
	private String[] fixCategoryNameFormat(ArrayList<Category> cats){
		ArrayList<String> names = new ArrayList<String>();
		for(Category cat : cats){
			names.add(cat.getName());
		}
		String[] catNameArray = new String[names.size()];
		System.out.println(names.size()+"");
		return names.toArray(catNameArray);
	}
	
	/**
	 * Sets the parent controller for this controller.
	 * @param pc The parent controller
	 */
	public void setController(PanicController pc) {
		this.pc = pc;
	}
	
	/**
	 * 
	 * @return The view this controller controls
	 */
	public RightPanelView getView() {
		return panel;
	}
	
	
	/**
	 * Will slide out and or in the panel where you add more details.
	 * @param b Slides out if True, slides in if False
	 */
	public void setRightPanel(boolean b) {
		show = b;
		panel.showPanel(b);
	}
	
	/**
	 * 
	 * @return The status of the right panel, True if it is showing, False otherwise.
	 */
	public boolean isOpen() {
		return show;
	}
	
	
	/**
	 * This will return the Task which the right panel is currently adding 
	 * or modifying details on.
	 * @return The currently selected Task
	 */
	public Task getCurrentTask() {
		return currentTask;
	}
	
	/**
	 * Selects a Task, which the user will be able to modify using controls on 
	 * the right panel.
	 * @param t The Task to modify.
	 */
	public void taskSelected(Task t) {
		if (t == currentTask) {
			currentTask = null;
			show = false;
			setRightPanel(false);
		}
		else {
			currentTask = null;
			title.setText(t.getTitle());
			description.setText(t.getDescription());
			priority.setSelectedIndex(t.getPriority()-1);
			currentTask = t;
			show = true;
			setRightPanel(true);
		}
	}
	
	/**
	 * Will animate the view to close or open
	 * @param s String to show as the "title" in the newly opened view.
	 */
	public void togglePanel(String s) {
		show = !show;					//Toggles the internal show-variable
		title.setText(show ? s : "");	//If the panel is about to show, use the String s, otherwise it doesn't matter so use ""
		panel.showPanel(show);	
	}
	
	/**
	 * Whenever a user modifies a task with something, this is called which will
	 * delegate the command to the parent controller.
	 * @param t Task which has been modified.
	 */
	public void updateTask(Task t) {
		pc.updateTask(t);
	}
	
	/**
	 * Whenever a user deletes a task, this is called which will delegate
	 * the command to the parent controller and also close the detailed view.
	 * @param t The task to be deleted
	 */
	public void deleteTask(Task t) {
		pc.deleteTask(t);
		panel.setPreferredSize(new Dimension(0, 0));
		panel.revalidate();
		panel.repaint();
		show = false;
	}
	
	/**
	 * Called when a user changes the language, makes sure that all
	 * views this controller controls changes their values to the correct
	 * language.
	 */
	public void updateLanguage() {
		titleLabel.setText(I18.getInstance().properties.getString("title"));
		descriptionLabel.setText(I18.getInstance().properties.getString("description"));
		priorityLabel.setText(I18.getInstance().properties.getString("priority"));
		categoriesLabel.setText(I18.getInstance().properties.getString("categories"));
		deleteButton.setText(I18.getInstance().properties.getString("delete"));
		s[0] = I18.getInstance().properties.getString("low");
		s[1] = I18.getInstance().properties.getString("medium");
		s[2] = I18.getInstance().properties.getString("high");
	}
	
}
