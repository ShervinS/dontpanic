package details.actions;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import details.DetailsPanelController;

/**
 * Action for use with a description textfield for a Task.
 * @author joseph
 *
 */
public class DescriptionAction implements DocumentListener {

	private JTextArea description;
	
	/**
	 * Constructor
	 * @param description JTextArea to get current description from
	 */
	public DescriptionAction(JTextArea description) {
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
	
	private void updateTask() {
		DetailsPanelController pc = DetailsPanelController.getInstance();
		if (pc.getCurrentTask() != null) {
			pc.getCurrentTask().setDescription(description.getText());
			pc.updateTask(pc.getCurrentTask());
		}
	}

}
