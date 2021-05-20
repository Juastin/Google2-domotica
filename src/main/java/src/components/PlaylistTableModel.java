package src.components;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class PlaylistTableModel extends AbstractTableModel {
    private ArrayList<ArrayList<String>> songs;
    private String[] columnNames;

    public PlaylistTableModel(ArrayList<ArrayList<String>> songs) {
        super();
        columnNames = new String[] {"Add", "SongName", "Duration"};
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
        return columnIndex == 0 || columnIndex == 1; //Or whatever column index you want to be editable
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ArrayList<String> song = songs.get(rowIndex);
        switch(columnIndex) {
            case 0: return song.get(0);
            case 1: return song.get(2);
            case 2: return song.get(3);
            default: return null;
        }
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
}
