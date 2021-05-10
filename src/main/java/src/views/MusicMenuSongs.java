package src.views;

import src.components.SongsTableCellRenderer;
import src.components.SongsTableLayout;
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
        songTableCell = new SongsTableCellRenderer(this);
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

