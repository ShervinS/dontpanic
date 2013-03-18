package panic.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Action to show the "About" window
 * @author joseph
 *
 */
public class AboutAction implements ActionListener {

	private JFrame frame;
	
	public AboutAction(JFrame frame) {
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame,"Version: 1.0", "", JOptionPane.INFORMATION_MESSAGE);
	}

}
