package details.actions;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import com.toedter.calendar.JDateChooser;

import details.DetailsPanelController;

public class DateAction implements PropertyChangeListener {

	private JDateChooser date;
	
	public DateAction(JDateChooser date) {
		this.date = date;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		DetailsPanelController pc = DetailsPanelController.getInstance();
		if (pc.getCurrentTask() != null) {
			Calendar c = date.getCalendar();
			String year = String.valueOf(c.get(Calendar.YEAR));
			String month = String.valueOf(c.get(Calendar.MONTH)+1);
			String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
			if (day.length() < 2) {
				day = "0" + day;
			}
			if (month.length() < 2) {
				month = "0" + month;
			}
			String dateString = year + month + day;
			pc.getCurrentTask().setDueDate(dateString);
			pc.updateTask(pc.getCurrentTask());
		}
	}

}
