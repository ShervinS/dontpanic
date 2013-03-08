package categories;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import panic.I18;
import panic.PanicController;


public class CategoryPanelController {

	private static final String ClockView = null;
	private static final int ROWHEIGHT = 40;
	CategoryPanel panel = new CategoryPanel();
	protected PanicController pc;
	ArrayList<Category> categories;
	private JScrollPane scrollPane;
	private JButton addButton;
	private JTable categoriesTable;
	private CategoriesModel categoriesModel;
	private static CategoryPanelController instance;
	
	public CategoryPanelController() {
		I18.getInstance().setLocale("swe");
		
		//Initialize the views
		panel = new CategoryPanel();
		
		//Create the categories table
		this.categoriesModel = new CategoriesModel();
		this.categoriesTable = new JTable(categoriesModel)
		{
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);
				System.out.println("Plz: " + dataModel.getValueAt(row, column));
				CategoriesModel model = (CategoriesModel) dataModel;
				Category category = model.getCategoryAtIndex(row);
				//  Color row based on a cell value
				c.setBackground(Color.BLACK);
				c.setBackground(category.getColor());
				
				return c;
			}
		};
		this.categoriesTable.setOpaque(true);
		this.categoriesTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		this.categoriesTable.setRowHeight(ROWHEIGHT);
		this.categoriesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// set gap between columns, api in TableColumnModel
		this.categoriesTable.getColumnModel().setColumnMargin(0);
		// convenience for setting both row and column gaps
		this.categoriesTable.setRowMargin(0);
		this.categoriesTable.setTableHeader(null);
	    this.categoriesTable.setColumnSelectionAllowed(false);

		//Set column width
		this.categoriesTable.getColumnModel().getColumn(0).setPreferredWidth(240);
		//this.categoriesTable.setDefaultRenderer(Object.class, )
		
		//Crate scrollpane with the categoriestable inside
		this.scrollPane = new JScrollPane(categoriesTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setBackground(new Color(88, 91, 95));
		
		//This sets the background color for the table
		this.scrollPane.getViewport().setBackground(new Color(88,91,95));

		addButton = new JButton(I18.getInstance().properties.getString("addCategory"), new ImageIcon(this.getClass().getResource("/resources/addIcon.png")));
		
		//Set parent controller
		this.pc = PanicController.getInstance();
				
		//Add actions and listeners
	//	addButton.addActionListener(new AddCategoryAction(addCategory));
		
		//Give everything to the view
		panel.addNewCategoryButton(addButton);	
		panel.addCategoriesScrollView(this.scrollPane);
		
		ClockView clockView = new ClockView();
		panel.addClockView(clockView);
		
		updateGUI();
	}
	
	
	
	public CategoryPanel getView() {
		return panel;
	}
	
	
	public void updateGUI(){
		this.categories = pc.getCategories();
		this.categoriesModel.setCategories(pc.getCategories());
		this.categoriesModel.fireTableDataChanged();
		//logCategories();
		generateDefaultCategories();
		System.out.print("HELLO");
		int i = 0;
		/*for (Category cat : this.categories) {
			JButton addButton = new JButton(cat.getName());
			addButton.setBackground(cat.getColor());
			addButton.setOpaque(true);
			addButton.setForeground(Color.BLACK);
			
			addButton.setBorderPainted(false);
			addButton.setFocusPainted(true);
			addButton.setContentAreaFilled(true);
			
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.NORTHWEST;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = i;
			c.weightx = 1;
			c.weighty = 0;
			c.insets = new Insets(0, 0, 0, 0);
			
			this.panel.add(addButton, c);
			i++;
		}*/
	}
	
	public void generateDefaultCategories() {
			Category category1 = new Category("School", new Color(10,100,150));
			Category category2 = new Category("Work", new Color(100,100,150));
			Category category3 = new Category("Activities", new Color(110,200,150));
			Category category4 = new Category("Workout", new Color(10,130,130));
			Category category5 = new Category("Fun", new Color(130,10,100));
			
			pc.addCategory(category1);
			pc.addCategory(category2);
			pc.addCategory(category3);
			pc.addCategory(category4);
			pc.addCategory(category5);
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
	
	public void logCategories() {
		for (Category cat : this.categories)
		    System.out.println(cat.getName());
	}
	public void updateLanguage(){
		addButton.setText(I18.getInstance().properties.getString("addCategory"));
	}
	
}
