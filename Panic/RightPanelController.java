package Panic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePicker;

public class RightPanelController {
	
	RightPanelView panel;
	
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
	
	public RightPanelView getView() {
		return panel;
	}
	
	public void togglePanel(String s) {
		show = show ? false : true;
		title.setText(show ? s : "");
		panel.showPanel(show);
	}
	
}
