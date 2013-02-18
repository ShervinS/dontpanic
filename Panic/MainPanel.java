package Panic;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	CategoryItemView catView;
	TaskItemView taskView;
	TaskModiferView modiView;
	public MainPanel(LayoutManager lm, LeftPanelView leftView, MidPanelView midView, RightPanelView rightView){
		super(lm);
		catView = new CategoryItemView();
		taskView = new TaskItemView();
	}
	
}
