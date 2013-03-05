package details;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
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
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.SqlDateModel;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;

import panic.I18;
import panic.PanicController;

import tasks.Task;

import categories.Category;
import details.actions.CategoriesAction;
import details.actions.DateAction;
import details.actions.DeleteAction;
import details.actions.DescriptionAction;
import details.actions.DoneAction;
import details.actions.PriorityAction;
import details.actions.TitleAction;

/**
 * The RightPanelController controls the right part of the !Panic ToDo-app
 * In this part you can add more details to your task.
 * A window will slide in and out when it's needed.
 * @author joseph
 *
 */
public class DetailsPanelController implements ActionListener {
	
	private String[] categoryStringArray;
	
	private PanicController pc;
	
	private DetailsPanelView panel;
	
	private JLabel titleLabel;
	private JTextField title;
	
	private JLabel descriptionLabel;
	private JTextArea description;
	
	private JLabel priorityLabel;
	private JList priority;
	
	private JLabel dateLabel;
	private JDateChooser date;
	
	private JLabel categoriesLabel;
	private JComboBox categories;
	
	private Timer t;
	
	private String[] s;
	
	private boolean animate;
	
	private Task currentTask;
	
	private JButton deleteButton;
	private JButton doneButton;
	
	/**
	 * Constructor for RightPanelController
	 * Will initialize all the views.
	 * However, enable(PanicController c) needs to 
	 * be called before the views get their actions 
	 * and get painted.
	 */
	public DetailsPanelController() {
		//Get language
		I18.getInstance();
		I18.setLocale("swe");
		
		//Initialize the RightPanel, which all views will be painted on
		panel = new DetailsPanelView(this);
		
		//Initialization of title and its label
		titleLabel = new JLabel(I18.properties.getString("title"));
		titleLabel.setForeground(Color.BLACK);
		title = new JTextField();
		
		//Initialization of description and its label
		descriptionLabel = new JLabel(I18.properties.getString("description"));
		descriptionLabel.setForeground(Color.BLACK);
		description = new JTextArea();
		
		//Initialization of priority and its label
		priorityLabel = new JLabel(I18.properties.getString("priority"));
		priorityLabel.setForeground(Color.BLACK);
		String[] s = {I18.getInstance().properties.getString("low"), 
				      I18.getInstance().properties.getString("medium"),
				      I18.getInstance().properties.getString("high")};
		priority = new JList(s);
		
		//Initialization of date and its label
		dateLabel = new JLabel(I18.properties.getString("date"));
		dateLabel.setForeground(Color.BLACK);
		date = new JDateChooser();
		
		//Initialization of categories and its label
		categoriesLabel = new JLabel(I18.properties.getString("categories"));
		categoriesLabel.setForeground(Color.BLACK);
		categories = new JComboBox();	
		
		//Initialization of the delete button
		deleteButton = new JButton(I18.getInstance().properties.getString("delete"), new ImageIcon(this.getClass().getResource("/resources/xIcon.png")));
		deleteButton.setForeground(Color.black);
		deleteButton.setBackground(Color.red);
		
		//Initialization of the done-button
		doneButton = new JButton("Klar", new ImageIcon(this.getClass().getResource("/resources/okIcon.png")));
		doneButton.setForeground(Color.black);
		doneButton.setBackground(Color.green);
	}
	
	
	/**
	 * Formats the array of Category to an array of String representing the categories
	 * @param cats The array of Category
	 * @return The array of String
	 */
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
	 * Enables this part of the TodoApp, will need a parent controller
	 * PanicController to be enabled
	 * @param pc The parent controller
	 */
	public void enable(PanicController pc) {
		this.pc = pc;
		
		categoryStringArray = fixCategoryNameFormat( pc.getCategories());
		for(String name:categoryStringArray){
			categories.addItem(name);
		}
		
		//Add actions to all the components
		title.getDocument().addDocumentListener(new TitleAction(this, title));
		description.getDocument().addDocumentListener(new DescriptionAction(this, description));
		priority.addListSelectionListener(new PriorityAction(this, priority));
		categories.addActionListener(new CategoriesAction(this, categories));
		date.addPropertyChangeListener(new DateAction(this, date));
		deleteButton.addActionListener(new DeleteAction(this));
		doneButton.addActionListener(new DoneAction(this));
		
		//Add every view component to the panel
		panel.gridAdd(0, 0, 5, titleLabel);
		panel.gridAdd(0, 1, 5, title);
		panel.gridAdd(0, 2, 10, descriptionLabel);
		panel.c.weighty = 0.5;
		panel.gridAdd(0, 3, 5, description);
		panel.c.weighty = 0;
		panel.gridAdd(0, 4, 10, dateLabel);
		panel.gridAdd(0, 5, 5, (JComponent) date);
		panel.gridAdd(0, 6, 10, categoriesLabel);
		panel.gridAdd(0, 7, 5, categories);
		panel.gridAdd(0, 8, 10, priorityLabel);
		panel.gridAdd(0, 9, 5, priority);
		panel.pad(0, 11);
		panel.gridAdd(0, 12, 10, doneButton);
		panel.gridAdd(0, 13, 10, deleteButton);

		
	}
	
	/**
	 * 
	 * @return The view this controller controls
	 */
	public DetailsPanelView getView() {
		return panel;
	}
	
	
	/**
	 * Will slide out and or in the panel where you add more details.
	 * @param b Slides out if True, slides in if False
	 */
	public void setRightPanel(boolean b) {
		if (!b)
			currentTask = null;
		panel.showPanel(b);
	}
	
	/**
	 * 
	 * @return The status of the right panel, True if it is showing, False otherwise.
	 */
	public boolean isOpen() {
		return panel.isOpen();
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
	public void animateChangeOfTask() {
		setRightPanel(false);
		t = new Timer(100, this);
		t.start();
	}
	*/
	/**
	 * Selects a Task, which the user will be able to modify using controls on 
	 * the right panel.
	 * @param t The Task to modify.
	 */
	public void taskSelected(Task t) {
		if (t != t) {
			currentTask = null;
			setRightPanel(false);
		}
		else {
			//First set the currentTask to null, ensuring no further changes
			//will be made during the "transition period"
			currentTask = null;
			
			//Use the Task t information to show on the right panel
			title.setText(t.getTitle());
			description.setText(t.getDescription());
			priority.setSelectedIndex(t.getPriority()-1);
			String dateString = t.getDueDate();
			if (dateString.length() < 8) {
				date.setCalendar(null);
			}
			else {
				int year = Integer.parseInt(dateString.substring(0, 4));
				int month = Integer.parseInt(dateString.substring(4, 6));
				int day = Integer.parseInt(dateString.substring(6, 8));
				Calendar c = Calendar.getInstance();
				c.set(year, month-1, day);
				date.setDate(c.getTime());
			}
			
			//Lastly, make the currentTask t and show the right panel
			currentTask = t;
			setRightPanel(true);
		}
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
		panel.setOpen(false);
		panel.revalidate();
		panel.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		setRightPanel(true);
		t.stop();
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
