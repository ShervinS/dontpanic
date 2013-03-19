package categories;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import panic.I18;
import panic.PanicController;

import categories.actions.AddCategoryAction;
import categories.actions.SelectCategoryAction;
import categories.actions.ShowAllCategoriesAction;

import panic.ThemeLoader;


/**
 * The controller for the category part of the gui, contains the communication
 * with the other controllers and the model.
 * 
 * @author Shervin Shoravi
 *
 */
public class CategoryPanelController {

	private CategoryPanel panel = new CategoryPanel();
	
	private JButton addButton;
	private JScrollPane scrollPane;
	private JTable categoriesTable; 
	private ThemeLoader themeLoader;
	private JButton allCategoriesButton;
	private ArrayList<Category> categories;
	private CategoriesModel categoriesModel;
	private PanicController panicController;
	private TextFieldWithPlaceholder addCategoryField;
	private static CategoryPanelController instance;
	private static final int ROWHEIGHT = 40;
	
	public CategoryPanelController() {
		//Default language, should be saved / restored on app close / startup
		I18.getInstance().setLocale("swe");
		 
		//Set the Panic controller singleton
		this.panicController = PanicController.getInstance();
		
		//ThemeLoader for loading of theme colors
		themeLoader = new ThemeLoader();

		//Initialize the views
		panel = new CategoryPanel();

		//Create the categories table and the model
		this.categoriesModel = new CategoriesModel();
		this.categoriesTable = new JTable(categoriesModel)
		{
			/**
			 * Overriding prepareRenderer to support custom rendering of the table rows
			 */
			private static final long serialVersionUID = 1L;

			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);
				CategoriesModel model = (CategoriesModel) dataModel;
				Category category = model.getCategoryAtIndex(row);
				
				//  Row color based category color
				c.setBackground(category.getColor());
				
				return c;
			}
		};	
		
		//Customize appearance of the table
		categoriesTable.setOpaque(true);
		categoriesTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		categoriesTable.setRowHeight(ROWHEIGHT);
		categoriesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		categoriesTable.setShowGrid(false);
		
		//Set column width
		categoriesTable.getColumnModel().getColumn(0).setPreferredWidth(240);	
		
		// Set gap between columns, api in TableColumnModel
		categoriesTable.getColumnModel().setColumnMargin(0);
		
		//Remove table header
		categoriesTable.setTableHeader(null);
		
		//Disable columnselection
	    categoriesTable.setColumnSelectionAllowed(false);
		
	    //Add a listener to the selection of a category
	    categoriesTable.getSelectionModel().addListSelectionListener(new SelectCategoryAction(this.categoriesTable));
		
		//Create scrollpane with the categoriestable inside
		this.scrollPane = new JScrollPane(categoriesTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//Customize the scrollPane
		scrollPane.setBackground(themeLoader.getColor("white"));
		scrollPane.setBorder(null);
		
		//This sets the background color for the table
		scrollPane.getViewport().setBackground(themeLoader.getColor("lightGray"));
		
		this.addCategoryField = new TextFieldWithPlaceholder(I18.getInstance().properties.getString("categoryPlaceHolder"));

		//Get all colors for the color picker
		Color[] colors = new Color[6];
		for (int i = 0; i< 6; i++) {
			//Add all colors from themeLoader. 
			//TODO: Themeloader should return an array of colours instead.
			colors[i] = themeLoader.getColor("categoryColor"+i);
		}
		
		//Create the colorPicker
		JComboBox colorPicker = new JComboBox(colors);
		
		//Create the custom renderer
		ColorComboBoxRenderer renderer = new ColorComboBoxRenderer();
		renderer.setPreferredSize(new Dimension(20, 13));
		colorPicker.setRenderer(renderer);
		colorPicker.setEditable(true);
		colorPicker.setEditor(new ColorComboBoxEditor(themeLoader.getColor("categoryColor1")));
		
		//Create the clock component
		ClockComponent cc = new ClockComponent();
		
		//Create the Add category button '+' and add a listener to it
		addButton = new JButton(new ImageIcon(this.getClass().getResource("/resources/addIcon.png")));
		addButton.addActionListener(new AddCategoryAction(panicController, this, colorPicker, addCategoryField));
		
		//Create the 'All categories'-button and add a listener to it
		this.allCategoriesButton = new JButton(I18.getInstance().properties.getString("allTasks"));
		allCategoriesButton.addActionListener(new ShowAllCategoriesAction());
		
		//All all the views to the CategoryPanel
		panel.addCategoryTextField(this.addCategoryField);
		panel.addColorPicker(colorPicker);
		panel.addNewCategoryButton(addButton);	
		panel.addCategoriesScrollView(this.scrollPane);
		panel.addShowAllCategoriesButton(this.allCategoriesButton);
		panel.addClockView(cc);
		
		//Start the clock
		cc.start();
		
		updateGUI();
	}

	public CategoryPanel getView() {
		return panel;
	}
	
	/**
	 * 
	 * Refreshes the UI to show changes made
	 */
	public void updateGUI(){
		ArrayList<Category> categories = panicController.getCategories();
		this.categories = categories;;
		this.categoriesModel.setCategories(categories);
		this.categoriesModel.fireTableDataChanged();
	}
	
	/**
	 * 
	 * @return Singleton instance of CategoryPanelController
	 */
	public static CategoryPanelController getInstance() {
		 if (instance == null) {
			 synchronized (CategoryPanelController.class){
				 if (instance == null) {
					 instance = new CategoryPanelController();
				 }
			 }
		 }
		 return instance;
	}

	/**
	 * Updates the language strings
	 */
	public void updateLanguage(){
		addCategoryField.changePlaceholderTextTo(I18.getInstance().properties.getString("categoryPlaceHolder"));
		allCategoriesButton.setText(I18.getInstance().properties.getString("allTasks"));
	}

	/**
	 * Selects the category at the given index
	 */
	public void selectCategoryAtIndex(int index) {
		panicController.setCategory(this.categories.get(index));
	}
	
	/**
	 * 
	 * @return The current text entered in addCategoryField
	 */
	public String getCategoryFieldText(){
		return addCategoryField.getText();
	}

}




