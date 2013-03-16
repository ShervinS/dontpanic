package details.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import details.DetailsPanelController;

/**
 * An action for use with the detailed RightPanel.
 * The action will close the RightPanel.
 * @author joseph
 *
 */
public class DoneAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DetailsPanelController pc = DetailsPanelController.getInstance();
		if (pc.getCurrentTask() != null) {
			pc.setRightPanel(false);
		}
	}

}
