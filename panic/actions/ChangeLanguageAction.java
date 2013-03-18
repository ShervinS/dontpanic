package panic.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panic.I18;
import panic.PanicController;

/**
 * Action to change the language throughout the application
 * @author joseph
 *
 */
public class ChangeLanguageAction implements ActionListener {

	private String lang;
	
	public ChangeLanguageAction(String lang) {
		this.lang = lang;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		I18.getInstance().setLocale(lang);
		PanicController.getInstance().updateLanguage();
	}

}
