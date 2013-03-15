package categories;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
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
import javax.swing.Timer;


/*
 * NOTE: This class i defective!
 * It used to work fine (a little analog clock made up of three rotating images,
 * but sinces the images were updated it all stopped working. We can't even load the
 * BufferedImages anymore, and havn't had the time to figue out why.
 * 
 * It will be fixed before our very final presentation though.
 */


public class ClockComponent extends JComponent implements ActionListener {
	
	Timer time;
	Calendar cal;
	BufferedImage pcFaceImg = null;
	BufferedImage pcHourImg = null;
	BufferedImage pcMinuteImg = null;
 
    public ClockComponent() {
    	
    	try {
    		pcFaceImg = ImageIO.read(new File("resources/img_ClockBase2.png"));
			pcHourImg = ImageIO.read(new File("resources/img_ClockHour2.png"));
			pcMinuteImg = ImageIO.read(new File("resources/img_ClockMinute2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	if (pcFaceImg == null) {
    		System.out.println("Ouch, den Šr fortfarande null!");
    	}

    	cal = Calendar.getInstance();	
    	time=new Timer(30000, this);
        time.start();
    }
    		
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
    	//System.out.println(formatter.format(rightNow));
    	
    	g2D.drawImage(pcFaceImg, 0, 0, null);
    	g2D.drawImage(rotate(pcMinuteImg, Math.toRadians(((float)minute/60.0)*360.0)), 0, 0, null);
    	g2D.drawImage(rotate(pcHourImg, Math.toRadians(((hour*60+minute)/(12.0*60.0))*360.0)), 0, 0, null);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.revalidate();
		this.repaint();
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
	}
	
}
