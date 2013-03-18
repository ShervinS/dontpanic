package details.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import panic.I18;
import details.DetailsPanelController;

/**
 * Action for use with the detailed view.
 * Will mark the currently selected Task as done.
 * @author joseph
 *
 */
public class MarkAsDoneAction implements ActionListener {

	private JButton button;
	
	public MarkAsDoneAction(JButton button) {
		this.button = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		DetailsPanelController pc = DetailsPanelController.getInstance();
		System.out.println(pc.getCurrentTask());
		if (pc.getCurrentTask() != null) {
			pc.getCurrentTask().setCheck(!pc.getCurrentTask().isCheck());
			pc.updateTask(pc.getCurrentTask());
			button.setText(pc.getCurrentTask().isCheck() ? I18.getInstance().properties.getString("markUnDone") : I18.getInstance().properties.getString("markDone"));
		}
	}

}
