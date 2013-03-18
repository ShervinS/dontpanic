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
	private BufferedImage clockImage;
	
	private static final double TWO_PI   = 2.0 * Math.PI;
 
    public ClockComponent() {
    	
    	setPreferredSize(new Dimension(60,60));
    	try {
    		pcFaceImg = ImageIO.read(new File("resources/img_CB.png"));
    		pcFaceCenterImg = ImageIO.read(new File("resources/img_CBC.png"));
			//pcHourImg = ImageIO.read(new File("resources/img_CH.png"));
			//pcMinuteImg = ImageIO.read(new File("resources/img_CM.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		this.revalidate();
		this.repaint();
	}
    
    @Override public void paintComponent(Graphics g) {
    	
        cal.setTimeInMillis(System.currentTimeMillis());

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        int w = getWidth();
        int h = getHeight();
        diameter = ((w < h) ? w : h);
        centerX = diameter / 2;
        centerY = diameter / 2;
        
        g2.drawImage(pcFaceImg, 7, 7, null);
        g2.drawImage(pcFaceCenterImg, 7, 7, null);
        
        drawDetails(g2);
    }
    
    private void drawDetails(Graphics2D g2) {
        int hours   = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        int millis  = cal.get(Calendar.MILLISECOND);
        int handMax = diameter/2;    // Second hand extends to outer rim.
        
        double fseconds = (seconds + (double)millis/1000) / 60.0;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        if (seconds % 2 == 0) {
        	formatter = new SimpleDateFormat("HH mm");
        }
    	Date rightNow = new Date();
    	g2.setColor(new Color(0xEAF2F2));
        g2.drawString(formatter.format(rightNow), 60, 35);
        
        //... minute hand
        handMax = diameter/3; 
        double fminutes = (minutes + fseconds) / 60.0;
        drawRadius(g2, fminutes, 0, handMax);
        
        //... hour hand
        handMax = diameter/4;
        drawRadius(g2, (hours + fminutes) / 12.0, 0, handMax);
    }
    
    private void drawRadius(Graphics2D g2, double percent, int minRadius, int maxRadius) {
		//... percent parameter is the fraction (0.0 - 1.0) of the way
		//    clockwise from 12.   Because the Graphics2D methods use radians
		//    counterclockwise from 3, a little conversion is necessary.
		//    It took a little experimentation to get this right.
		double radians = (0.5 - percent) * TWO_PI;
		double sine   = Math.sin(radians);
		double cosine = Math.cos(radians);
		
		int dxmin = centerX + (int)(minRadius * sine);
		int dymin = centerY + (int)(minRadius * cosine);
		
		int dxmax = centerX + (int)(maxRadius * sine);
		int dymax = centerY + (int)(maxRadius * cosine);
		g2.setStroke(new BasicStroke(2));
		g2.setColor(new Color(0xEAF2F2));
		g2.drawLine(dxmin, dymin, dxmax, dymax);
    }
    
    /*		
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);  
    	Graphics2D g2D = (Graphics2D) g;
    	
    	int minute = cal.get(Calendar.MINUTE);
    	int hour = cal.get(Calendar.HOUR_OF_DAY);
    	if (hour>=12) {
    		hour -= 12;
    	}
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    	Date rightNow = new Date();
    	System.out.println(formatter.format(rightNow));
    	g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
    	g2D.drawImage(pcFaceImg, 0, 0, null);
    	g2D.drawImage(rotate(pcMinuteImg, Math.toRadians(((float)minute/60.0)*360.0)), 0, 0, null);
    	g2D.drawImage(rotate(pcHourImg, Math.toRadians(((hour*60+minute)/(12.0*60.0))*360.0)), 0, 0, null);

	}
	
	private BufferedImage rotate(BufferedImage image, double angle) {
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = image.getWidth(), h = image.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
	    GraphicsConfiguration gc = getGraphicsConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww-w)/2, (newh-h)/2);
	    g.rotate(angle, w/2, h/2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	}*/
	
}
