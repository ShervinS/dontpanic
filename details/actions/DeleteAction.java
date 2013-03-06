package details.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import details.DetailsPanelController;

public class DeleteAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DetailsPanelController pc = DetailsPanelController.getInstance();
		if (pc.getCurrentTask() != null) {
			pc.deleteTask(pc.getCurrentTask());
		}
	}

}
