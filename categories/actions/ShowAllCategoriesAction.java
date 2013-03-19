package categories.actions;

/**
 * Runs when the add category button is pressed, adds a new category.
 * 
 */

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import panic.I18;
import panic.PanicController;

import tasks.Task;
import tasks.TasksPanelController;

public class ShowAllCategoriesAction extends AbstractAction {

	
	private static final long serialVersionUID = 1L;

	
	public void actionPerformed(ActionEvent arg0) {
		PanicController.getInstance().setCategory(null);
	}

}
