package details.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;

public class CategoriesAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JComboBox categories;
	
	public CategoriesAction(JComboBox categories) {
		this.categories = categories;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) categories.getSelectedItem();
		System.out.println("Category: " + s);
	}

}
