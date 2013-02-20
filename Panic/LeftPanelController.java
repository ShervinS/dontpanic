package Panic;

import java.util.Arrays;

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
	
	public void updateGUI(){
		this.categories = panicController.getCategories();
		System.out.print("Categories: %s" + Arrays.toString(this.categories));
	
		logCategories();
	}
	
	public void logCategories() {
		for (Category cat : this.categories)
		    System.out.println(cat.getName());
	}
}
