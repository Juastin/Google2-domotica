package src.components;

import src.components.MusicButton;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class SongsTableCellRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
    private JButton jb;

    public SongsTableCellRenderer(ActionListener listener) {
        jb = new MusicButton(listener, "⏵", 20);
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return jb;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return jb;
    }

    public JButton getJb() {
        return jb;
    }
} // end class TableCell extends AbstractCellEditor implements TableCellEditor,
// TableCellRenderer
//source: https://developer-salieri.tistory.com/199

