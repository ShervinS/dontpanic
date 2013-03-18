package details.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import details.DetailsPanelController;

/**
 * An action for use with the detailed view.
 * The action will close the the detailed view.
 * @author joseph
 *
 */
public class DoneAction implements ActionListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DetailsPanelController pc = DetailsPanelController.getInstance();
		if (pc.getCurrentTask() != null) {
			pc.setRightPanel(false);
		}
	}

}
