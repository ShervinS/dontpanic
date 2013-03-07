package tasks;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class TasksPanelView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private GridBagConstraints c;
	
	public TasksPanelView() {
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
	}
	
	public void addToView(JComponent quickAdd, JComponent addButton, JComponent pane, JButton showToday) {
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		c.weighty = 0.0;
		c.weightx = 0.9;
		add(quickAdd, c);
		c.gridx += 1;
		c.weighty = 0.0;
		c.weightx = 0.1;
		add(addButton, c);
		c.gridx += 1;
		c.weighty = 0.0;
		c.weightx = 0.1;
		add(showToday, c);
		
		c.gridwidth += 2;
		c.gridy += 1;
		c.gridx = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		add(pane, c);
		
		pane.revalidate();
		pane.repaint();
	}
	

}
