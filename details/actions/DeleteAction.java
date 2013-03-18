package details.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import details.DetailsPanelController;

/**
 * Action for use with a delete Task button
 * @author joseph
 *
 */
public class DeleteAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DetailsPanelController pc = DetailsPanelController.getInstance();
		if (pc.getCurrentTask() != null) {
			pc.deleteTask(pc.getCurrentTask());
		}
	}

}
