package src.views;

import src.components.SongsTableCellRenderer;
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

public class MusicMenuPlaylist extends JPanel implements ActionListener {
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
        jtSongs = new JTable(new SongsTableModel(playlistSongsList));

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
