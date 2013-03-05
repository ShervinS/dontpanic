package tasks.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import tasks.Task;
import tasks.TasksPanelController;

public class AddAction extends AbstractAction {

	private TasksPanelController pc;
	private JTextField quickAdd;
	
	public AddAction(TasksPanelController pc, JTextField quickAdd) {
		this.pc = pc;
		this.quickAdd = quickAdd;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		pc.addTask(new Task(quickAdd.getText(), "", "", 1, "", false));
		quickAdd.setText("Quickadd...");
		quickAdd.setForeground(Color.GRAY);
	}

}
