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

public class TitleAction implements DocumentListener {

	
	private DetailsPanelController pc;
	private JTextField title;
	private JTextArea description;
	private JList priority;
	
	public TitleAction(DetailsPanelController pc, JTextField title) {
		this.pc = pc;
		this.title = title;
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
			pc.getCurrentTask().setTitle(title.getText());
			pc.updateTask(pc.getCurrentTask());
		}
	}

}
