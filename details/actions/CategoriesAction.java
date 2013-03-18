package details.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import panic.PanicController;
import categories.Category;
import details.DetailsPanelController;

/**
 * Action for use with a categorychanger for a Task
 */
public class CategoriesAction implements ActionListener {

	private JComboBox categories;
	
	/**
	 * Constructor
	 * @param categories The JComboBox which will hold the currently selected category
	 */
	public CategoriesAction(JComboBox categories) {
		this.categories = categories;
	}

	/**
	 * When a category is changed, this function will be called
	 * Should not be called manually.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) categories.getSelectedItem();
		DetailsPanelController pc = DetailsPanelController.getInstance();
		if (s.equals("")) {
			//If the "no category" category is chosen, create a new dummy category and set
			//that as the currentTasks() category
			if (pc.getCurrentTask() != null) {
				pc.getCurrentTask().setCategory(new Category("", Color.black));
				pc.updateTask(pc.getCurrentTask());
			}
		}
		else {
			//If any other category is chosen, find the correct category and set it as the tasks
			//category
			for (Category c : PanicController.getInstance().getCategories()) {
				if (c.getName().equals(s)) {
					if (pc.getCurrentTask() != null) {
						pc.getCurrentTask().setCategory(c);
						pc.updateTask(pc.getCurrentTask());
					}
					break;
				}
			}
		}
	}

}
