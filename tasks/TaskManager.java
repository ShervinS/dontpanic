package tasks;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import panic.PanicController;
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
	private String XMLPath;
	private XML xml;
	private PanicController pc;
	
	public TaskManager(){
		xml = new XML();
		taskList = xml.getTasks();
		categoryList = xml.getCategorys();
	}

	public void setController(PanicController pc) {
		this.pc = pc;
		for (Task i : taskList) {
			i.getView().addMouseListener(new TaskSelectionListener(i, pc));
		}
	}

	
	
	public void addTask(Task t){
		//t.setId(xml.getId());
		xml.addTask(t);
		taskList.add(t);
		t.getView().addMouseListener(new TaskSelectionListener(t, pc));
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