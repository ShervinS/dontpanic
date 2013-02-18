package Panic;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.JDatePicker;

public class RightPanelView extends JPanel {
	
	PanicController pc;
	
	GridBagConstraints c;
	JLabel titleLabel;
	JTextField title;
	
	JLabel descriptionLabel;
	JTextField description;
	
	JMenu priority;
	JMenuItem lowPrio;
	JMenuItem mediumPrio;
	JMenuItem highPrio;
	
	JDatePicker date;
	
	
	public RightPanelView(PanicController p) {
		super();
		pc = p;
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		title = new JTextField();
		description = new JTextField();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		
		c.gridy = 0;
		titleLabel = new JLabel("Title: ");
		add(titleLabel);
		title = new JTextField("Enter Title");
		c.gridy = 1;
		add(title);
		
		c.gridy = 2;
		descriptionLabel = new JLabel("Description: ");
		add(descriptionLabel);
		c.gridy = 3;
		description = new JTextField("Enter Description");
		add(description);
		
		
	}
	
}
