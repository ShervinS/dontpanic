package tasks;

import java.util.ArrayList;
import java.util.Calendar;

import panic.XML;
import categories.Category;

/** Manages the list of tasks and handles writing and reading from the XML database. 
 * 
 * @author Johannes Henriksson
 *
 */
public class TaskManager {
	private ArrayList<Task> taskList;
	private ArrayList<Category> categoryList;
	private XML xml;
	private static TaskManager instance;
	
	private TaskManager() {
		xml = new XML();
		taskList = xml.getTasks();
		categoryList = xml.getCategorys();
	}
	
	public static TaskManager getInstance() {
		 if (instance == null) {
			 synchronized (TaskManager.class){
				 if (instance == null) {
					 instance = new TaskManager();
				 }
			 }
		 }
		 return instance;
	}

	
	public ArrayList<Task> getTodayTasks() {
		String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		ArrayList<Task> today = new ArrayList<Task>();
		if (day.length() < 2) {
			day = "0" + day;
		}
		if (month.length() < 2) {
			month = "0" + month;
		}
		for (Task i : taskList) {
			if (i.getDueDate().equalsIgnoreCase(year+month+day)) {
				today.add(i);
			}
		}
		return today;
	}
	
	
	public void addTask(Task t){
		//t.setId(xml.getId());
		xml.addTask(t);
		taskList.add(t);
		//t.getView().addMouseListener(new TaskSelectionListener(t, pc));
	}
	
	public void readTask(Task t) {
		xml.addTask(t);
		taskList.add(t);
	}
	
	public void removeTask(Task t){
		taskList.remove(t);
		xml.removeTask(t);
	}
	
	public void updateTask(Task t){
		taskList.remove(t);
		readTask(t);
	}
	
	public ArrayList<Task> getTaskList(){
		return taskList;
	}
	
	public ArrayList<Category> getCategoryList(){
		return categoryList;
	}
}
