package details;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import details.actions.DateAction;

public class DateChooser extends JPanel {
	
	private JYearChooser year;
	private JMonthChooser month;
	private JDayChooser day;
	
	public DateChooser() {
		this.year = new JYearChooser();
		this.month = new JMonthChooser();
		this.day = new JDayChooser();
	}
	
	public void addChangeListener(DateAction da) {
		
	}
	
	public int getDay() {
		return day.getDay();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		year.paint(g);
		month.paint(g);
		day.paint(g);
	}
}
