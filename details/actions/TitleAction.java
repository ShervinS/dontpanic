package details.actions;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import details.DetailsPanelController;

public class TitleAction implements DocumentListener {

	private JTextField title;
	private JList priority;
	
	public TitleAction(JTextField title) {
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
		DetailsPanelController pc = DetailsPanelController.getInstance();
		if (pc.getCurrentTask() != null) {
			pc.getCurrentTask().setTitle(title.getText());
			pc.updateTask(pc.getCurrentTask());
		}
	}

}
