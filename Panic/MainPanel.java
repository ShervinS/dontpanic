package Panic;

import java.awt.GridBagConstraints;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	CategoryItemView catView;
	TaskItemView taskView;
	TaskModiferView modiView;
	public MainPanel(LayoutManager lm, LeftPanelView leftView, MidPanelView midView, RightPanelView rightView){
		super(lm);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		add(leftView, c);
		c.gridx = 1;
		add(midView, c);
		c.gridx = 2;
		add(rightView, c);
		catView = new CategoryItemView();
		taskView = new TaskItemView();
	}
	
}
