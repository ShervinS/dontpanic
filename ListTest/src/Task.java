package todo;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/** Contains the data belonging to each task.
 * @author Johannes Henriksson 
 * 
 */
public class Task {
  private String title,description,catagory/*catagory list?*/,id,adDate,dueDate;
	private int priority;
	
	public static void main(String[] args) {
		new Task("","","",4,"");
	}

	Task(String title,String description,String catagory, int priority,String dueDate){
		this.title = title;
		this.description = description;
		this.catagory = catagory;
		this.priority = priority;
		Calendar c = Calendar.getInstance();
		String myString = DateFormat.getDateTimeInstance().format(c.getTime());
		System.out.println(myString);
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the catagory
	 */
	public String getCatagory() {
		return catagory;
	}
	/**
	 * @param catagory the catagory to set
	 */
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param prio the prio to set
	 */
	public void setPrio(int priority) {
		this.priority = priority;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
}
