package tasks;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;


public class TasksPanelView extends JPanel {
	
	private TasksPanelController mpc;
	private GridBagConstraints c;
	
	public TasksPanelView(TasksPanelController mpc) {
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		this.mpc = mpc;
	}
	
	public void addToView(JComponent quickAdd, JComponent addButton, JComponent pane) {
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
		
		c.gridwidth += 1;
		c.gridy += 1;
		c.gridx = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		add(pane, c);
		
		pane.revalidate();
		pane.repaint();
	}
	
	public void updateLanguage() {
		
	}
}
