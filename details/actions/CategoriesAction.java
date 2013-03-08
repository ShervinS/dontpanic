package details.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;

import panic.PanicController;
import categories.Category;
import details.DetailsPanelController;

public class CategoriesAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JComboBox categories;
	
	public CategoriesAction(JComboBox categories) {
		this.categories = categories;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) categories.getSelectedItem();
		for (Category c : PanicController.getInstance().getCategories()) {
			if (c.getName().equals(c)) {
				DetailsPanelController.getInstance().getCurrentTask().setCategory(c);
				break;
			}
		}
	}

}
