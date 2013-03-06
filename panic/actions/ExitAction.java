package panic.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.JFrame;

public class ExitAction extends WindowAdapter implements ActionListener {

	private JFrame frame;
	
	public ExitAction(JFrame frame) {
		this.frame = frame;
	}
	
	private void exit() {
		try {
			Properties p = new Properties();
			p.setProperty("WindowX", String.valueOf(frame.getWidth()));
			p.setProperty("WindowY", String.valueOf(frame.getHeight()));
			p.setProperty("PositionX", String.valueOf(
									   Math.round(frame.getLocation().getX())));
			p.setProperty("PositionY", String.valueOf(
									   Math.round(frame.getLocation().getY())));
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
	
	public void windowClosing(WindowEvent e) {
		exit();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		exit();
		
	}
}
