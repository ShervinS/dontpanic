package tasks;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;

import panic.I18;


public class MidPanelView extends JPanel {

	private final JTextField quickAdd; 
	private JButton addButton;
	private JButton detailsButton;
	private GridBagConstraints c;
	private JScrollPane pane;
	final private MidPanelController mpc;
	
	public MidPanelView(final MidPanelController mpc, ArrayList<Task> tasks) {
		I18.getInstance().setLocale("swe");
		this.mpc = mpc;
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		/**
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
		*/
		pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		quickAdd = new JTextField(I18.getInstance().properties.getString("quickAdd"));
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
		
		addButton = new JButton(I18.getInstance().properties.getString("addTask"), new ImageIcon("ikon.png"));
		detailsButton = new JButton(I18.getInstance().properties.getString("addDetails"));
		addButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				mpc.newTask(new Task(quickAdd.getText(), "", "", 1, "", false));
				quickAdd.setText("Quickadd...");
				quickAdd.setForeground(Color.GRAY);
			}
		});
		
		detailsButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				mpc.addTask(new Task(quickAdd.getText(), "", "", 1, "", false));
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
		pane.revalidate();
		pane.repaint();
	}
	
	
	public void updateShownTasks(ArrayList<Task> tasks) {
		JPanel newView = new JPanel();
		newView.setBackground(new Color(0));
		newView.setLayout(new BoxLayout(newView, BoxLayout.PAGE_AXIS));
		if (tasks.size() > 0) {
			newView.add(tasks.get(tasks.size()-1).getView());
		}
		for (int i = tasks.size()-2; i>=0; i--) {
			newView.add(Box.createVerticalStrut(1));
			newView.add(tasks.get(i).getView());
		}
		JViewport viewport = new JViewport();
		viewport.setView(newView);
		pane.setViewport(viewport);
	}
	
	public void updateLanguage(){
		addButton.setText(I18.getInstance().properties.getString("addTask"));
		detailsButton.setText(I18.getInstance().properties.getString("addDetails"));
		quickAdd.setText(I18.getInstance().properties.getString("quickAdd"));
	}
}
