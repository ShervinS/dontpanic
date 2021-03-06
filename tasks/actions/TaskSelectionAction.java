package tasks.actions;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tasks.Task;
import tasks.TaskTableModel;
import tasks.TasksPanelController;

/**
 * Action for when a Task is selected
 * @author joseph
 *
 */
public class TaskSelectionAction implements ListSelectionListener {

	private TaskTableModel tableModel;
	private JTable table;
	
	/**
	 * Constructor
	 * @param table The table to get selected row from
	 * @param tableModel The model to retrieve selected tasks from
	 */
	public TaskSelectionAction(JTable table, TaskTableModel tableModel) {
		this.table = table;
		this.tableModel = tableModel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		//The Task is stored in the first column in the table model, retrieve it!
		Task task = (Task) tableModel.getValueAt(table.getSelectedRow(), 0);
		//If it is not null, select it
		if (task != null)
			TasksPanelController.getInstance().taskSelected((Task) tableModel.getValueAt(table.getSelectedRow(), 0));
	}

}