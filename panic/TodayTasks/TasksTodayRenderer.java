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
		Task t = (Task) value;
		title.setText(t.getTitle());
		title.setPreferredSize(new Dimension(150, 20));
		title.setForeground(Color.black);
		if (isSelected)
			this.setBackground(Color.LIGHT_GRAY);
		else
			this.setBackground(Color.WHITE);
		this.add(title, BorderLayout.WEST);
		return this;
	}

}
