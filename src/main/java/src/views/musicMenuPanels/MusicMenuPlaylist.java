package src.views.musicMenuPanels;

import src.components.SongsTableCellRenderer;
import src.components.SongsTableLayout;
import src.components.SongsTableModel;
import src.core.Audio;
import src.system.Queries;
import src.system.User;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MusicMenuPlaylist extends JPanel {
    private JTable jtSongs;
    private JPanel center;
    private JLabel jlTitel;
    private ArrayList<ArrayList<String>> playlistSongsList;
    private SongsTableCellRenderer songTableCell;
    private JScrollPane scroll;

    public MusicMenuPlaylist(int id, String name) {
        setVisible(false);
        setLayout(new BorderLayout());

        playlistSongsList = Queries.getPlaylistSongsList(id);
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
        playlistSongsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                playlistSongsList.add(test);
            } else {
                playlistSongsList.add(test2);
            }
        }
        */

        // Top of panel Title
        jlTitel = new JLabel("Naam afspeellijst: " + name);
        jlTitel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jlTitel.setFont(jlTitel.getFont().deriveFont(20.0f));

        // Center panel table
        center = new JPanel();
        center.setLayout(new BorderLayout());

        // Table songs
        songTableCell = new SongsTableCellRenderer();
        jtSongs = new SongsTableLayout(new SongsTableModel(playlistSongsList), songTableCell);

        // Add
        add(jlTitel, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        center.add(jtSongs.getTableHeader(), BorderLayout.NORTH);
        center.add(jtSongs, BorderLayout.CENTER);
        /* Scroller */
        scroll = new JScrollPane(jtSongs);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setViewportBorder(BorderFactory.createEmptyBorder());
        center.add(scroll);
    }
}
