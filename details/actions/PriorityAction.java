package details.actions;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import details.DetailsPanelController;

/**
 * Action for used with a priority-choose for a Task
 * @author joseph
 *
 */
public class PriorityAction implements ListSelectionListener {

	
	private JList priority;
	
	/**
	 * Constructor
	 * @param priority JList to get the current priority chosen from
	 */
	public PriorityAction(JList priority) {
		this.priority = priority;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		DetailsPanelController pc = DetailsPanelController.getInstance();
		if (pc.getCurrentTask() != null) {
			pc.getCurrentTask().setPriority(priority.getAnchorSelectionIndex()+1);
			pc.updateTask(pc.getCurrentTask());
		}
		
	}
}
