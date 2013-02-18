package Panic;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MidPanelView extends JPanel {

	GridBagConstraints c;
	DefaultListModel model;
	JList list;
	
	public MidPanelView() {
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		model = new DefaultListModel();
		list = new JList(model);
		list.setCellRenderer(new CheckBoxListRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				JList list = (JList) event.getSource();
				int index = list.locationToIndex(event.getPoint());
				TaskItem item = (TaskItem) list.getModel().getElementAt(index);
				item.setSelected(! item.isSelected());
				list.repaint(list.getCellBounds(index, index));
			}
		});
		JScrollPane pane = new JScrollPane(list);
		
		JButton addButton = new JButton("New Task");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame popup = new JFrame("!Panic");
				String s = "";
				while (s == "") {
					s = (String)JOptionPane.showInputDialog(popup, "Task:", "New To-Do Task", JOptionPane.PLAIN_MESSAGE, null, null, null);
				};
				TaskItem item = new TaskItem(s);
				model.addElement(item);
			}
		});
		
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		add(pane, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.0;
		add(addButton, c);
	}
	
}
