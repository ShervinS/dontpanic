package panic.TodayTasks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import panic.PanicController;
import tasks.Task;
import tasks.TasksPanelController;

/**
 * Mouse adapter for use with a popup showing Today's Tasks
 * 
 * @author joseph
 *
 */
public class TodayTasksMouseAdapter extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent e) {
		//Only do something if right-clicked (Button3)
		if(e.getButton() == MouseEvent.BUTTON3 && e.getComponent() instanceof JList && e.isPopupTrigger()) {
			final JList l = (JList) e.getComponent();
			//First, select the object that is where the mouse was clicked
			l.setSelectedIndex(l.locationToIndex(e.getPoint()));
			JPopupMenu menu = new JPopupMenu();
			JMenuItem done = new JMenuItem("Mark As Done");
			JMenuItem show = new JMenuItem("Show In Main App");
			done.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//Sets the Task as done, and then updates the popupview
					Task t = (Task) l.getSelectedValue();
					t.setCheck(true);
					TasksPanelController.getInstance().updateTodayView();
					PanicController.getInstance().updateTask(t);
				}
			});
			show.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//Will select the Task which has been clicked in the main
					//application
					Task t = (Task) l.getSelectedValue();
					PanicController.getInstance().taskSelected(t);
				}
			});
			menu.add(done);
			menu.add(show);
			//Show the menu where the mouse is
		    menu.show(e.getComponent(), e.getX(), e.getY());
		}
	}
	
}
