package tasks.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import tasks.Task;
import tasks.TasksPanelController;

/**
 * Action for adding a Task
 * @author joseph
 *
 */
public class AddAction implements ActionListener {


	private JTextField quickAdd;
	
	/**
	 * Constructor
	 * @param quickAdd The TextField the title will be taken from
	 */
	public AddAction(JTextField quickAdd) {
		this.quickAdd = quickAdd;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		TasksPanelController pc = TasksPanelController.getInstance();
		//Adds a new Task, if we have a currently selected category
		//that category will be the one the new Task belongs to
		pc.addTask(
				new Task(quickAdd.getText(), "", 
						pc.getCategory() == null ? "" : pc.getCategory().getName(), 
						1, "", false));
		quickAdd.setText("Quickadd...");
		quickAdd.setForeground(Color.GRAY);
	}

}
