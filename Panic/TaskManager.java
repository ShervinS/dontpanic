package Panic;

import java.util.ArrayList;

/** Manages the list of tasks and handles writing and reading from the XML database. 
 * 
 * @author Johannes Henriksson
 *
 */
public class TaskManager {
  private ArrayList<Task> taskList;
  private ArrayList<Category> categoryList;
	private String XMLPath;
	private XML xml;
	
	public TaskManager(){
		xml = new XML();
		taskList = xml.getTasks();
		categoryList = xml.getCategorys();
	}



	
	
	public void addTask(Task t){
		//t.setId(xml.getId());
		xml.addTask(t);
		taskList.add(t);
	}
	
	public void removeTask(Task t){
		taskList.remove(t); //compare med vadå
		//xml.removeTask(t);
	}
	
	public void updateTask(Task t){
		removeTask(t);
		addTask(t);
	}
	
	public ArrayList<Task> getTaskList(){
		return taskList;
	}
}
