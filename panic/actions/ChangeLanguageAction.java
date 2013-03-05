package panic.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import panic.I18;
import panic.PanicController;

public class ChangeLanguageAction extends AbstractAction {

	private PanicController pc;
	private String lang;
	
	public ChangeLanguageAction(String lang, PanicController pc) {
		this.lang = lang;
		this.pc = pc;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		I18.setLocale(lang);
		pc.updateLanguage();
	}

}
