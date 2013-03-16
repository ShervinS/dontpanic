package tasks;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * Custom model for showing tasks
 * @author joseph
 *
 */
public class TaskTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] header;
	private ArrayList<Object[]> data;
	
	
	/**
	 * Constructor
	 * @param header The header for the table
	 */
	public TaskTableModel(String[] header) {
		this.header = header;
		data = new ArrayList<Object[]>();
	}
	
	
	@Override
	public int getColumnCount() {
		return header.length;
	}
	
	/**
	 * @param i The index of the header to return
	 * @return The name of the header as position i
	 */
	public String getColumnName(int i) {
		return header[i];
	}
	
	/**
	 * Changes the current data of this table to
	 * newData
	 * @param newData The new data of this table
	 */
	public void changeData(ArrayList<Object[]> newData) {
		data = newData;
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}
	
	/**
	 * Sets a new header for this table
	 * @param h The new header
	 */
	public void setHeader(String[] h) {
		h = header;
	}

	@Override
	public Object getValueAt(int x, int y) {
		if (x <= data.size() && x >= 0 && y >= 0 && y <= header.length)
			return data.get(x)[y];
		return null;
	}

}
