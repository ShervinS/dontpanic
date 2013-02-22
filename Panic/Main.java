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
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main {
	private static JMenu file;
	private static JMenu edit;
	private static JMenu help;
	private static JMenu language;
	private static JMenuItem english;
	private static JMenuItem swedish;
	
	public static void main(String[] args) {
		// Initialization of internalization
		I18.getInstance().setLocale("swe");

		LeftPanelController pcLeft = new LeftPanelController();
		MidPanelController pcMid = new MidPanelController();
		final RightPanelController pcRight = new RightPanelController();

		PanicController panicController = new PanicController(pcLeft, pcMid,
				pcRight, new TaskManager());
		pcLeft.setPanicController(panicController);

		final JFrame frame = new JFrame("!Panic");
		frame.setContentPane(new MainPanel(pcLeft.getView(), pcMid.getView(),
				pcRight.getView()));
		// frame.setContentPane(new MainPanel(new GridBagLayout(),
		// pcLeft.getView(), pcMid.getView(), pcRight.getView()));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					Properties p = new Properties();
					p.setProperty("WindowX", String.valueOf(frame.getWidth()));
					p.setProperty("WindowY", String.valueOf(frame.getHeight()));
					p.setProperty("PositionX", String.valueOf(Math.round(frame
							.getLocation().getX())));
					p.setProperty("PositionY", String.valueOf(Math.round(frame
							.getLocation().getY())));
					p.setProperty("RightPanel",
							String.valueOf(pcRight.isOpen()));
					String saveLocation = System.getProperty("user.home")
							+ "/.TODO-group9/config.properties";

					File saveFile = new File(saveLocation);
					if (!saveFile.exists()) {
						saveFile.getParentFile().mkdirs();
						saveFile.createNewFile();
					}
					p.store(new FileOutputStream(saveFile),
							"Settings for TODO-application");
				} catch (Exception ex) {
					System.err.println("Could not save.");
					ex.printStackTrace();
				} finally {
					System.exit(0);
				}
			}
		});
		JMenuBar menuBar = new JMenuBar();
		file = new JMenu(I18.getInstance().properties.getString("file"));
		edit = new JMenu(I18.getInstance().properties.getString("edit"));
		help = new JMenu(I18.getInstance().properties.getString("help"));
		
		language = new JMenu(I18.getInstance().properties.getString("changeLang"));
		swedish = new JMenuItem(I18.getInstance().properties.getString("swedish"));
		english = new JMenuItem(I18.getInstance().properties.getString("english"));
		
		JMenuItem exit = new JMenuItem(I18.getInstance().properties.getString("exit"));
		exit.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Properties p = new Properties();
					p.setProperty("WindowX", String.valueOf(frame.getWidth()));
					p.setProperty("WindowY", String.valueOf(frame.getHeight()));
					p.setProperty("PositionX", String.valueOf(Math.round(frame
							.getLocation().getX())));
					p.setProperty("PositionY", String.valueOf(Math.round(frame
							.getLocation().getY())));
					p.setProperty("RightPanel",
							String.valueOf(pcRight.isOpen()));
					String saveLocation = System.getProperty("user.home")
							+ "/.TODO-group9/config.properties";

					File saveFile = new File(saveLocation);
					if (!saveFile.exists()) {
						saveFile.getParentFile().mkdirs();
						saveFile.createNewFile();
					}
					p.store(new FileOutputStream(saveFile),
							"Settings for TODO-application");
				} catch (Exception ex) {
					System.err.println("Could not save.");
					ex.printStackTrace();
				} finally {
					System.exit(0);
				}
			}
		});
		//TODO Update the gui with the right language
		swedish.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				I18.setLocale("swe");
				update();
				//file.setText(I18.getInstance().properties.getString("file"));
			}
		});
		
		english.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				I18.setLocale("usa");
				update();
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
		int posX = 20;
		int posY = 20;
		boolean b = false;
		try {
			Properties p = new Properties();
			String saveLocation = System.getProperty("user.home")
					+ "/.TODO-group9/config.properties";
			p.load(new FileInputStream(new File(saveLocation)));
			x = Integer.parseInt(p.getProperty("WindowX"));
			y = Integer.parseInt(p.getProperty("WindowY"));
			posX = Integer.parseInt(p.getProperty("PositionX"));

			posY = Integer.parseInt(p.getProperty("PositionY"));
			// b = Boolean.parseBoolean(p.getProperty("RightPanel")); //Vi vet
			// inte vilken task som är "vald" än...? Hur bestämmer vi det?
		} catch (Exception e) {
			System.err
					.println("Could not load configuration, using default values");
		} finally {
			frame.setSize(new Dimension(x, y));
			frame.setLocation(posX, posY);
			frame.setVisible(true);
			pcRight.setRightPanel(b);
		}

	}
	
	private static void update(){
		file.setText(I18.getInstance().properties.getString("file"));
		edit.setText(I18.getInstance().properties.getString("edit"));
		help.setText(I18.getInstance().properties.getString("help"));
		language.setText(I18.getInstance().properties.getString("changeLang"));
		swedish.setText(I18.getInstance().properties.getString("swedish"));
		english.setText(I18.getInstance().properties.getString("english"));
	}

}
