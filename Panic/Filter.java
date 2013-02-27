package Panic;

import java.util.ArrayList;
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
