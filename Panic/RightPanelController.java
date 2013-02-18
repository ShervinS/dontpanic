package Panic;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.JDatePicker;

public class RightPanelController {
	
	RightPanelView panel;
	
	JLabel titleLabel;
	JTextField title;
	
	JLabel descriptionLabel;
	JTextField description;
	
	JLabel priorityLabel;
	JMenu priority;
	JMenuItem lowPrio;
	JMenuItem mediumPrio;
	JMenuItem highPrio;
	
	JLabel dateLabel;
	JDatePicker date;
	
	public RightPanelController() {
		
		panel = new RightPanelView(this);
		
		titleLabel = new JLabel("Title: ");
		title = new JTextField();
		
		descriptionLabel = new JLabel("Description: ");
		description = new JTextField();
		
		priorityLabel = new JLabel("Priority: ");
		priority = new JMenu();
		lowPrio = new JMenuItem("Low Priority");
		mediumPrio = new JMenuItem("Medium Priority");
		highPrio = new JMenuItem("High Priority");
		priority.add(lowPrio);
		priority.add(mediumPrio);
		priority.add(highPrio);
		
		dateLabel = new JLabel("Date: ");
		
		panel.gridAdd(0, 0, titleLabel);
		panel.gridAdd(0, 1, title);
		panel.gridAdd(0, 2, descriptionLabel);
		panel.gridAdd(0, 3, description);
		panel.gridAdd(0, 4, priorityLabel);
		panel.gridAdd(0, 5, priority);
	}
	
	public RightPanelView getView() {
		return panel;
	}
}
