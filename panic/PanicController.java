package panic;

import java.awt.Color;
import java.util.ArrayList;

import tasks.MidPanelController;
import tasks.Task;
import tasks.TaskManager;

import categories.Category;
import categories.LeftPanelController;
import detailed.RightPanelController;

public class PanicController {

	LeftPanelController leftPanelController;
	MidPanelController midPanelController;
	RightPanelController rightPanelController;
	private TaskManager taskManager;
	
	public PanicController(LeftPanelController leftController, MidPanelController midController, RightPanelController rightController, TaskManager t) {
		this.leftPanelController = leftController;
		this.midPanelController = midController;
		this.rightPanelController = rightController;
		this.taskManager = t;
		t.setController(this);
		rightPanelController.setController(this);
		midPanelController.setController(this);
		midController.updateShownTasks(t.getTaskList());
	}

	
	public void deleteTask(Task t) {
		taskManager.removeTask(t);
		midPanelController.updateShownTasks(taskManager.getTaskList());
	}
	
	public void updateTask(Task t) {
		taskManager.updateTask(t);
		midPanelController.updateShownTasks(taskManager.getTaskList());
	}
	
	public void newTask(Task t) {
		taskManager.addTask(t);
		System.out.println(taskManager.getTaskList());
		midPanelController.updateShownTasks(taskManager.getTaskList());
	}
	public void toggleRightPanel(String s) {
		rightPanelController.togglePanel(s);
	}

	public void taskSelected(Task t) {
		rightPanelController.taskSelected(t);
	}
	
	public ArrayList<Category> getCategories(){
		return taskManager.getCategoryList();
	}
}
