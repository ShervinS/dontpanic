package Panic;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main {

	public static void main(String[] args) {
		
		LeftPanelController pcLeft = new LeftPanelController();
		MidPanelController pcMid = new MidPanelController();
		RightPanelController pcRight = new RightPanelController();
		
		PanicController panicController = new PanicController(pcLeft, pcMid, pcRight);
		pcLeft.setPanicController(panicController);
		
		JFrame frame = new JFrame("!Panic");
		frame.setContentPane(new MainPanel(pcLeft.getView(), pcMid.getView(), pcRight.getView()));
		//frame.setContentPane(new MainPanel(new GridBagLayout(), pcLeft.getView(), pcMid.getView(), pcRight.getView()));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		
		JMenu language = new JMenu("Change Language");
		JMenuItem swedish = new JMenuItem("Swedish");
		JMenuItem english = new JMenuItem("English");
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		language.add(swedish);
		language.add(english);
		edit.add(language);
		file.add(exit);
		
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		frame.setJMenuBar(menuBar);
		
		
		pcLeft.updateGUI();
		
		frame.setSize(new Dimension(600,600));
		frame.setVisible(true);		
	}
}
