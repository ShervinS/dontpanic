package Panic;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18 {
    private static I18 instance = null;
    public static ResourceBundle properties;

    private I18() {
    	Locale currentLocale = new Locale(Locale.getDefault().getLanguage(), Locale.getDefault().getCountry());
    	//currentLocale = new Locale("se", "SE");
    	properties = ResourceBundle.getBundle("MessagesBundle",currentLocale);
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
    
    public static void setLocale(String country){
    	if(country.equals("swe")){
    		properties = ResourceBundle.getBundle("MessagesBundle",new Locale("se", "SE"));
    	}else if(country.equals("usa")){
    		properties = ResourceBundle.getBundle("MessagesBundle",new Locale("en", "US"));
    	}else{
    		properties = ResourceBundle.getBundle("MessagesBundle",new Locale(Locale.getDefault().getLanguage(), Locale.getDefault().getCountry()));
    	}
    }
}
