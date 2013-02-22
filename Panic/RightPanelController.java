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
	
	private Task currentTask;
	
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
		String[] s = {I18.getInstance().properties.getString("low"), I18.getInstance().properties.getString("medium"), I18.getInstance().properties.getString("high")};
		priority = new JList(s);
		
		dateLabel = new JLabel(I18.getInstance().properties.getString("date"));
		dateLabel.setForeground(new Color(0xFFFFFF));
		JDateComponentFactory factory = new JDateComponentFactory();
		date = factory.createJDatePicker();
		
		categoriesLabel = new JLabel(I18.getInstance().properties.getString("categories"));
		categoriesLabel.setForeground(new Color(0xFFFFFF));
		String[] cat = {"Test1", "Test2"};
		categories = new JComboBox(cat);
		
		
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
		
		JButton deleteButton = new JButton(I18.getInstance().properties.getString("delete"));
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
	
	
	public void setRightPanel(boolean b) {
		show = b;
		panel.showPanel(b);
	}
	
	public boolean isOpen() {
		return show;
	}
	
	
	
	public Task getCurrentTask() {
		return currentTask;
	}
	
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
		show = !show;
		title.setText(show ? s : "");
		panel.showPanel(show);
	}
	
	public void updateTask(Task t) {
		pc.updateTask(t);
	}
	
	public void deleteTask(Task t) {
		pc.deleteTask(t);
		panel.setPreferredSize(new Dimension(0, 0));
		panel.revalidate();
		panel.repaint();
		show = false;
	}
	
}
