package tasks.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import tasks.Task;
import tasks.TasksPanelController;

public class AddAction extends AbstractAction {


	private static final long serialVersionUID = 1L;
	private JTextField quickAdd;
	
	public AddAction(JTextField quickAdd) {
		this.quickAdd = quickAdd;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		TasksPanelController pc = TasksPanelController.getInstance();
		pc.addTask(
				new Task(quickAdd.getText(), "", 
						pc.getCategory() == null ? "" : pc.getCategory().getName(), 
						1, "", false));
		quickAdd.setText("Quickadd...");
		quickAdd.setForeground(Color.GRAY);
	}

}
