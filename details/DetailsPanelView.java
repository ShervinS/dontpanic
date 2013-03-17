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


/**
 * The view for the window with detailed information
 * in the !Panic ToDo-application
 * @author joseph
 *
 */
public class DetailsPanelView extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	
	//A JPanel used to pad components from each other 
	private JPanel filler;
	
	private boolean show;
	
	//Timer used for animations
	private Timer t;
	
	private int currentWidth;
	private int currentHeight;
	
	
	/**
	 * Constructor for this view. 
	 * Will create an empty DetailsPanelView
	 */
	public DetailsPanelView() {
		super();
		
		//Set layoutmanager
		setLayout(new GridBagLayout());
		
		
		//Size and background, height will be 0, because it will automatically follow the 
		//big frame's height
		setBackground(new Color(0xededed));
		setPreferredSize(new Dimension(0, 600));
		
		//The timer used for animations
		t = new Timer(1, this);
	}
	
	
	/**
	 * Adds an element below the last added element
	 * @param pad How much padding should be done before the component comp
	 * @param c The constraints to use for the component comp
	 * @param comp The component to add
	 */
	public void add(int pad, GridBagConstraints c, JComponent comp) {
		c.insets = new Insets(pad, 5, 0, 5);
		this.add(comp, c);
		c.gridy += 1;
	}
	
	/**
	 * Will pad this panel by moving every component added before the call
	 * to this function to the top, and every component added after it to the bottom
	 */
	public void pad(GridBagConstraints c) {		
		//We only allow one filler, if one is already there, remove it first
		if (filler != null) {
			this.remove(filler);
		}
		filler = new JPanel();
		//Let filler be invisible
		filler.setOpaque(false);
		c.weighty = 1;
		add(0, c, filler);
		c.weighty = 0;
	}
	
	/**
	 * Will animate this panel to slide out, or slide in
	 * @param b Slide out if b is true, slide in if b is false
	 */
	public synchronized void showPanel(Boolean b) {
		show = b;
		//Stop the animating timer before staring a new one
		t.stop();
		//Update the current state of the panel
		currentHeight = this.getHeight();
		currentWidth = this.getWidth();
		//Start the new animation
		t.start();
	}

	@Override
	/*
	 * Shrinks or enlarges this panel
	 */
	public void actionPerformed(ActionEvent e) {
		//If this panel's width is below 15 or above 185, the animation is done, and the
		//width will be set to its max or min value
		if ((currentWidth < 15 && !show) || (currentWidth > 185 && show)) {
			currentWidth = show ? 200 : 0;
			setPreferredSize(new Dimension(currentWidth, currentHeight));
			revalidate();
			repaint();
			//Stop the animation
			t.stop();
		}
		//If the panel's width is in between 15 and 185, its width will be enlarged
		//or shrunk
		else {
			currentWidth += show ? 15 : -15;
			setPreferredSize(new Dimension(currentWidth, currentHeight));
			revalidate();
			repaint();
		}
	}
	
	
	/**
	 * 
	 * @return If this panel is slid out
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
