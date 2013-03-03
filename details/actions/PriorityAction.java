package details.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import details.DetailsPanelController;

public class PriorityAction implements ListSelectionListener {

	
	private DetailsPanelController pc;
	private JList priority;
	
	public PriorityAction(DetailsPanelController pc, JList priority) {
		this.pc = pc;
		this.priority = priority;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (pc.getCurrentTask() != null) {
			pc.getCurrentTask().setPriority(priority.getAnchorSelectionIndex()+1);
			pc.updateTask(pc.getCurrentTask());
		}
		
	}
}
