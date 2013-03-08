package tasks;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class TaskTableRenderer extends DefaultTableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table,
												   Object object,
												   boolean isSelected,
												   boolean hasFocus,
												   int row,
												   int column) {
		
		Component cell = super.getTableCellRendererComponent(table, object, isSelected, hasFocus, row, column);
		System.out.println(object.getClass() + " " + column);
		if (row % 2 == 0) {
			cell.setBackground(Color.LIGHT_GRAY);
		}
		else {
			cell.setBackground(Color.gray);
		}
		return cell;
	}
}
