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
		Category s = (Category) categories.getSelectedItem();
		for (Category c : PanicController.getInstance().getCategories()) {
			if (c.getName().equals(s.getName())) {
				DetailsPanelController pc = DetailsPanelController.getInstance();
				if (pc.getCurrentTask() != null) {
					pc.getCurrentTask().setCategory(c);
					pc.updateTask(pc.getCurrentTask());
				}
				break;
			}
		}
	}

}
