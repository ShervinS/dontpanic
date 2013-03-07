package panic.TodayTasks;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import panic.PanicController;
import tasks.Task;
import tasks.TaskManager;


public class ShowTodayAction extends AbstractAction implements Runnable {

	
	private TasksTodayRenderer renderer;
	private JList tasks;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initUi();
			}
		});
	}

	@Override
	public void run() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initUi();
			}
		});
	}
	
	
	private ArrayList<Task> getTodayTasks(ArrayList<Task> taskList) {
		String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		ArrayList<Task> today = new ArrayList<Task>();
		if (day.length() < 2) {
			day = "0" + day;
		}
		if (month.length() < 2) {
			month = "0" + month;
		}
		for (Task i : taskList) {
			if (i.getDueDate().equalsIgnoreCase(year+month+day) && !i.isCheck()) {
				today.add(i);
			}
		}
		return today;
	}
	
	
	public void update() {
		DefaultListModel model = new DefaultListModel();
		for (Task i : getTodayTasks(TaskManager.getInstance().getTaskList())) {
			model.addElement(i);
		}
		tasks.setModel(model);
	}
	
	
	private void initUi() {
		
		ArrayList<Task> todaysTasks = getTodayTasks(TaskManager.getInstance().getTaskList());
		if (todaysTasks.size() == 0) {
			JOptionPane.showMessageDialog(PanicController.getInstance().getFrame(), "No Tasks Today");
		}
		else {
			JFrame today = new JFrame();
			JPanel panel = new JPanel();
			today.setTitle("Today's Tasks");
			tasks = new JList(todaysTasks.toArray());
			renderer = new TasksTodayRenderer();
			tasks.setCellRenderer(renderer);
			tasks.addMouseListener(new TodayTasksMouseAdapter());
			panel.add(tasks);
			JFrame mainFrame = PanicController.getInstance().getFrame();
			today.setContentPane(panel);
			today.pack();
			today.setLocation(mainFrame.getLocation().x + mainFrame.getWidth()/2 - today.getWidth()/2, 
					  mainFrame.getLocation().y + mainFrame.getHeight()/2 - today.getHeight()/2);
			today.setVisible(true);
		}
		
	}

}
