package tasks;


import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import panic.I18;
import panic.PanicController;
import panic.TodayTasks.ShowTodayAction;
import tasks.actions.AddAction;
import tasks.actions.QuickAddAction;
import tasks.actions.TaskSelectionAction;
import categories.Category;

/**
 * The controller for the views that
 * represent the tasks in the !Panic ToDo-appliction
 * @author joseph
 *
 */
public class TasksPanelController {
	
	private TasksPanelView panel;
	private PanicController pc;
	private JTextField quickAdd; 
	private JButton addButton;
	private JButton showToday;
	private JScrollPane pane; 
	private TaskTableModel tableModel;
	private JTable table;
	private ShowTodayAction showTodayAction;
	private Category currentCategory;
	private static TasksPanelController instance;
	
	

	private TasksPanelController() {
		I18.getInstance().setLocale("swe");
		
		//Initialize the views
		panel = new TasksPanelView();
		
		
		//The table and its model/renderer
		ResourceBundle langProp = I18.getInstance().properties;
		String[] h = {langProp.getString("title"), 
					  langProp.getString("duedate"), 
					  langProp.getString("priority"),
					  langProp.getString("category"), 
					  langProp.getString("done")};
		tableModel = new TaskTableModel(h);
		table = new JTable(tableModel);
		TaskTableRenderer renderer = new TaskTableRenderer();
		//table.setDefaultRenderer(String.class, renderer);
		//table.setDefaultRenderer(Boolean.class, renderer);
		table.getSelectionModel().addListSelectionListener(new TaskSelectionAction(table, tableModel));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		quickAdd = new JTextField(I18.getInstance().properties.getString("quickAdd"));
		quickAdd.setEditable(true);
		
		addButton = new JButton(I18.getInstance().properties.getString("addTask"), new ImageIcon(this.getClass().getResource("/resources/addIcon.png")));
		showToday = new JButton(I18.getInstance().properties.getString("showToday"));
		
		//Set parent controller
		this.pc = PanicController.getInstance();
				
		//Add actions and listeners
		quickAdd.addFocusListener(new QuickAddAction(quickAdd));
		addButton.addActionListener(new AddAction(quickAdd));
		showTodayAction = new ShowTodayAction();
		showToday.addActionListener(showTodayAction);
		//Give everything to the view
		panel.addToView(quickAdd, addButton, pane, showToday);
	}
	
	/**
	 * 
	 * @return The only instance of this controller
	 */
	public static TasksPanelController getInstance() {
		 if (instance == null) {
			 synchronized (TasksPanelController.class){
				 if (instance == null) {
					 instance = new TasksPanelController();
				 }
			 }
		 }
		 return instance;
	}

	/**
	 * 
	 * @return The main view this controller controls
	 */
	public TasksPanelView getView() {
		return panel;
	}
	
	/**
	 * Updates the Today's Tasks popup
	 */
	public void updateTodayView() {
		showTodayAction.update();
		updateShownTasks(TaskManager.getInstance().getTaskList());
		tableModel.fireTableDataChanged();
	}
	
	/**
	 * Adds a Task 
	 * @param t The Task added
	 */
	public void addTask(Task t) {
		pc.newTask(t);
	}
	
	/**
	 * Sets the current category chosen
	 * @param c The Category
	 */
	public void setCategory(Category c) {
		currentCategory = c;
	}
	
	/**
	 * 
	 * @return The current category selected
	 */
	public Category getCategory() {
		return currentCategory;
	}
	
	/**
	 * Updates the shown Tasks in the view
	 * @param tasks The tasks that should be filtered
	 */
	public void updateShownTasks(ArrayList<Task> tasks) {
		ArrayList<Object[]> newData = new ArrayList<Object[]>();
		//Add them in reverse, which puts the most recently added Task first in the view
		for (int i = tasks.size()-1; i >= 0;i--) {
			Task task = tasks.get(i);
			//If no category is selected, all tasks should be shown
			//If a category is selected, the tasks category must match to be added
			if (currentCategory == null || task.getCategory().getName().equals(currentCategory.getName())) {
				Object[] newRow = new Object[5];
				newRow[0] = task;
				newRow[1] = task.getDueDate();
				newRow[2] = task.getPriorityString();
				newRow[3] = task.getCategory().getName();
				newRow[4] = task.isCheck();
				newData.add(newRow);
			}	
		}
		tableModel.changeData(newData);
		tableModel.fireTableDataChanged();
	}
	
	/**
	 * Select the Task t for editing in the right panel
	 * @param t The Task
	 */
	public void taskSelected(Task t) {
		pc.taskSelected(t);
	}
	
	/**
	 * Add a Task
	 * @param t The Task added
	 */
	public void newTask(Task t) {
		pc.newTask(t);
	}
	
	/**
	 * Updates the language throughout the views this controller controls
	 */
	public void updateLanguage() {
		ResourceBundle langProp = I18.getInstance().properties;
		String[] h = {langProp.getString("title"), 
					  langProp.getString("duedate"), 
					  langProp.getString("priority"),
					  langProp.getString("category"), 
					  langProp.getString("done")};
		quickAdd.setText(langProp.getString("quickAdd"));
		addButton.setText(langProp.getString("addTask"));
		showToday.setText(langProp.getString("showToday"));
		TableColumnModel tm = table.getTableHeader().getColumnModel();
		tableModel.setHeader(h);
		for (int i = 0; i <= 4; i++) {
			tm.getColumn(i).setHeaderValue(h[i]);
		}

	}

}
