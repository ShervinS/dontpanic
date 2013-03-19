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

import panic.I18;
import panic.PanicController;
import tasks.Task;
import tasks.TaskManager;


/**
 * An action to show which tasks are for today and overdue.
 * @author joseph
 *
 */
public class ShowTodayAction extends AbstractAction implements Runnable {

	JFrame today;
	
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
		//We need to check so that the format of the strings are correct.
		if (day.length() < 2) {
			day = "0" + day;
		}
		if (month.length() < 2) {
			month = "0" + month;
		}
		for (Task i : taskList) {
			//If the tasks duedate is the same, or smaller (overdue) it needs to be shown.
			if (!i.getDueDate().equals("") &&
				i.getDueDate().compareTo(year+month+day) <= 0 && 
				!i.isCheck()) {
				today.add(i);
			}
		}
		return today;
	}
	
	
	/**
	 * Update the shown tasks
	 */
	public void update() {
		DefaultListModel model = new DefaultListModel();
		for (Task i : getTodayTasks(TaskManager.getInstance().getTaskList())) {
			model.addElement(i);
		}
		tasks.setModel(model);
	}
	
	
	
	private void initUi() {
		//Get today and overdues tasks
		ArrayList<Task> todaysTasks = getTodayTasks(TaskManager.getInstance().getTaskList());
		//If there are no tasks for today show an error
		if (todaysTasks.size() == 0) {
			JOptionPane.showMessageDialog(PanicController.getInstance().getFrame(), I18.getInstance().properties.getString("noTasksToday"), I18.getInstance().properties.getString("noTasksTodayTitle"), JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			//If there already is a frame showing, remove it and show a new one
			if (today == null) {
				today = new JFrame();
			}
			else {
				today.dispose();
				today = new JFrame();
			}
			today.setTitle(I18.getInstance().properties.getString("todaysTasks"));
			
			//Create a panel to use as contentpane
			JPanel panel = new JPanel();
			
			//Create a view for the tasks
			tasks = new JList(todaysTasks.toArray());
			tasks.setSize(300, 0);
			renderer = new TasksTodayRenderer();
			tasks.setCellRenderer(renderer);
			tasks.addMouseListener(new TodayTasksMouseAdapter());
			//Add it to the contentpane
			panel.add(tasks);
			
			//Get the mainframe for positioning calculations
			JFrame mainFrame = PanicController.getInstance().getFrame();
			
			//Set the contentpane to the JPanel
			today.setContentPane(panel);
			today.pack();
			today.setSize(200,todaysTasks.size()*20 + 30);
			today.setResizable(false);
			
			//Use the mainFrame to calculate position to appear in the middle
			today.setLocation(mainFrame.getLocation().x + mainFrame.getWidth()/2 - today.getWidth()/2, 
					  mainFrame.getLocation().y + mainFrame.getHeight()/2 - today.getHeight()/2);
			today.setVisible(true);
			//Lastly, make sure you get focus
			today.requestFocus();
		}
		
	}

}
