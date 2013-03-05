package panic;

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
import javax.swing.JOptionPane;

import panic.actions.ExitAction;

import tasks.TasksPanelController;
import tasks.TaskManager;

import categories.CategoryPanelController;
import details.DetailsPanelController;

public class Main {
	
	public static void main(String[] args) {
		// Initialization of internalization
		I18.getInstance().setLocale("swe");
		
		// Set menu items in OSX
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "!Panic");
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("apple.eawt.quitStrategy","CLOSE_ALL_WINDOWS");

		CategoryPanelController pcLeft = new CategoryPanelController();
		TasksPanelController pcMid = new TasksPanelController();
		DetailsPanelController pcRight = new DetailsPanelController();
		

		PanicController panicController = new PanicController(pcLeft, pcMid,
				pcRight, new TaskManager());
	}
	
	

}

