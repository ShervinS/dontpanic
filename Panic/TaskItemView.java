package Panic;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author johannes
 */
public class TaskItemView extends JPanel {

    JPanel prioPanel;
    JCheckBox doneCheck;
    JLabel titleLabel, dateLabel, prioLabel;

    public TaskItemView() {
    	
    }
    
    public TaskItemView(Task t) {
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        prioPanel = new JPanel();
        doneCheck = new JCheckBox();
        titleLabel = new JLabel(t.getTitle());
        dateLabel = new JLabel(t.getDate());
        prioLabel = new JLabel(Integer.toString(t.getPriority()));

        prioPanel.setBackground(Color.red);
        c.fill = GridBagConstraints.VERTICAL;
        c.ipadx = 20;
        c.ipady = 10;      //make this component tall
        c.weightx = 0.0;

        prioPanel.setBackground(Color.red);
        c.fill = GridBagConstraints.VERTICAL;
        c.ipadx = 20;
        c.ipady = 10;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridx = GridBagConstraints.RELATIVE;
        add(prioPanel, c);

       // c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;      //make this component tall
        c.ipadx = 20;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridx = GridBagConstraints.RELATIVE;
        add(doneCheck, c);

        //c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;      //make this component tall
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridx = GridBagConstraints.RELATIVE;
        add(titleLabel, c);

        //c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;      //make this component tall
        c.weightx = 0.3;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridx = GridBagConstraints.RELATIVE;
        add(dateLabel, c);

        //c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;      //make this component tall
        c.weightx = 0.3;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridx = GridBagConstraints.RELATIVE;
        add(prioLabel, c);
    }
}
