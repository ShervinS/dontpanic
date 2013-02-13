/* Java Swing list test
 * By Carl Ekman
 * 
 * User Interface Programming I
 * Uppsala University
 * 2013-02-06
 * 
 * CheckBoxCellRenderer Class
 */


import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class CheckBoxListRenderer extends JCheckBox implements ListCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
		setEnabled(list.isEnabled());
		setSelected(((CheckBoxListItem)value).isSelected());
		setFont(list.getFont());
		setBackground(list.getBackground());
		setForeground(list.getForeground());
		setText(value.toString());
		return this;
	}
     
 }
