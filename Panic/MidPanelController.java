package Panic;

import javax.swing.JPanel;

public class MidPanelController {
	
	MidPanelView panel;
	PanicController pc;
	
	public MidPanelController() {
		panel = new MidPanelView(this);
	}

	public MidPanelView getView() {
		return panel;
	}
	
	public void toggleRightPanel(String s) {
		pc.toggleRightPanel(s);
	}
	
	public void setController(PanicController pc) {
		this.pc = pc;
	}
}
