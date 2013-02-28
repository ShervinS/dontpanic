package tasks;


import java.util.ArrayList;

import javax.swing.JPanel;

import panic.PanicController;


public class TasksPanelController {
	
	TasksPanelView panel;
	PanicController pc;
	
	public TasksPanelController(ArrayList<Task> tasks) {
		panel = new TasksPanelView(this, tasks);
	}
	
	public TasksPanelController() {
		panel = new TasksPanelView(this, new ArrayList<Task>());
	}

	public TasksPanelView getView() {
		return panel;
	}
	
	public void toggleRightPanel(String s) {
		pc.toggleRightPanel(s);
	}
	
	public void addTask(Task t) {
		pc.newTask(t);
		pc.taskSelected(t);
	}
	
	public void setController(PanicController pc) {
		this.pc = pc;
	}
	
	public void updateShownTasks(ArrayList<Task> tasks) {
		panel.updateShownTasks(tasks);
	}
	
	public void newTask(Task t) {
		pc.newTask(t);
	}

}
