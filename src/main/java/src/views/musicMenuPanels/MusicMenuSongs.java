package src.views.musicMenuPanels;

import src.components.SongsTableCellRenderer;
import src.components.SongsTableLayout;
import src.components.SongsTableModel;
import src.core.Audio;
import src.system.Queries;
import src.views.MusicUpdate;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MusicMenuSongs extends JPanel {
    private JTable jtSongs;
    private ArrayList<ArrayList<String>> songsList;
    private SongsTableCellRenderer songTableCell;
    private JScrollPane scroll;

    public MusicMenuSongs() {
        setVisible(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
        songsList = MusicUpdate.getSongsList();
        songTableCell = new SongsTableCellRenderer();
        jtSongs = new SongsTableLayout(new SongsTableModel(songsList), songTableCell);

        // Add
        add(jtSongs.getTableHeader());
        add(jtSongs);

        /* Scroller */
        scroll = new JScrollPane(jtSongs);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setViewportBorder(BorderFactory.createEmptyBorder());
        add(scroll);
    }
}

