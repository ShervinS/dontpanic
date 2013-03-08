package categories.actions;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import categories.CategoryPanelController;

public class SelectCategoryAction implements ListSelectionListener {

	private JTable table;
	
	public SelectCategoryAction(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		CategoryPanelController.getInstance().selectCategoryAtIndex(table.getSelectedRow());
	}
}