package Panic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TaskSelectionListener implements MouseListener {

	private Task task;
	private PanicController pc;
	
	public TaskSelectionListener(Task t, PanicController p) {
		task = t;
		pc = p;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		pc.taskSelected(task);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
