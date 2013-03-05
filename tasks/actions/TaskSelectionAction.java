package tasks.actions;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tasks.Task;
import tasks.TaskTableModel;
import tasks.TasksPanelController;

public class TaskSelectionAction implements ListSelectionListener {

	private TasksPanelController pc;
	private TaskTableModel tm;
	private JTable table;
	
	public TaskSelectionAction(JTable table, TasksPanelController p, TaskTableModel tm) {
		pc = p;
		this.tm = tm;
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Task task = (Task) tm.getValueAt(table.getSelectedRow(), 0);
		if (task != null)
			pc.taskSelected((Task) tm.getValueAt(table.getSelectedRow(), 0));
	}

}