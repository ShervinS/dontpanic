package panic.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Action to show the "About" window
 * @author joseph
 *
 */
public class AboutAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	public AboutAction(JFrame frame) {
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame,"Version: 1.0");
	}

}
