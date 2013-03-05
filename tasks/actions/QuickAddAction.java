package tasks.actions;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class QuickAddAction implements FocusListener {
	
	private JTextField quickAdd;
	
	public QuickAddAction(JTextField quickAdd) {
		this.quickAdd = quickAdd;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		quickAdd.selectAll();
		quickAdd.setForeground(new Color(0));
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		quickAdd.select(0, 0);
		quickAdd.setForeground(Color.gray);		
	}

}
