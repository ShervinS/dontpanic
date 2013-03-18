package categories.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import categories.Category;
import categories.CategoryPanelController;


import panic.I18;
import panic.PanicController;

import tasks.Task;
import tasks.TasksPanelController;

public class AddCategoryAction extends AbstractAction {

	private AddCategoryFrame addCategoryFrame;
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox colorPicker;
	private PanicController pc;
	private CategoryPanelController cpc;
	
	public AddCategoryAction(PanicController pc,CategoryPanelController cpc, JComboBox colorPicker, JTextField textField) {
		this.pc = pc;
		this.cpc = cpc;
		this.colorPicker = colorPicker;
		this.textField = textField;
		addCategoryFrame = new AddCategoryFrame(I18.getInstance().properties.getString("colorPickerFrameTitle"),this);
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		this.addCategory((Color)this.colorPicker.getSelectedItem(),this.textField.getText());
		this.cpc.updateGUI();
	}
	
	public void updateLanguage(){
		addCategoryFrame.updateLanguage();
	}
	
	public void addCategory(Color color, String name){
		pc.addCategory(new Category(name,color));
	}
	
	public String getCategoryFieldText(){
		return cpc.getCategoryFieldText();
	}

}