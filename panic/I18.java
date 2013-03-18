package panic;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Class for helping with the language internationalization,
 * you get the word in the language of the current locale by calling
 * I18.getInstance.properties("*keyword*") where *keyword* is the keyword used
 * in the resource bundle 
 * 
 * @author Johannes Henriksson
 *
 */
public class I18 {
    private static I18 instance = null;
    public ResourceBundle properties;

    private I18() {
    	Locale currentLocale = new Locale(Locale.getDefault().getLanguage(), Locale.getDefault().getCountry());
    	//currentLocale = new Locale("se", "SE");
    	properties = ResourceBundle.getBundle("resources.MessagesBundle",currentLocale);
    }

    public static I18 getInstance() {
            if (instance == null) {
                    synchronized (I18 .class){
                            if (instance == null) {
                                    instance = new I18 ();
                            }
                  }
            }
            return instance;
    }
    
    public void setLocale(String country){
    	if(country.equals("swe")){
    		properties = ResourceBundle.getBundle("resources.MessagesBundle",new Locale("se", "SE"));
    	}else if(country.equals("usa")){
    		properties = ResourceBundle.getBundle("resources.MessagesBundle",new Locale("en", "US"));
    	}else{
    		properties = ResourceBundle.getBundle("resources.MessagesBundle",new Locale(Locale.getDefault().getLanguage(), Locale.getDefault().getCountry()));
    	}
    }
}
