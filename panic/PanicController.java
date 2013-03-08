package panic;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;

import panic.TodayTasks.ShowTodayAction;
import panic.actions.AboutAction;
import panic.actions.ChangeLanguageAction;
import panic.actions.ExitAction;
import tasks.Task;
import tasks.TaskManager;
import tasks.TasksPanelController;
import categories.Category;
import categories.CategoryPanelController;
import details.DetailsPanelController;

public class PanicController {

	private JMenu file;
	private JMenu edit;
	private JMenu help;
	private JMenu language;
	private JMenuItem about;
	private JMenuItem english;
	private JMenuItem swedish;
	private JMenuItem exit;
	
	private static PanicController instance;
	
	private CategoryPanelController leftPanelController;
	private TasksPanelController midPanelController;
	private DetailsPanelController rightPanelController;
	private TaskManager taskManager;
	private JFrame frame;
	
	
	private PanicController() {
		
	}
	
	public void start() {
		this.taskManager = TaskManager.getInstance();
		this.leftPanelController = CategoryPanelController.getInstance();
		this.midPanelController = TasksPanelController.getInstance();
		this.rightPanelController = DetailsPanelController.getInstance();
		
		//We want to show todays tasks at some time in the morning 
		//(at the moment hard coded to 8 in the morning).
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		Calendar cal = Calendar.getInstance();
		int timeToRun = 8*60 - cal.get(Calendar.HOUR_OF_DAY)*60 + cal.get(Calendar.MINUTE);
		if (timeToRun < 0)
			timeToRun += 24*60;
	    service.schedule(new ShowTodayAction(), timeToRun, TimeUnit.MINUTES);  
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGui();
			}
		});
	}
	
	public static PanicController getInstance() {
		 if (instance == null) {
			 synchronized (PanicController.class){
				 if (instance == null) {
					 instance = new PanicController();
				 }
			 }
		 }
		 return instance;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	private void initGui() {
		//Initialization of frame
		frame = new JFrame("!Panic");
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
		ExitAction closeAction = new ExitAction(frame);
		frame.addWindowListener(closeAction);
		exit.addActionListener(closeAction);
		swedish.addActionListener(new ChangeLanguageAction("swe"));
		english.addActionListener(new ChangeLanguageAction("eng"));
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
		midPanelController.updateLanguage();
		leftPanelController.updateLanguage();
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
	
	public void addCategory(Category category) {
			System.out.println("PanicController: Adding category");
			taskManager.addCategory(category);
	}
}
