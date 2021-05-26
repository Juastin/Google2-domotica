package src.components;

import src.components.MusicButton;
import src.core.Audio;
import src.views.MusicUpdate;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SongsTableCellRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
    private JButton jb;

    public SongsTableCellRenderer() {
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String tag;
        if (MusicUpdate.getIndex()==row) {
            if (MusicUpdate.isPlaying()) {
                tag = "⏸";
            } else {
                tag = "⏵";
            }
        } else {
            tag = "";
        }

        jb = new MusicButton(tag, 20);
        return jb;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        String tag = (MusicUpdate.getIndex()==row) ? "⏸" : "";
        jb = new MusicButton(tag, 20);
        return jb;
    }

    public JButton getJb() {
        return jb;
    }
} // end class TableCell extends AbstractCellEditor implements TableCellEditor,
// TableCellRenderer
//source: https://developer-salieri.tistory.com/199

