package panic;

import java.awt.Color;

import javax.swing.UIManager;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.synth.SynthLookAndFeel;



public class Main {
	
	public static void main(String[] args) {
		// Initialization of internalization
		I18.getInstance().setLocale("swe");
		
		// Load application color scheme from XML
		//Color c = Color.decode("#ffdc23");
		//UIManager.put("control", c);
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		ThemeLoader tl = new ThemeLoader();
		tl.loadColors();
		
		//Use a Look and Feel with custom Theme (ABORTED, DUE TO TIME RESTRICTIONS)
				/*SynthLookAndFeel synth = new SynthLookAndFeel();
			    try {
			      Class aClass = PanicController.class;
			      InputStream is = aClass.getResourceAsStream("/resources/panicTheme.xml");
			      if (is == null) {
			        System.err.println("Unable to find theme configuration");
			        System.exit(-1);
			      }
			      synth.load(is, aClass);
			    } catch (ParseException e) {
			      System.err.println("Unable to load theme configuration");
			      System.exit(-2);
			    }
			    try {
			      System.out.println("Trying to set the LaF Theme...");
			      UIManager.setLookAndFeel(synth);
			    } catch (javax.swing.UnsupportedLookAndFeelException e) {
			      System.err.println("Unable to change look and feel");
			      System.exit(-3);
			    }*/
		
		// Set menu items in OSX
				System.setProperty("com.apple.mrj.application.apple.menu.about.name", "!Panic");
				System.setProperty("apple.laf.useScreenMenuBar", "true");
				System.setProperty("apple.eawt.quitStrategy","CLOSE_ALL_WINDOWS");
		
		//Create the only instance of the PanicController
		PanicController pc = PanicController.getInstance();
		//UIManager.put("nimbusBase", new Color(67, 23, 23));
		//This needs run after the PanicController's constructor, because the PanicController
		//needs to be fully initiated before this is run.
		pc.start();
	}
	
	

}

