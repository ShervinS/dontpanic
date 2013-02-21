package Panic;

import java.util.ArrayList;

import javax.swing.JPanel;

public class MidPanelController {
	
	MidPanelView panel;
	PanicController pc;
	
	public MidPanelController(ArrayList<Task> tasks) {
		panel = new MidPanelView(this, tasks);
	}
	
	public MidPanelController() {
		panel = new MidPanelView(this, new ArrayList<Task>());
	}

	public MidPanelView getView() {
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
