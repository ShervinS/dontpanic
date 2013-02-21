package Panic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DetailedViewListener implements DocumentListener, ListSelectionListener, ActionListener {

	
	private RightPanelController pc;
	private JTextField title;
	private JTextArea description;
	private JList priority;
	
	public DetailedViewListener(RightPanelController pc, JTextField title, JTextArea description, JList priority) {
		this.pc = pc;
		this.title = title;
		this.description = description;
		this.priority = priority;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (pc.getCurrentTask() != null) {
			pc.getCurrentTask().setPriority(priority.getAnchorSelectionIndex()+1);
			pc.updateTask(pc.getCurrentTask());
		}
		
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
			pc.getCurrentTask().setDescription(description.getText());
			pc.updateTask(pc.getCurrentTask());
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (pc.getCurrentTask() != null) {
			pc.deleteTask(pc.getCurrentTask());
		}
	}

}
