package tasks;

import java.util.ArrayList;

import panic.XML;
import categories.Category;

/** Manages the list of tasks and categorys. 
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
	
	public int getNumberOfTasksForCategory(Category category){
		int count = 0;
		for(Task t : taskList){
			if(t.getCategory().getName().equals(category.getName())){
				count++;
			}
		}
		return count;
	}
	public void addCategory(Category category){
		xml.addCategory(category);
		categoryList.add(category);
	}
}
