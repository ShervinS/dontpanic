package Panic;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class PanicController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		LeftPanelController pcLeft = new LeftPanelController();
		MidPanelController pcMid = new MidPanelController();
		RightPanelController pcRight = new RightPanelController();
		
		JFrame frame = new JFrame("!Panic");
		frame.setContentPane(new MainPanel(new GridBagLayout(), pcLeft.getView(), pcMid.getView(), pcRight.getView()));
		
		frame.pack();
		frame.setVisible(true);		
	}

}
