package tasks;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


/**
 * The view for the displaying all tasks
 * in the !Panic ToDo-application
 * @author joseph
 *
 */
public class TasksPanelView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private GridBagConstraints c;
	
	/**
	 * Constructor
	 */
	public TasksPanelView() {
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
	}
	
	/**
	 * Adds the components to this view
	 * @param quickAdd Component to act as the textfield for quick adding a task
	 * @param addButton Component to act as the button for adding a new task
	 * @param pane Component to act as the displayer for tasks
	 * @param showToday Component to act as the button which shows today's tasks
	 */
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
