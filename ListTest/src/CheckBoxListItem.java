/* Java Swing list test
 * By Carl Ekman
 * 
 * User Interface Programming I
 * Uppsala University
 * 2013-02-06
 * 
 * CheckBoxCellRenderer Class
 */


public class CheckBoxListItem {

	private String label;
	private boolean isSelected = false;
	
	public CheckBoxListItem(String s) {
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
