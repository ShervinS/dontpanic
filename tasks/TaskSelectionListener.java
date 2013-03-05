package tasks;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import panic.PanicController;

public class TaskSelectionListener implements ListSelectionListener {

	private TasksPanelController pc;
	private TaskTableModel tm;
	private JTable table;
	
	public TaskSelectionListener(JTable table, TasksPanelController p, TaskTableModel tm) {
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