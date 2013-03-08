package categories.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import tasks.Task;
import tasks.TasksPanelController;

public class AddCategoryAction extends AbstractAction {


	private static final long serialVersionUID = 1L;
	private JTextField quickAdd;
	
	public void AddAction() {
		this.quickAdd = quickAdd;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		TasksPanelController.getInstance().addTask(new Task(quickAdd.getText(), "", "", 1, "", false));
		quickAdd.setText("Quickadd...");
		quickAdd.setForeground(Color.GRAY);
	}

}