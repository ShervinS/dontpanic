package details.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.JDatePicker;

import details.DetailsPanelController;

public class DateAction extends AbstractAction {

	
	private DetailsPanelController pc;
	private JDatePicker date;
	
	public DateAction(DetailsPanelController pc, JDatePicker date) {
		this.pc = pc;
		this.date = date;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DateModel<?> d = date.getModel();
		System.out.println(d.getYear() + " " + d.getMonth() + " " + d.getDay());
	}

}
