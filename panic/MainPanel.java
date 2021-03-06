package panic;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import tasks.TaskView;
import tasks.TasksPanelView;
import categories.CategoryPanel;
import categories.CategoryView;
import details.DetailsPanelView;

/**
 * The MainPanel which has all three views of
 * the !Panic ToDo-app
 * @author 
 *
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	CategoryView catView;
	TaskView taskView;
	
	
	/**
	 * @param leftView
	 * @param midView
	 * @param rightView
	 */
	public MainPanel(CategoryPanel leftView, TasksPanelView midView, DetailsPanelView rightView) {
		super();
		setLayout(new BorderLayout());
		add(leftView, BorderLayout.LINE_START);
		add(midView, BorderLayout.CENTER);
		add(rightView, BorderLayout.LINE_END);
		
	}
	
	public MainPanel(LayoutManager lm, CategoryPanel leftView, TasksPanelView midView, DetailsPanelView rightView){
		super(lm);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		add(leftView, c);
		c.gridx = 1;
		c.weightx = 1;
		add(midView, c);
		c.gridx = 2;
		c.weightx = 0.5;
		add(rightView, c);
		
	//	catView = new CategoryView();
		taskView = new TaskView();
	}
	
}
