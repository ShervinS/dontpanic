package panic;

import java.util.ArrayList;

import tasks.Task;

import categories.Category;
/**
 * 
 * @author Johannes Henriksson
 *
 */
public class Filter {
	public ArrayList<Task> filter(ArrayList<Task> tasks, ArrayList<Category> categories){
		ArrayList<Task> filteredList = new ArrayList<Task>();
		for(Task t:tasks){
			for (Category c : categories){
				if(t.getCategory().getName().equals(c.getName()))
					filteredList.add(t);
				
			}
		}
		return filteredList;
	}
}
