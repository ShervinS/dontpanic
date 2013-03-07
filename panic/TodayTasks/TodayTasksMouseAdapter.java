package panic.TodayTasks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import tasks.Task;
import tasks.TasksPanelController;

public class TodayTasksMouseAdapter extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3 && e.getComponent() instanceof JList && e.isPopupTrigger()) {
			final JList l = (JList) e.getComponent();
			l.setSelectedIndex(l.locationToIndex(e.getPoint()));
			JPopupMenu menu = new JPopupMenu();
			JMenuItem done = new JMenuItem("Mark As Done");
			done.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Task t = (Task) l.getSelectedValue();
					t.setCheck(true);
					TasksPanelController.getInstance().updateTodayView();
				}
			});
			menu.add(done);
		    menu.show(e.getComponent(), e.getX(), e.getY());
		}
	}
	
}
