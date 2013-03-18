package tasks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Component for displaying a task in the version before tables were used.
 * @author johannes
 */
public class TaskView extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel categoryPanel;
	JCheckBox doneCheck;
	JLabel titleLabel, dateLabel, prioLabel;
	Image bgImage;
	private Task t;

	public TaskView() {

	}

	public TaskView(Task t) {
		super(new GridBagLayout());
		this.t = t;
		GridBagConstraints c = new GridBagConstraints();
		categoryPanel = new JPanel();
		doneCheck = new JCheckBox();
		titleLabel = new JLabel(t.getTitle());
		dateLabel = new JLabel(t.getDueDate());
		String priorityString = "";
		
		prioLabel = new JLabel(priorityString);
		prioLabel.setFont(new Font("Verdana", 40, 40));
		
		c.fill = GridBagConstraints.VERTICAL;
		c.ipadx = 20;
		c.ipady = 10;     
		c.weightx = 0.0;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = GridBagConstraints.RELATIVE;
		add(categoryPanel, c);

		
		c.ipady = 10;      
		c.ipadx = 20;
		c.weightx = 0.0;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = GridBagConstraints.RELATIVE;
		
		add(doneCheck, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;     
		c.weightx = 1;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = GridBagConstraints.RELATIVE;
		add(titleLabel, c);

	
		c.ipady = 10;      
		c.weightx = 0.3;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = GridBagConstraints.RELATIVE;
		add(dateLabel, c);

		
		
		c.ipady = 10;      
		c.weightx = 0.3;
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = GridBagConstraints.RELATIVE;
		add(prioLabel, c);
		
		
		
		setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
		setPreferredSize(new Dimension(0, 50));
		setMinimumSize(new Dimension(0, 50));
		
		try
	    {
	      bgImage = javax.imageio.ImageIO.read(this.getClass().getResource("/resources/taskCellBackground_light.png"));
	    }
	    catch (Exception e) { /*handled in paintComponent()*/ System.out.println("No bg!");}
	}
	

	  @Override
	  protected void paintComponent(Graphics g)
	  {
	    super.paintComponent(g); 
	    if (bgImage != null)
	      g.drawImage(bgImage, 0,0,this.getWidth(),this.getHeight(),this);
	  }
	
	public void paint(Graphics g) {
		super.paint(g);
		titleLabel.setText(t.getTitle());
		String priorityString = "";
		prioLabel.setText(priorityString);
		revalidate();
		repaint();
	}
}
