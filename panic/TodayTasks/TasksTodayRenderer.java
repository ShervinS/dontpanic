package panic.TodayTasks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import tasks.Task;

/**
 * A custom renderer for use for a popup window
 * showing Todays Tasks
 * @author joseph
 *
 */
public class TasksTodayRenderer extends JPanel implements ListCellRenderer {

	private JLabel title;
	
	public TasksTodayRenderer() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		title = new JLabel();
		
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		//Only the object Task should be inserted into the component using this
		//renderer, so casting is safe
		Task t = (Task) value;
		//The title of t is what will be shown
		title.setText(t.getTitle());
		title.setPreferredSize(new Dimension(150, 20));
		title.setForeground(Color.black);
		//Different color depending on if selected or not
		if (isSelected)
			this.setBackground(Color.LIGHT_GRAY);
		else
			this.setBackground(Color.WHITE);
		this.add(title, BorderLayout.WEST);
		return this;
	}

}
