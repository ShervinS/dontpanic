package Panic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
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
	
	JLabel dateLabel;
	JDatePicker date;
	private boolean show;
	
	public RightPanelController() {
		
		panel = new RightPanelView(this);
		
		titleLabel = new JLabel("Title: ");
		titleLabel.setForeground(new Color(0xFFFFFF));
		title = new JTextField();
		
		descriptionLabel = new JLabel("Description: ");
		descriptionLabel.setForeground(new Color(0xFFFFFF));
		description = new JTextArea();
		description.setPreferredSize(new Dimension(100, 100));
		
		priorityLabel = new JLabel("Priority: ");
		priorityLabel.setForeground(new Color(0xFFFFFF));
		String[] s = {"Low", "Medium", "High"};
		priority = new JList(s);
		
		dateLabel = new JLabel("Date: ");
		
		
		panel.gridAdd(0, 0, 5, titleLabel);
		panel.gridAdd(0, 1, 5, title);
		panel.gridAdd(0, 2, 10, descriptionLabel);
		panel.gridAdd(0, 3, 5, description);
		panel.gridAdd(0, 4, 10, priorityLabel);
		panel.gridAdd(0, 5, 5, priority);
		panel.pad(0, 6);
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
