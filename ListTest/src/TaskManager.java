package todo;

import java.util.ArrayList;

/** Manages the list of tasks and handles writing and reading from the XML database. 
 * 
 * @author Johannes Henriksson
 *
 */
public class TaskManager {
  private ArrayList<Task> taskList;
	private String XMLPath;
	
	public TaskManager(String XMLPath){
		this.XMLPath = XMLPath;
		readAllTasksFromXML();
	}
	/**
	 * 
	 * @param t the task that is to be written to the XML database.
	 * @return return true if successful otherwise returns false
	 */
	private boolean writeTaskToXML(Task t){//might need to catch something
		//TODO
		return true;
	}
	/**
	 * @param id the identification number for the task.
	 * @return the task with the identification number id
	 */
	private Task readTaskFromXML(String id){
		//TODO
		return new Task("","","",0,"");
	}
	/**
	 * 
	 * @param id the id of the task to be updated
	 * @param t the task that will replace the previous task
	 * @return true if successful otherwise returns false
	 */
	private boolean updateTaskInXML(String id, Task t){//might need to catch something
		return true;
	}
	
	
	private void readAllTasksFromXML(){
		
		
	}
	
	public void addTask(Task t){
		taskList.add(t);
		writeTaskToXML(t);
	}
	
	public void removeTask(String id){
		
	}
	
	public ArrayList<Task> getTaskList(){
		return taskList;
	}
}
