package categories;
/**
 * The table model for the category part of the gui.
 * 
 */
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class CategoriesModel extends AbstractTableModel {
		
	private ArrayList<Category> categories;	

	public boolean isCellEditable(int row, int col)
	        { return false; }
	    
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
		System.out.println(categories);
	}
	
	public Category getCategoryAtIndex(int index){
		return categories.get(index);
	}
	
	@Override
	public int getColumnCount() {
		return 1;
	}
	
	public String getColumnName(int columnIndex) {
		return null;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.categories.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if (arg1 == 0) {
			Category category = this.categories.get(arg0);
			return "  " + category.getName();
		} else if (arg1 == 1) {
				return 3;
		} else if (arg1 == 2) {
				return null;
		}
		return null;
	}

}
