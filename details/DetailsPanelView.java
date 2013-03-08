package details;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DetailsPanelView extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	public GridBagConstraints c;
	
	private JPanel filler;
	private boolean show;
	private Timer t;
	private int y;
	
	private int currentWidth;
	private int currentHeight;
	
	/**
	 * Constructor for this view. Will create an empty 
	 * RightPanelView
	 * @param p The controller for this panel
	 */
	public DetailsPanelView() {
		super();
		//y is the position the next component should be added, starts at position 0
		y = 0;
		//Set layoutmanager
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.7;
		c.insets = new Insets(0, 5, 5, 5);
		
		//Size and background, height will be 0, because it will automatically follow the 
		//big frame's height
		setBackground(new Color(0xededed));
		setPreferredSize(new Dimension(0, 600));
		
		//The timer used for animations
		t = new Timer(1, this);
	}
	
	
	/**
	 * Adds an element to this component
	 * @param x Where in the grid horizontally the component should be added 
	 * @param y Where in the grid vertically the component should be added
	 * @param pad How much padding should be done before the component
	 * @param comp The component to add
	 */
	public void gridAdd(int pad, JComponent comp) {
		c.insets = new Insets(pad, 5, 0, 5);
		c.gridy = y;
		//Add this to position y, then increase y
		y += 1;
		this.add(comp, c);
	}
	
	/**
	 * Will pad this view to move every component over it
	 * to the top, and every component below it to the bottom
	 */
	public void pad() {
		//We only allow one filler, if one is already there, remove it first
		if (filler != null) {
			remove(filler);
		}
		c.gridy = y;
		//Add this to position y, then increase y
		y += 1;
		c.weighty = 1;
		filler = new JPanel();
		//Let filler be the invisible
		filler.setOpaque(false);
		add(filler, c);
		c.weighty = 0;
	}
	
	/**
	 * Will animate this panel to show, or close
	 * @param b Show if b is true, close if b is false
	 */
	public synchronized void showPanel(Boolean b) {
		show = b;
		//Stop the animating timer before staring a new one
		t.stop();
		//Get the current state of the panel
		currentHeight = this.getHeight();
		currentWidth = this.getWidth();
		//Start the new animation
		t.start();
	}

	@Override
	/**
	 * Shrinks or enlarges this panel by 15 pixels
	 * @param e Event being executed
	 */
	public void actionPerformed(ActionEvent e) {
		//If the animation is below 15 or above 185, it is done, and we will
		//set the width to its max or min value
		if ((currentWidth < 15 && !show) || (currentWidth > 185 && show)) {
			currentWidth = show ? 200 : 0;
			setPreferredSize(new Dimension(currentWidth, currentHeight));
			revalidate();
			repaint();
			//Stop the animation
			t.stop();
		}
		else {
			currentWidth += show ? 15 : -15;
			setPreferredSize(new Dimension(currentWidth, currentHeight));
			revalidate();
			repaint();
		}
	}
	
	
	/**
	 * 
	 * @return If this panel is showing at the moment
	 */
	public boolean isOpen() {
		return show;
	}
	
	/**
	 * Open or closes the panel without animation
	 * @param b True for open, False for close
	 */
	public void setOpen(boolean b)  {
		show = b;
		this.setPreferredSize(new Dimension(show ? 200 : 0, currentHeight));
	}
}
