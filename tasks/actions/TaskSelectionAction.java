package tasks.actions;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tasks.Task;
import tasks.TaskTableModel;
import tasks.TasksPanelController;

public class TaskSelectionAction implements ListSelectionListener {

	private TaskTableModel tableModel;
	private JTable table;
	
	public TaskSelectionAction(JTable table, TaskTableModel tableModel) {
		this.table = table;
		this.tableModel = tableModel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Task task = (Task) tableModel.getValueAt(table.getSelectedRow(), 0);
		if (task != null)
			TasksPanelController.getInstance().taskSelected((Task) tableModel.getValueAt(table.getSelectedRow(), 0));
	}

}