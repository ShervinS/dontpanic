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

import panic.ThemeLoader;
import tasks.actions.TaskSelectionAction;


/**
 * The controller for the category part of the gui, contains the communication
 * with the other controllers and the model.
 * 
 * @author Erik
 *
 */
public class CategoryPanelController {

	private ThemeLoader tl;
	private static final String ClockView = null;
	private static final int ROWHEIGHT = 40;
	CategoryPanel panel = new CategoryPanel();
	protected PanicController pc;
	ArrayList<Category> categories;
	private JScrollPane scrollPane;
	private JButton addButton;
	private JTable categoriesTable;
	private TextFieldWithPlaceholder addCategoryField;
	private CategoriesModel categoriesModel;
	private static CategoryPanelController instance;

	public CategoryPanelController() {
		I18.getInstance().setLocale("swe");
		tl = new ThemeLoader();

		//Initialize the views
		panel = new CategoryPanel();

		//Create the categories table
		this.categoriesModel = new CategoriesModel();
		this.categoriesTable = new JTable(categoriesModel)
		{
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);
				CategoriesModel model = (CategoriesModel) dataModel;
				Category category = model.getCategoryAtIndex(row);
				
				//  Color row based on a cell value
				c.setBackground(category.getColor());
				
				return c;
			}
		};	
		
		this.categoriesTable.setOpaque(true);
		this.categoriesTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		this.categoriesTable.setRowHeight(ROWHEIGHT);
		this.categoriesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.categoriesTable.setShowGrid(false);
		// set gap between columns, api in TableColumnModel
		this.categoriesTable.getColumnModel().setColumnMargin(0);
		
		// convenience for setting both row and column gaps
		this.categoriesTable.setRowMargin(0);
		
		this.categoriesTable.setTableHeader(null);
	    this.categoriesTable.setColumnSelectionAllowed(false);
		this.categoriesTable.getSelectionModel().addListSelectionListener(new SelectCategoryAction(this.categoriesTable));
		
		//Set column width
		this.categoriesTable.getColumnModel().getColumn(0).setPreferredWidth(240);

		//Create scrollpane with the categoriestable inside
		this.scrollPane = new JScrollPane(categoriesTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setBackground(tl.getColor("white"));

		//This sets the background color for the table
		this.scrollPane.getViewport().setBackground(new Color(0xededed));
		this.scrollPane.setBorder(null);

		addButton = new JButton(new ImageIcon(this.getClass().getResource("/resources/addIcon.png")));

		//Set parent controller
		this.pc = PanicController.getInstance();

		this.addCategoryField = new TextFieldWithPlaceholder(I18.getInstance().properties.getString("categoryPlaceHolder"));

		ClockView clockView = new ClockView();
		Color[] colors = {new Color(10,100,200), new Color(100,10,200), new Color(200,100,200), new Color(255,0,0)};
		JComboBox colorPicker = new JComboBox(colors);
		ColorComboBoxRenderer renderer = new ColorComboBoxRenderer();
		renderer.setPreferredSize(new Dimension(20, 13));
		colorPicker.setRenderer(renderer);
		colorPicker.setEditable(true);
		colorPicker.setEditor(new ColorComboBoxEditor(tl.getColor("red")));
		ClockComponent cc = new ClockComponent();
		
		//Add actions and listeners
		addButton.addActionListener(new AddCategoryAction(pc, this, colorPicker, addCategoryField));
		
		//Give everything to the view
		panel.addCategoryTextField(this.addCategoryField);
		panel.addColorPicker(colorPicker);
		panel.addNewCategoryButton(addButton);	
		panel.addCategoriesScrollView(this.scrollPane);
		panel.addClockView(cc);
		cc.start();
		updateGUI();
	}

	public CategoryPanel getView() {
		return panel;
	}

	public void updateGUI(){
		this.categories = pc.getCategories();
		this.categoriesModel.setCategories(pc.getCategories());
		this.categoriesModel.fireTableDataChanged();
	}
	
	
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

	public void updateLanguage(){
		addCategoryField.changePlaceholderTextTo(I18.getInstance().properties.getString("categoryPlaceHolder"));

	}

	public void selectCategoryAtIndex(int index) {
		pc.setCategory(this.categories.get(index));
	}
	
	public String getCategoryFieldText(){
		return addCategoryField.getText();
	}

}




