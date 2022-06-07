package gui;

import java.io.File;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class GameTableModel extends AbstractTableModel {
	
	private ArrayList<File> files;
	
	public GameTableModel(ArrayList<File> files) {
		this.files = files;
	}
	@Override
	public int getRowCount() {
		return files.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		File file = files.get(rowIndex);
		return file.getName();
	}

}
