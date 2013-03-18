package categories;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.Timer;

import panic.ThemeLoader;

/**
 * Our custom component: the !Panic Clock
 * An analog clock that updates in real time
 * and scales in the application window.
 * @author Carl Ekman
 */

public class ClockComponent extends JComponent implements ActionListener {
	
	private Timer time;
	private Calendar cal;
	private Color clockColor;
	
	private int centerX;
	private int centerY;
	private int diameter;
	private int offset;
	
	private static final double DOUBLE_PI = Math.PI*2.0;
	private static final int OFFSET_VAL = 4; // Looks good
	
	//Creating the component instance.
    public ClockComponent() {
    	setPreferredSize(new Dimension(60,60));
    	cal = Calendar.getInstance();	
    	
    	// The timer will update every second.
    	time=new Timer(1000, this);
    }
    
    // Call this to start the clock.
    public void start() {
    	time.start();
    }
    
    // Call this to stop the clock.
    public void stop() {
    	time.stop();
    }
    
    // Called every second by the timer.
    @Override
	public void actionPerformed(ActionEvent arg0) {
    	// Get the current time.
    	cal.setTimeInMillis(System.currentTimeMillis());
    	
    	//Repaint the component.
		this.revalidate();
		this.repaint();
	}
    
    // Repaints the component to the current time.
    @Override
    public void paintComponent(Graphics g) {
    	// Get the 2D graphics context.
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Theme Loader gets color codes stored in colors.xml.
        ThemeLoader tl = new ThemeLoader();
        clockColor = tl.getColor("text"); // Same color as text.
        // Set the color of the clock.
        g2.setColor(clockColor);
        
        // Update the dimensions in case of resize.
        int w = getWidth();
        int h = getHeight();
        diameter = ((w < h) ? w : h);
        offset = OFFSET_VAL;
        diameter -= offset*2;
        centerX = diameter/2;
        centerY = diameter/2;
        
        // Draw the component.
        drawFace(g2);
        drawDetails(g2);
    }
    
    // Draws the face of the clock.
    private void drawFace(Graphics2D g2) {
    	// Set thickness of clock frame to 3...
    	g2.setStroke(new BasicStroke(3));
    	// ...or 2 if the clock is scaled to very small size.
    	if (diameter < 35) {
    		g2.setStroke(new BasicStroke(2));
    	}
    	// Draw the frame.
        g2.drawOval(offset, offset, diameter, diameter);
        g2.fillOval(offset+(diameter/2)-2, offset+(diameter/2)-2, 6, 6);
        
        int radius = diameter/2;
        
        // Draw the notches at every five minute mark.
		g2.setStroke(new BasicStroke(1));
        for (int sec = 0; sec < 60; sec++) {
            int tickStart;
            if (sec%15 == 0) {
            	// Every 15 minutes it is a bit longer.
                tickStart = radius - 4;
                drawRadius(g2, sec / 60.0, tickStart , radius);
            } else if (sec%5 == 0) {
            	// Otherwise short.
                tickStart = radius - 2;
                drawRadius(g2, sec / 60.0, tickStart , radius);
            }
        }
    }
    
    // Draw the hands and time displays of the clock.
    private void drawDetails(Graphics2D g2) {
    	// Get the latest time divisions.
        int hrs   = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        int secs = cal.get(Calendar.SECOND);
        int msecs  = cal.get(Calendar.MILLISECOND);
        int handMax = diameter/2;
        
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        if (secs % 2 == 0) {
        	formatter = new SimpleDateFormat("HH mm");
        }
    	Date rightNow = new Date();
        g2.drawString(formatter.format(rightNow), getHeight()+10, (getHeight()/2)+5);
        formatter = new SimpleDateFormat("EEE d MMM yyyy");
        g2.drawString(formatter.format(rightNow), getHeight()+65, (getHeight()/2)+5);
        
        // Float value for seconds (makes ticks smoother).
        double fseconds = (secs + (double)msecs/1000) / 60.0;
        
        // Draw the minute hand.
        handMax = diameter/3; 
        double fminutes = (mins + fseconds) / 60.0;
        drawRadius(g2, fminutes, 0, handMax);
        
        // Draw the hour hand.
        handMax = diameter/4;
        drawRadius(g2, (hrs + fminutes) / 12.0, 0, handMax);
    }
    
    // Draw a radius in the clock given the angle percentage, starting point and stopping point.
    private void drawRadius(Graphics2D g2, double percent, int startRadius, int stopRadius) {
    	// Convert the angle to radians.
		double radians = (0.5 - percent) * DOUBLE_PI;
		double sine   = Math.sin(radians);
		double cosine = Math.cos(radians);
		
		// Set the coordinates for the points of the line.
		int dxmin = centerX + (int)(startRadius * sine);
		int dymin = centerY + (int)(startRadius * cosine);
		int dxmax = centerX + (int)(stopRadius * sine);
		int dymax = centerY + (int)(stopRadius * cosine);
		
		// Draw the line.
		g2.setStroke(new BasicStroke(2));
		g2.drawLine(dxmin+offset, dymin+offset, dxmax+offset, dymax+offset);
    }
	
}
