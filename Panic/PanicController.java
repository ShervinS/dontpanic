package Panic;

public class PanicController {

	LeftPanelController leftPanelController;
	MidPanelController midPanelController;
	RightPanelController rightPanelController;
	
	public PanicController(LeftPanelController leftController, MidPanelController midController, RightPanelController rightController) {
		this.leftPanelController = leftController;
		this.midPanelController = midController;
		this.rightPanelController = rightController;
		midPanelController.setController(this);
	}

	public void toggleRightPanel(String s) {
		rightPanelController.togglePanel(s);
	}

	public Category[] getCategories(){
		//TODO: This is just a stub, fix this method to get data from the model
		
		Category[] categories = new Category[5];
		
		categories[0] = new Category("Green", "School");
		categories[1] = new Category("Red", "Work");
		categories[2] = new Category("Purple", "Activities");
		categories[3] = new Category("Blue", "Home");
		categories[4] = new Category("Yellow", "School");
	
		return categories;
	}
}
