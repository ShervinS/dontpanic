package categories;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;


/*
 * NOTE: This class i defective!
 * It used to work fine (a little analog clock made up of three rotating images,
 * but since the images were updated it all stopped working. We can't even load the
 * BufferedImages anymore, and havn't had the time to figure out why.
 * 
 * It will be fixed before our very final presentation though.
 */


public class ClockComponent extends JComponent implements ActionListener {
	
	Timer time;
	Calendar cal;
	BufferedImage pcFaceImg = null;
	BufferedImage pcHourImg = null;
	BufferedImage pcMinuteImg = null;
	BufferedImage pcFaceCenterImg = null;
		
	private int centerX;
	private int centerY;
	private int diameter;
	private int offset;
	private BufferedImage clockImage;
	
	private static final double TWO_PI   = 2.0 * Math.PI;
 
    public ClockComponent() {
    	
    	setPreferredSize(new Dimension(60,60));
    	cal = Calendar.getInstance();	
    	time=new Timer(1000, this);
    }
    
    public void start() {
    	time.start();
    }
    
    public void stop() {
    	time.stop();
    }
    
    @Override
	public void actionPerformed(ActionEvent arg0) {
    	cal.setTimeInMillis(System.currentTimeMillis());
		this.revalidate();
		this.repaint();
	}
    
    @Override public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(new Color(0x252525));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        int w = getWidth();
        int h = getHeight();
        diameter = ((w < h) ? w : h);
        offset = 4;
        diameter -= offset*2;
        centerX = diameter/2;
        centerY = diameter/2;
        
        drawFace(g2);
        drawDetails(g2);
    }
    
    private void drawFace(Graphics2D g2) {
    	g2.setStroke(new BasicStroke(3));
    	if (diameter < 35) {
    		g2.setStroke(new BasicStroke(2));
    	}
        g2.drawOval(offset, offset, diameter, diameter);
        g2.fillOval(offset+(diameter/2)-2, offset+(diameter/2)-2, 6, 6);
        
        int radius = diameter/2;
        
        //... Draw the tick marks around the circumference.
		g2.setStroke(new BasicStroke(1));
        for (int sec = 0; sec < 60; sec++) {
            int tickStart;
            if (sec%15 == 0) {
                tickStart = radius - 4;  // Draw long tick mark every 15.
                drawRadius(g2, sec / 60.0, tickStart , radius);
            } else if (sec%5 == 0) {
                tickStart = radius - 2;   // Short tick mark.
                drawRadius(g2, sec / 60.0, tickStart , radius);
            }
        }
    }
    
    private void drawDetails(Graphics2D g2) {
        int hours   = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        int millis  = cal.get(Calendar.MILLISECOND);
        int handMax = diameter/2;
        
        double fseconds = (seconds + (double)millis/1000) / 60.0;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        if (seconds % 2 == 0) {
        	formatter = new SimpleDateFormat("HH mm");
        }
    	Date rightNow = new Date();
        g2.drawString(formatter.format(rightNow), getHeight()+10, (getHeight()/2)+5);
        formatter = new SimpleDateFormat("EEE d MMM yyyy");
        g2.drawString(formatter.format(rightNow), getHeight()+65, (getHeight()/2)+5);
        
        
        
        //... minute hand
        handMax = diameter/3; 
        double fminutes = (minutes + fseconds) / 60.0;
        drawRadius(g2, fminutes, 0, handMax);
        
        //... hour hand
        handMax = diameter/4;
        drawRadius(g2, (hours + fminutes) / 12.0, 0, handMax);
    }
    
    private void drawRadius(Graphics2D g2, double percent, int minRadius, int maxRadius) {
		double radians = (0.5 - percent) * TWO_PI;
		double sine   = Math.sin(radians);
		double cosine = Math.cos(radians);
		
		int dxmin = centerX + (int)(minRadius * sine);
		int dymin = centerY + (int)(minRadius * cosine);
		
		int dxmax = centerX + (int)(maxRadius * sine);
		int dymax = centerY + (int)(maxRadius * cosine);
		g2.setStroke(new BasicStroke(2));
		g2.drawLine(dxmin+offset, dymin+offset, dxmax+offset, dymax+offset);
    }
	
}
