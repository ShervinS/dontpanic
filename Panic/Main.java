package Panic;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		LeftPanelController pcLeft = new LeftPanelController();
		MidPanelController pcMid = new MidPanelController();
		RightPanelController pcRight = new RightPanelController();
		
		PanicController panicController = new PanicController(pcLeft, pcMid, pcRight);
		pcLeft.setPanicController(panicController);
		
		JFrame frame = new JFrame("!Panic");
		frame.setContentPane(new MainPanel(new GridBagLayout(), pcLeft.getView(), pcMid.getView(), pcRight.getView()));
		
		pcLeft.updateGUI();
		
		frame.setSize(new Dimension(600,600));
		frame.setVisible(true);		
	}
}
