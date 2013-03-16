package details;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import panic.I18;
import panic.PanicController;
import tasks.Task;
import categories.Category;

import com.toedter.calendar.JDateChooser;

import details.actions.CategoriesAction;
import details.actions.DateAction;
import details.actions.DeleteAction;
import details.actions.DescriptionAction;
import details.actions.DoneAction;
import details.actions.MarkAsDoneAction;
import details.actions.PriorityAction;
import details.actions.TitleAction;
//import details.actions.MarkAsDoneAction;

/**
 * The RightPanelController controls the right part of the !Panic ToDo-app
 * In this part you can add more details to your task.
 * A window will slide in and out when it's needed.
 * This class implements the Singleton pattern
 * @author joseph
 *
 */
public class DetailsPanelController  {
	
	private String[] categoryStringArray;
	
	private PanicController pc;
	
	private DetailsPanelView panel;
	
	private JLabel titleLabel;
	private JTextField title;
	
	private JLabel descriptionLabel;
	private JTextArea description;
	
	private JLabel priorityLabel;
	private JList priority;
	
	private JLabel dateLabel;
	private JDateChooser date;
	
	private JLabel categoriesLabel;
	private JComboBox categories;
	
	private String[] s;
	
	private static DetailsPanelController instance;
	
	private Task currentTask;
	
	private JButton deleteButton;
	private JButton doneButton;
	private JButton markDoneButton;
	
	/**
	 * Constructor for the DetailsPanelController
	 * Will initialize all the views.
	 */
	public DetailsPanelController() {
		//Get language
		I18.getInstance().setLocale("swe");
		
		//Initialize the RightPanel, which all views will be painted on
		panel = new DetailsPanelView();
		
		//Initialization of title and its label
		titleLabel = new JLabel(I18.getInstance().properties.getString("title"));
		title = new JTextField();
		
		//Initialization of description and its label
		descriptionLabel = new JLabel(I18.getInstance().properties.getString("description"));
		description = new JTextArea();
		
		//Initialization of priority and its label
		priorityLabel = new JLabel(I18.getInstance().properties.getString("priority"));
		s = new String[3];
		s[0] = I18.getInstance().properties.getString("low");
		s[1] = I18.getInstance().properties.getString("medium");
		s[2] = I18.getInstance().properties.getString("high");
		priority = new JList(s);
		priority.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Initialization of date and its label
		dateLabel = new JLabel(I18.getInstance().properties.getString("date"));
		date = new JDateChooser();
		
		//Initialization of categories and its label
		categoriesLabel = new JLabel(I18.getInstance().properties.getString("categories"));
		categories = new JComboBox();	
		
		//Initialization of the delete button
		deleteButton = new JButton(I18.getInstance().properties.getString("delete"), new ImageIcon(this.getClass().getResource("/resources/xIcon.png")));
		
		//Initialization of the done-button
		doneButton = new JButton(I18.getInstance().properties.getString("done"), new ImageIcon(this.getClass().getResource("/resources/okIcon.png")));

		//Initialization of the button that marks the task as done
		markDoneButton = new JButton();
		
		//Set parent controller
		this.pc = PanicController.getInstance();
		categoryStringArray = new String[1];
		categoryStringArray = fixCategoryNameFormat( pc.getCategories()).toArray(categoryStringArray);
		for(String name:categoryStringArray){
			categories.addItem(name);
		}
		categories.addItem("");
		
		//Add actions to all the components
		title.getDocument().addDocumentListener(new TitleAction(title));
		description.getDocument().addDocumentListener(new DescriptionAction(description));
		priority.addListSelectionListener(new PriorityAction(priority));
		categories.addActionListener(new CategoriesAction(categories));
		date.addPropertyChangeListener(new DateAction(date));
		deleteButton.addActionListener(new DeleteAction());
		doneButton.addActionListener(new DoneAction());
		markDoneButton.addActionListener(new MarkAsDoneAction(markDoneButton));
		
		
		//Create some constraints for adding to the view
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.7;
		c.insets = new Insets(0, 5, 5, 5);
		c.gridy = 0;
		//Add every view component to the panel
		panel.add(5, c, titleLabel);
		panel.add(5, c, title);
		panel.add(10, c, descriptionLabel);
		c.weighty = 0.5;
		panel.add(5, c, description);
		c.weighty = 0;
		panel.add(10, c, dateLabel);
		panel.add(5, c, (JComponent) date);
		panel.add(10, c, categoriesLabel);
		panel.add(5, c, categories);
		panel.add(10, c, priorityLabel);
		panel.add(5, c, priority);
		panel.add(10, c, markDoneButton);
		panel.pad(c);
		panel.add(10, c, doneButton);
		panel.add(10, c, deleteButton);
	}
	
	/**
	 * 
	 * @return The only instance of this class
	 */
	public static DetailsPanelController getInstance() {
		 if (instance == null) {
			 synchronized (DetailsPanelController.class){
				 if (instance == null) {
					 instance = new DetailsPanelController();
				 }
			 }
		 }
		 return instance;
	}
	
	/**
	 * Formats the array of Category to an array of String representing the categories
	 * @param cats The array of Category
	 * @return The array of String
	 */
	private ArrayList<String> fixCategoryNameFormat(ArrayList<Category> cats){
		ArrayList<String> names = new ArrayList<String>();
		for(Category cat : cats){
			names.add(cat.getName());
		}
		return names;
	}
	
	/**
	 * 
	 * @return The view this controller controls
	 */
	public DetailsPanelView getView() {
		return panel;
	}
	
	
	/**
	 * Will slide out and or in the panel where you add more details.
	 * @param b Slides out if True, slides in if False
	 */
	public void setRightPanel(boolean b) {
		if (!b) {
			//If the panel is slid in, no task should be selected
			currentTask = null;
		}
		panel.showPanel(b);
	}
	
	/**
	 * 
	 * @return The status of the right panel, True if it is showing, False otherwise.
	 */
	public boolean isOpen() {
		return panel.isOpen();
	}
	
	
	/**
	 * This will return the Task which the right panel is currently adding 
	 * or modifying details on.
	 * @return The currently selected Task
	 */
	public Task getCurrentTask() {
		return currentTask;
	}

	
	/**
	 * Selects a Task, which the user will be able to modify using controls on 
	 * the right panel.
	 * @param t The Task to modify.
	 */
	public void taskSelected(Task t) {
		//First set the currentTask to null, ensuring no further changes
		//will be made during the "transition period"
		currentTask = null;
		
		//Use the Task t information to show on the right panel
		
		//Get title from the Task
		title.setText(t.getTitle());
		
		//Get description from the Task
		description.setText(t.getDescription());
		
		//Select the correct priority (-1 because indexing starts from 0)
		priority.setSelectedIndex(t.getPriority()-1);
		
		//Get the date, and display it correctly
		String dateString = t.getDueDate();
		if (dateString.length() < 8) {
			date.setCalendar(null);
		}
		else {
			int year = Integer.parseInt(dateString.substring(0, 4));
			int month = Integer.parseInt(dateString.substring(4, 6));
			int day = Integer.parseInt(dateString.substring(6, 8));
			Calendar c = Calendar.getInstance();
			c.set(year, month-1, day);
			date.setDate(c.getTime());
		}
		
		//Get "done"-state
		markDoneButton.setText(t.isCheck() ? I18.getInstance().properties.getString("markUnDone") : I18.getInstance().properties.getString("markDone"));
		
		//Fix categories to choose from
		ArrayList<String> cats = fixCategoryNameFormat(PanicController.getInstance().getCategories());
		cats.add(0, "");
		String[] catStrings = new String[1];
		catStrings = cats.toArray(catStrings);
		DefaultComboBoxModel newModel = new DefaultComboBoxModel(catStrings);
		categories.setModel(newModel);
		
		//And choose the correct category 
		int i;
		for (i = 0; i < categories.getItemCount(); i++) {
			String s = (String) categories.getItemAt(i);
			if (t.getCategory().getName().equals(s)) {
				break;
			}
		}
		categories.setSelectedIndex(i);
		
		//Lastly, make the currentTask t and show the right panel
		currentTask = t;
		setRightPanel(true);
	}
	
	/**
	 * Whenever a user modifies a task with something, this is called which will
	 * delegate the command to the parent controller.
	 * @param t Task which has been modified.
	 */
	public void updateTask(Task t) {
		pc.updateTask(t);
	}
	
	/**
	 * Whenever a user deletes a task, this is called which will delegate
	 * the command to the parent controller and also close the detailed view.
	 * @param t The task to be deleted
	 */
	public void deleteTask(Task t) {
		pc.deleteTask(t);
		panel.setOpen(false);
		panel.revalidate();
		panel.repaint();
	}
	
	/**
	 * Called when a user changes the language, makes sure that all
	 * views this controller controls changes their values to the correct
	 * language.
	 */
	public void updateLanguage() {
		titleLabel.setText(I18.getInstance().properties.getString("title"));
		descriptionLabel.setText(I18.getInstance().properties.getString("description"));
		priorityLabel.setText(I18.getInstance().properties.getString("priority"));
		categoriesLabel.setText(I18.getInstance().properties.getString("categories"));
		deleteButton.setText(I18.getInstance().properties.getString("delete"));
		doneButton.setText(I18.getInstance().properties.getString("done"));
		dateLabel.setText(I18.getInstance().properties.getString("date"));
		s[0] = I18.getInstance().properties.getString("low");
		s[1] = I18.getInstance().properties.getString("medium");
		s[2] = I18.getInstance().properties.getString("high");
		if (currentTask != null) {
			markDoneButton.setText(currentTask.isCheck() ? I18.getInstance().properties.getString("markUnDone") : I18.getInstance().properties.getString("markDone"));
		}
	}
	
}
