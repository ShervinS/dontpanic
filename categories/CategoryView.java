package categories;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class CategoryView extends JLabel
implements TableCellRenderer {

	public CategoryView(boolean isBordered) {
		//this.isBordered = isBordered;
		setOpaque(true); //MUST do this for background to show up.
	}

	public Component getTableCellRendererComponent(
			JTable table, Object color,
			boolean isSelected, boolean hasFocus,
			int row, int column) {
		Color newColor = (Color)color;
		setBackground(newColor);

		if (isSelected) {


			return this;
		}
		return this;
	}
}