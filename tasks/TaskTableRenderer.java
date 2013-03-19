package tasks;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import categories.Category;

/**
 * Custom renderer for table that shows Tasks
 * @author joseph
 *
 */
public class TaskTableRenderer extends DefaultTableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table,
												   Object object,
												   boolean isSelected,
												   boolean hasFocus,
												   int row,
												   int column) {
		
		//Get the cell from the default getTableCellRendererComponent
		Component cell = super.getTableCellRendererComponent(table, object, isSelected, hasFocus, row, column);
		//Change background depending on which row we are on
		System.out.println(column + "" + row);
		if (column ==  3) {
			System.out.println("Category detected");
			cell.setBackground(((Category) object).getColor());
		}
		else if (row % 2 == 0) {
			cell.setBackground(Color.LIGHT_GRAY);
		}
		else {
			cell.setBackground(Color.gray);
		}
		return cell;
	}
}
