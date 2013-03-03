package details.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import details.DetailsPanelController;

public class CategoriesAction extends AbstractAction {

	
	private DetailsPanelController pc;
	private JComboBox<String> categories;
	
	public CategoriesAction(DetailsPanelController pc, JComboBox<String> categories) {
		this.pc = pc;
		this.categories = categories;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) categories.getSelectedItem();
		System.out.println("Category: " + s);
	}

}
