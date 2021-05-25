package src.components;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SongsTableModel extends AbstractTableModel {
    private ArrayList<ArrayList<String>> songs;
    private String[] columnNames;

    public SongsTableModel(ArrayList<ArrayList<String>> songs) {
        super();
        columnNames = new String[] {"", "SongName", "Duration"};
        this.songs = songs;
    }

    @Override
    public int getRowCount() {
        return songs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 0; //Or whatever column index you want to be editable
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ArrayList<String> song = songs.get(rowIndex);
        switch(columnIndex) {
            case 0: return song.get(0);
            case 1: return song.get(1);
            case 2: return song.get(2);
            default: return null;
        }
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public void switchTableList(ArrayList<ArrayList<String>> songs) {
        this.songs.clear();
        this.songs = songs;
    }

}
