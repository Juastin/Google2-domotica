package src.components;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.*;
import java.awt.*;

public class PlaylistTableLayout extends JTable {
    public PlaylistTableLayout(AbstractTableModel model) {
        super(model);
        setOpaque(false);
        setShowGrid(false);

        ((DefaultTableCellRenderer)getDefaultRenderer(Object.class)).setOpaque(false);
        ((DefaultTableCellRenderer)getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.LEFT);
        UIManager.put("Table.focusCellHighlightBorder",
                new BorderUIResource.LineBorderUIResource(new Color(0, 0, 0, 0)));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
//        getColumnModel().getColumn(0).setCellEditor(this.getDefaultEditor(Boolean.class));
        getColumnModel().getColumn(0).setMaxWidth(50);
        getColumnModel().getColumn(1).setMaxWidth(500);
        getColumnModel().getColumn(2).setMaxWidth(330);
    }
}
