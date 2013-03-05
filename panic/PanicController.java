package panic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import panic.actions.AboutAction;
import panic.actions.ChangeLanguageAction;
import panic.actions.ExitAction;

import tasks.TasksPanelController;
import tasks.Task;
import tasks.TaskManager;

import categories.Category;
import categories.CategoryPanelController;
import details.DetailsPanelController;

public class PanicController implements Runnable {

	private JMenu file;
	private JMenu edit;
	private JMenu help;
	private JMenu language;
	private JMenuItem about;
	private JMenuItem english;
	private JMenuItem swedish;
	private JMenuItem exit;
	
	private CategoryPanelController leftPanelController;
	private TasksPanelController midPanelController;
	private DetailsPanelController rightPanelController;
	private TaskManager taskManager;
	
	public PanicController(CategoryPanelController leftController, TasksPanelController midController, DetailsPanelController rightController, TaskManager t) {
		this.leftPanelController = leftController;
		this.midPanelController = midController;
		this.rightPanelController = rightController;
		this.taskManager = t;
		taskManager.enable(this);
		rightPanelController.enable(this);
		midPanelController.enable(this);
		leftPanelController.enable(this);
		
		SwingUtilities.invokeLater(this);
		
	}

	@Override
	public void run() {
		initGui();
	}
	
	private void initGui() {
		//Initialization of frame
		JFrame frame = new JFrame("!Panic");
		frame.setContentPane(
				new MainPanel(
						leftPanelController.getView(), 
						midPanelController.getView(),
						rightPanelController.getView()));
		frame.setMinimumSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//Initialization of menubar
		JMenuBar menuBar = new JMenuBar();
		file = new JMenu(I18.getInstance().properties.getString("file"));
		edit = new JMenu(I18.getInstance().properties.getString("edit"));
		help = new JMenu(I18.getInstance().properties.getString("help"));
		about = new JMenuItem(I18.getInstance().properties.getString("about"));
		language = new JMenu(I18.getInstance().properties.getString("changeLang"));
		swedish = new JMenuItem("Svenska");
		english = new JMenuItem("English");
		exit = new JMenuItem(I18.getInstance().properties.getString("exit"));
		language.add(swedish);
		language.add(english);
		edit.add(language);
		file.add(exit);
		help.add(about);

		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		frame.setJMenuBar(menuBar);

		//Add listeners and actions to all components
		ExitAction closeAction = new ExitAction(rightPanelController, frame);
		frame.addWindowListener(closeAction);
		exit.addActionListener(closeAction);
		swedish.addActionListener(new ChangeLanguageAction("swe", this));
		english.addActionListener(new ChangeLanguageAction("eng", this));
		about.addActionListener(new AboutAction(frame));
		
		
		
		//Load save configurations
		int x = 600;
		int y = 600;
		int posX = 20;
		int posY = 20;
		try {
			Properties p = new Properties();
			String saveLocation = System.getProperty("user.home")
					+ "/.TODO-group9/config.properties";
			p.load(new FileInputStream(new File(saveLocation)));
			x = Integer.parseInt(p.getProperty("WindowX"));
			y = Integer.parseInt(p.getProperty("WindowY"));
			posX = Integer.parseInt(p.getProperty("PositionX"));
			posY = Integer.parseInt(p.getProperty("PositionY"));
		} catch (Exception e) {
			System.err.println("Could not load configuration, using default values");
		} finally {
			frame.setSize(new Dimension(x, y));
			frame.setLocation(posX, posY);
			frame.setVisible(true);
		}
		
		midPanelController.updateShownTasks(taskManager.getTaskList());
	}
	
	public void updateLanguage(){
		rightPanelController.updateLanguage();
		midPanelController.getView().updateLanguage();
		file.setText(I18.getInstance().properties.getString("file"));
		edit.setText(I18.getInstance().properties.getString("edit"));
		help.setText(I18.getInstance().properties.getString("help"));
		exit.setText(I18.getInstance().properties.getString("exit"));
		about.setText(I18.getInstance().properties.getString("about"));
		language.setText(I18.getInstance().properties.getString("changeLang"));
	}
	
	public void deleteTask(Task t) {
		taskManager.removeTask(t);
		midPanelController.updateShownTasks(taskManager.getTaskList());
	}
	
	public void updateTask(Task t) {
		taskManager.updateTask(t);
		midPanelController.updateShownTasks(taskManager.getTaskList());
	}
	
	public void newTask(Task t) {
		taskManager.addTask(t);
		System.out.println(taskManager.getTaskList());
		midPanelController.updateShownTasks(taskManager.getTaskList());
	}

	public void taskSelected(Task t) {
		rightPanelController.taskSelected(t);
	}
	
	public ArrayList<Category> getCategories(){
		return taskManager.getCategoryList();
	}
}
