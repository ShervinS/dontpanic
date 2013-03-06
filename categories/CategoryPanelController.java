package categories;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;

import panic.PanicController;


public class CategoryPanelController {

	CategoryPanel panel = new CategoryPanel();
	protected PanicController pc;
	ArrayList<Category> categories;
	private static CategoryPanelController instance;
	
	public CategoryPanelController() {
		this.pc = PanicController.getInstance();
	}
	
	
	public CategoryPanel getView() {
		return panel;
	}
	
	
	public void updateGUI(){
		this.categories = pc.getCategories();
	
		//logCategories();
		int i = 0;
		for (Category cat : this.categories) {
			JButton addButton = new JButton(cat.getName());
			addButton.setBackground(cat.getColor());
			addButton.setOpaque(true);
			addButton.setForeground(Color.BLACK);
			
			addButton.setBorderPainted(false);
			addButton.setFocusPainted(true);
			addButton.setContentAreaFilled(true);
			
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.NORTHWEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = i;
			c.weightx = 1;
			c.weighty = 0;
			c.insets = new Insets(0, 0, 0, 0);
			
			this.panel.add(addButton, c);
			i++;
		}
		
	}
	
	public static CategoryPanelController getInstance() {
		 if (instance == null) {
			 synchronized (CategoryPanelController.class){
				 if (instance == null) {
					 instance = new CategoryPanelController();
				 }
			 }
		 }
		 return instance;
	}
	
	public void logCategories() {
		for (Category cat : this.categories)
		    System.out.println(cat.getName());
	}
}
