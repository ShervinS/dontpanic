package Panic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** Contains the data belonging to each task.
 * @author Johannes Henriksson 
 * 
 */
public class Task {
	private String title,description,category,adDate,dueDate;
	private int priority, id;
	private boolean check;
	private TaskItemView view;



	public Task(String title,String description,String category, int priority,String adDate,String dueDate, boolean check){
		this.title = title;
		this.description = description;
		this.category = category;
		this.priority = priority;
		this.adDate = adDate;
		this.dueDate = dueDate;
		this.check = check;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		dueDate = dateFormat.format(date);
		view = new TaskItemView(this);
	}

	public Task(String title,String description,String category, int priority,String dueDate, boolean check){
		this.title = title;
		this.description = description;
		this.category = category;
		this.priority = priority;
		this.dueDate = dueDate;
		this.check = check;
		Calendar c = Calendar.getInstance();
		String myString = DateFormat.getDateTimeInstance().format(c.getTime());
		dueDate = myString;
		view = new TaskItemView(this);
	}


	public TaskItemView getView() {
		return view;
	}
	

	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getAdDate() {
		return adDate;
	}



	public void setAdDate(String adDate) {
		this.adDate = adDate;
	}



	public String getDueDate() {
		return dueDate;
	}



	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}



	public int getPriority() {
		return priority;
	}



	public void setPriority(int priority) {
		this.priority = priority;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public boolean isCheck() {
		return check;
	}



	public void setCheck(boolean check) {
		this.check = check;
	}



}
