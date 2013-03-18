package categories;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class ColorComboBoxRenderer extends JLabel
implements ListCellRenderer {
	JButton button;
	
	public ColorComboBoxRenderer() {
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		this.button = new JButton("Test");
		this.button.setBackground(Color.CYAN);
		add(button);
	}

	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		
		/*if (isSelected) {
			setBackground(new Color(0, 200, 0));
		}
		if (cellHasFocus) {
			setBackground(new Color(200,0,0));
		}*/
		
		
		 if (isSelected) {
	            //setBackground(list.getSelectionBackground());
	            //setForeground(list.getSelectionForeground());
	            Color color = (Color) value;
	    		setBackground(color);
	        } else {
	    		Color color = (Color) value;
	    		setBackground(color);
	        }
		 if	(cellHasFocus) {
			 
		 }
		setText(" ");
		
		//Set the icon and text.  If icon was null, say so.
		/*String pet = petStrings[selectedIndex];
		setIcon(icon);
		if (icon != null) {
			setText(pet);
			setFont(list.getFont());
		} else {
			
		}*/
	// 	Color color = colors[index];
		return this;
	}
	  public void setItem(Object newValue) {
		    if (newValue instanceof Color) {
		      Color color = (Color) newValue;
		   //   editor.setBackground(color);
		    } else {
		      try {
		        Color color = Color.decode(newValue.toString());
		     //   editor.setBackground(color);
		      } catch (NumberFormatException e) {
		      }
		    }
		  }
	
}
