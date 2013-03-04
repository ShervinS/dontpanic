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

public class DoneAction extends AbstractAction {

	
	private DetailsPanelController pc;
	
	public DoneAction(DetailsPanelController pc) {
		this.pc = pc;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (pc.getCurrentTask() != null) {
			pc.setRightPanel(false);
		}
	}

}
