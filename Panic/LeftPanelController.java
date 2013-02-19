package Panic;

public class LeftPanelController {

	LeftPanelView panel = new LeftPanelView();
	protected PanicController panicController;
	Category categories[];
	
	public LeftPanelView getView() {
		return panel;
	}
	
	public void setPanicController(PanicController panicController) {
		this.panicController = panicController;
	}
	
}
