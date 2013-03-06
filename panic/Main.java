package panic;



public class Main {
	
	public static void main(String[] args) {
		// Initialization of internalization
		I18.getInstance().setLocale("swe");
		
		// Set menu items in OSX
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "!Panic");
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("apple.eawt.quitStrategy","CLOSE_ALL_WINDOWS");
		
		//Create the only instance of the PanicController
		PanicController pc = PanicController.getInstance();
		
		//This needs run after the PanicController's constructor, because the PanicController
		//needs to be fully initiated before this is run.
		pc.start();
	}
	
	

}

