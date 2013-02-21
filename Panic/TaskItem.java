package Panic;


/**
 * DEPRECATED
 * @author joseph
 *
 */
public class TaskItem {

	private String label;
	private boolean isSelected = false;
	
	public TaskItem(String s) {
		this.label = s;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean b) {
		this.isSelected = b;
	}
	
	public String toString() {
		return label;
	}
	
}
