package Panic;

import javax.swing.JPanel;

public class MidPanelController {
	
	MidPanelView panel;
	
	public MidPanelController() {
		panel = new MidPanelView();
	}

	public MidPanelView getView() {
		return panel;
	}
}
