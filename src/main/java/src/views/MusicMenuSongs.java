package src.views;

import src.components.SongsTableCellRenderer;
import src.components.SongsTableModel;
import src.core.Audio;
import src.system.Queries;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MusicMenuSongs extends JPanel implements ActionListener {
    private JTable jtSongs;
    private ArrayList<ArrayList<String>> songsList;
    private SongsTableCellRenderer songTableCell;
    private JScrollPane scroll;

    public MusicMenuSongs() {
        setVisible(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        songsList = Queries.getAllSongs();

        // Test scroller
        /*
        ArrayList<String> test = new ArrayList<>();
        test.add("4");
        test.add("null");
        test.add("test");
        test.add("145");
        ArrayList<String> test2 = new ArrayList<>();
        test2.add("1");
        test2.add("null");
        test2.add("liedje");
        test2.add("60");
        songsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                songsList.add(test);
            } else {
                songsList.add(test2);
            }
        }
        */

        // Table songs
        jtSongs = new JTable(new SongsTableModel(songsList));

        // Styling for the Table
        jtSongs.setOpaque(false);
        jtSongs.setShowGrid(false);
        ((DefaultTableCellRenderer)jtSongs.getDefaultRenderer(Object.class)).setOpaque(false);
        ((DefaultTableCellRenderer)jtSongs.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.LEFT);
        UIManager.put("Table.focusCellHighlightBorder",
                new BorderUIResource.LineBorderUIResource(new Color(0, 0, 0, 0)));
        jtSongs.getTableHeader().setReorderingAllowed(false);
        jtSongs.getTableHeader().setResizingAllowed(false);
        songTableCell = new SongsTableCellRenderer(this);
        jtSongs.getColumnModel().getColumn(0).setCellRenderer(songTableCell);
        jtSongs.getColumnModel().getColumn(0).setCellEditor(songTableCell);
        jtSongs.getColumnModel().getColumn(0).setMaxWidth(50);
        jtSongs.getColumnModel().getColumn(1).setMaxWidth(500);
        jtSongs.getColumnModel().getColumn(2).setMaxWidth(330);

        // Add
        add(jtSongs.getTableHeader());
        add(jtSongs);

        /* Scroller */
        scroll = new JScrollPane(jtSongs);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setViewportBorder(BorderFactory.createEmptyBorder());
        add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == songTableCell.getJb()) {
            int test = jtSongs.getSelectedRow();
            System.out.println(String.format("\nSong: %s\n" +
            "SongId: %s", jtSongs.getValueAt(test, 1), jtSongs.getValueAt(test, 0)));
        }
        Audio.play("click.wav");
    }
}

