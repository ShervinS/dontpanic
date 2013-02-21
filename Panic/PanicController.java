package Panic;

import java.awt.Color;

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
	
	public Category[] getCategories(){
		//TODO: This is just a stub, fix this method to get data from the model
		
		Category[] categories = new Category[5];
		
		categories[0] = new Category("School", new Color(0xdd4444));
		categories[1] = new Category("Work", new Color(0x44aa44));
		categories[2] = new Category("Activities", new Color(0x44dd44));
		categories[3] = new Category("Home", new Color(0x444aa));
		categories[4] = new Category("School", new Color(0x4444dd));

	
		return categories;
	}
}
