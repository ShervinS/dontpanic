package categories;
/**
 * The panel component for the category part of the gui, 
 * contains the swing components.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import panic.ThemeLoader;

public class CategoryPanel extends JPanel {
	private ThemeLoader tl;
	private static final int NEWCATEGORY = 0;
	private static final int CATEGORIES = 1;
	private static final int CLOCKVIEW = 2;
	private static final int PANEL_DEFAULT_WIDTH = 240;
	private static final long serialVersionUID = 1L;
	private GridBagConstraints c;

	public CategoryPanel () {
		super();
		tl = new ThemeLoader();
		setPreferredSize(new Dimension(PANEL_DEFAULT_WIDTH,0));
		setBackground(tl.getColor("white"));
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
	}
	
	public void addCategoryTextField(JTextField textfield) {
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.9;
		c.gridwidth = 1;
		add(textfield, c);
	}
	
	public void addColorPicker(JComboBox colorPicker) {
		c.gridx = 1;
		c.weightx = 0.1;
		add(colorPicker,c);
	}
	
	public void addNewCategoryButton(JComponent addButton) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = NEWCATEGORY;
		
		c.weighty = 0.0;
		c.weightx = 0.1;
		add(addButton, c);
	}
	
	public void addCategoriesScrollView(JScrollPane scrollView) {
		c.weighty = 1.0;
		c.gridy = CATEGORIES;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 3;
		c.gridx = 0;
		add(scrollView, c);
	}
	
	public void addClockView(ClockComponent clockView){
		c.weighty = 0.1;
		c.gridy = CLOCKVIEW;
		c.gridwidth = 3;
		c.gridx = 0;
		add(clockView, c);
	}
}
