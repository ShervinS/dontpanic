package tasks;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TaskTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] header;
	private ArrayList<Object[]> data;
	
	
	public TaskTableModel(String[] header) {
		this.header = header;
		data = new ArrayList<Object[]>();
	}
	
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return header.length;
	}
	
	public String getColumnName(int i) {
		return header[i];
	}
	
	public void changeData(ArrayList<Object[]> newData) {
		data = newData;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}
	
	public Class getColumnClass(int column) {
		switch (column) {
			case 0:
			case 1:
			case 2:
			case 3:
				return String.class;
			default:
				//Should be Boolean later
				return String.class;
		}
	}
	
	public void setHeader(String[] h) {
		h = header;
	}

	@Override
	public Object getValueAt(int x, int y) {
		// TODO Auto-generated method stub
		if (x <= data.size() && x >= 0 && y >= 0 && y <= header.length)
			return data.get(x)[y];
		return null;
	}

}
