package tasks;


import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import panic.I18;
import panic.PanicController;
import tasks.actions.AddAction;
import tasks.actions.QuickAddAction;
import tasks.actions.TaskSelectionAction;


public class TasksPanelController {
	
	TasksPanelView panel;
	PanicController pc;
	private JTextField quickAdd; 
	private JButton addButton;
	private JScrollPane pane; 
	private TaskTableModel tableModel;
	
	
	public TasksPanelController() {
		I18.getInstance().setLocale("swe");
		
		//Initialize the views
		panel = new TasksPanelView(this);
		
		String[] h = {"Title", "DueDate", "Priority"};
		tableModel = new TaskTableModel(h);
		JTable table = new JTable(tableModel);
		table.getSelectionModel().addListSelectionListener(new TaskSelectionAction(table, this, tableModel));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		quickAdd = new JTextField(I18.getInstance().properties.getString("quickAdd"));
		quickAdd.setEditable(true);
		quickAdd.setForeground(Color.GRAY);
		addButton = new JButton(I18.getInstance().properties.getString("addTask"), new ImageIcon(this.getClass().getResource("/resources/addIcon.png")));
	}
	
	public void enable(PanicController pc) {
		//Set parent controller
		this.pc = pc;
		
		//Add actions and listeners
		quickAdd.addFocusListener(new QuickAddAction(quickAdd));
		addButton.addActionListener(new AddAction(this, quickAdd));			
		
		//Give everything to the view
		panel.addToView(quickAdd, addButton, pane);
		
	}

	public TasksPanelView getView() {
		return panel;
	}
	
	
	public void addTask(Task t) {
		pc.newTask(t);
		pc.taskSelected(t);
	}
	
	public void updateShownTasks(ArrayList<Task> tasks) {
		ArrayList<Object[]> newData = new ArrayList<Object[]>();
		for (int i = tasks.size()-1; i >= 0;i--) {
			Task task = tasks.get(i);
			Object[] newRow = new Object[3];
			newRow[0] = task;
			newRow[1] = task.getDueDate();
			newRow[2] = task.getPriority();
			newData.add(newRow);
		}
		tableModel.changeData(newData);
		tableModel.fireTableDataChanged();
	}
	
	public void taskSelected(Task t) {
		pc.taskSelected(t);
	}
	
	public void newTask(Task t) {
		pc.newTask(t);
	}

}
