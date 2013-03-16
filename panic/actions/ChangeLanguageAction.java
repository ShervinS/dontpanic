package panic.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import panic.I18;
import panic.PanicController;

/**
 * Action to change the language throughout the application
 * @author joseph
 *
 */
public class ChangeLanguageAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
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
