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
	
	RightPanelView panel;
	
	private TaskItem currentTask;
	
	JLabel titleLabel;
	JTextField title;
	
	JLabel descriptionLabel;
	JTextArea description;
	
	JLabel priorityLabel;
	JList priority;
	
	JLabel dateLabel;
	JDatePicker date;
	
	JLabel categoriesLabel;
	JComboBox categories;
	
	private boolean show;
	
	/**
	 * Constructor for RightPanelController
	 */
	public RightPanelController() {
		
		panel = new RightPanelView(this);
		
		titleLabel = new JLabel("Title: ");
		titleLabel.setForeground(new Color(0xFFFFFF));
		title = new JTextField();
		
		descriptionLabel = new JLabel("Description: ");
		descriptionLabel.setForeground(new Color(0xFFFFFF));
		description = new JTextArea();
		
		priorityLabel = new JLabel("Priority: ");
		priorityLabel.setForeground(new Color(0xFFFFFF));
		String[] s = {"Low", "Medium", "High"};
		priority = new JList(s);
		
		dateLabel = new JLabel("Date: ");
		dateLabel.setForeground(new Color(0xFFFFFF));
		JDateComponentFactory factory = new JDateComponentFactory();
		date = factory.createJDatePicker();
		
		categoriesLabel = new JLabel("Categories: ");
		categoriesLabel.setForeground(new Color(0xFFFFFF));
		String[] cat = {"Test1", "Test2"};
		categories = new JComboBox(cat);
		
		
		title.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				System.out.println("Title: " + title.getText());
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				System.out.println("Title: " + title.getText());
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				System.out.println("Title: " + title.getText());
				
			}

			
		});
		
		description.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				System.out.println("Description: " + description.getText());
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				System.out.println("Description: " + description.getText());
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				System.out.println("Description: " + description.getText());
			}

			
		});
		
		
		priority.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				String s = (String) priority.getSelectedValue();
				System.out.println("Priority: " + s);
			}
		});
		
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
		panel.pad(0, 10);
	}
	
	/**
	 * 
	 * @return The view this controller controls
	 */
	public RightPanelView getView() {
		return panel;
	}
	
	
	public void setRightPanel(boolean b) {
		show = !b;
		togglePanel("");
	}
	
	public boolean isOpen() {
		return show;
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
	
}
