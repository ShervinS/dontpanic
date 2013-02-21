package Panic;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

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
		
		final JFrame frame = new JFrame("!Panic");
		frame.setContentPane(new MainPanel(pcLeft.getView(), pcMid.getView(), pcRight.getView()));
		//frame.setContentPane(new MainPanel(new GridBagLayout(), pcLeft.getView(), pcMid.getView(), pcRight.getView()));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					Properties p = new Properties();
					p.setProperty("WindowX", String.valueOf(frame.getWidth()));
					p.setProperty("WindowY", String.valueOf(frame.getHeight()));
					String saveLocation = System.getProperty("user.home") + "/.TODO-group9/config.properties";
					
					File saveFile = new File(saveLocation);
					if (!saveFile.exists()) {
						saveFile.getParentFile().mkdirs();
						saveFile.createNewFile();
					}
					p.store(new FileOutputStream(saveFile), "Settings for TODO-application");
				}
				catch(Exception ex) {
					System.err.println("Could not save.");
					ex.printStackTrace();
				}
				finally {
					System.exit(0);
				}
			}
		});
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
		
		int x = 600;
		int y = 600;
		try {
			Properties p = new Properties();
			String saveLocation = System.getProperty("user.home") + "/.TODO-group9/config.properties";
			p.load(new FileInputStream(new File(saveLocation)));
			x = Integer.parseInt(p.getProperty("WindowX"));
			y = Integer.parseInt(p.getProperty("WindowY"));
			frame.setSize(new Dimension(x, y));
		}
		catch (Exception e) {
			System.err.println("Could not load configuration, using default values");
		}
		finally {
			frame.setSize(new Dimension(x, y));
			frame.setVisible(true);
		}
			
	}
}
