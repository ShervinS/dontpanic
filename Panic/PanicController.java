package Panic;

public class PanicController {

	LeftPanelController leftPanelController;
	MidPanelController midPanelController;
	RightPanelController rightPanelController;
	
	public PanicController(LeftPanelController leftController, MidPanelController midController, RightPanelController rightController) {
		this.leftPanelController = leftController;
		this.midPanelController = midController;
		this.rightPanelController = rightController;
	}

}
