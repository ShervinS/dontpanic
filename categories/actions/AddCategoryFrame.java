package categories.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import panic.I18;

public class AddCategoryFrame extends JFrame {
	private JPanel pane;
	private JButton doneButton;
	private JTextField categoryNameField;
	private JColorChooser colorChooser;
	private AddCategoryAction caller;
	
	public AddCategoryFrame(String name,AddCategoryAction caller){
		super(name);
		this.caller = caller;
		doneButton = new JButton(I18.getInstance().properties.getString("done"));
	    colorChooser = new JColorChooser();
	    colorChooser.setPreviewPanel(new JPanel());
	   
	    AbstractColorChooserPanel[] oldPanels = colorChooser.getChooserPanels();
	    for (int i=0; i<oldPanels.length; i++) {
	    	
	        String clsName = oldPanels[i].getClass().getName();
	        System.out.println(clsName);
	        if (i == 0) {//remove all choser panel but the first
	        } else{
	        	colorChooser.removeChooserPanel(oldPanels[i]);
	        }
	    }
	    
	    doneButton.addActionListener(new DoneActionListner());
	    
	    pane = new JPanel();
	    pane.add(colorChooser);
	    pane.add(doneButton);
	    getContentPane().add(pane);
	    
	    
	    pack();
	    
		
	}
	
	public void updateLanguage(){
		doneButton.setText(I18.getInstance().properties.getString("done"));
	}
	
	private class DoneActionListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			caller.addCategory(colorChooser.getColor(),caller.getCategoryFieldText());
		}
		
		
	}
}
