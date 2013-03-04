package details.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.JDatePicker;

import details.DateChooser;
import details.DetailsPanelController;

public class DateAction implements PropertyChangeListener {

	
	private DetailsPanelController pc;
	private JDateChooser date;
	
	public DateAction(DetailsPanelController pc, JDateChooser date) {
		this.pc = pc;
		this.date = date;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		//System.out.println(date.getDate());
		if (pc.getCurrentTask() != null) {
			Calendar c = date.getCalendar();
			String year = String.valueOf(c.get(c.YEAR));
			String month = String.valueOf(c.get(c.MONTH)+1);
			String day = String.valueOf(c.get(c.DAY_OF_MONTH));
			if (day.length() < 2) {
				day = "0" + day;
			}
			if (month.length() < 2) {
				month = "0" + month;
			}
			String dateString = year + month + day;
			pc.getCurrentTask().setDueDate(dateString);	
		}
	}

}
