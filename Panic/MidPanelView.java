package Panic;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class MidPanelView extends JPanel {

	private GridBagConstraints c;
	private DefaultListModel model;
	private JList list;
	final private MidPanelController mpc;
	

	public MidPanelView(final MidPanelController mpc) {
		this.mpc = mpc;
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
		final JTextField quickAdd = new JTextField("Quickadd...");
		quickAdd.setEditable(true);
		quickAdd.setForeground(Color.GRAY);
		quickAdd.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				quickAdd.selectAll();
				quickAdd.setForeground(new Color(0));
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				quickAdd.select(0, 0);
				quickAdd.setForeground(Color.gray);		
			}
			
		});
		
		JButton addButton = new JButton("Add Task", new ImageIcon("ikon.png"));
		JButton detailsButton = new JButton("Add More Details");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskItem item = new TaskItem(quickAdd.getText());
				quickAdd.setText("Quickadd...");
				quickAdd.setForeground(Color.GRAY);
				model.addElement(item);
				/**
				JFrame popup = new JFrame("!Panic");
				String s = "";
				while (s == "") {
					s = (String)JOptionPane.showInputDialog(popup, "Task:", "New To-Do Task", JOptionPane.PLAIN_MESSAGE, null, null, null);
				};
				TaskItem item = new TaskItem(s);
				model.addElement(item);
				*/
			}
		});
		
		detailsButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				mpc.toggleRightPanel(quickAdd.getText());
			}
		});
		
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		
		c.gridwidth += 1;
		c.weightx = 0.5;
		c.weighty = 0.5;
		add(pane, c);
		c.gridy += 1;
		c.weighty = 0.0;
		add(quickAdd, c);
		
		
		c.gridwidth -= 1;
		c.gridy += 1;
		c.weighty = 0.0;
		add(addButton, c);
		c.gridx += 1;
		add(detailsButton, c);
	}
	
}
