package Panic;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.JDatePicker;

public class RightPanelController {
	
	RightPanelView panel;
	
	JLabel titleLabel;
	JTextField title;
	
	JLabel descriptionLabel;
	JTextArea description;
	
	JLabel priorityLabel;
	JList priority;
	String lowPrio;
	String mediumPrio;
	String highPrio;
	
	JLabel dateLabel;
	JDatePicker date;
	
	public RightPanelController() {
		
		panel = new RightPanelView(this);
		
		titleLabel = new JLabel("Title: ");
		title = new JTextField();
		
		descriptionLabel = new JLabel("Description: ");
		description = new JTextArea();
		description.setPreferredSize(new Dimension(100, 100));
		
		priorityLabel = new JLabel("Priority: ");
		lowPrio = "Low";
		mediumPrio = "Medium";
		highPrio = "High";
		ArrayList<String> s = new ArrayList<String>();
		s.add(lowPrio);
		s.add(mediumPrio);
		s.add(highPrio);
		priority = new JList(s.toArray());
		
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
