package details.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import details.DetailsPanelController;

public class DescriptionAction implements DocumentListener {

	
	private DetailsPanelController pc;
	private JTextArea description;
	
	public DescriptionAction(DetailsPanelController pc, JTextArea description) {
		this.pc = pc;
		this.description = description;
	}
	

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateTask();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateTask();
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateTask();
		
	}
	
	public void updateTask() {
		if (pc.getCurrentTask() != null) {
			pc.getCurrentTask().setDescription(description.getText());
			pc.updateTask(pc.getCurrentTask());
		}
	}

}
