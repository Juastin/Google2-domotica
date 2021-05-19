package src.views.musicMenuPanels;

import src.components.SongsTableCellRenderer;
import src.components.SongsTableLayout;
import src.components.SongsTableModel;
import src.core.Audio;
import src.system.Queries;
import src.system.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MusicMenuPlaylist extends JPanel implements ActionListener {
    private JTable jtSongs;
    private JPanel top, center;
    private JLabel jlTitle;
    private ArrayList<ArrayList<String>> playlistSongsList, playlistData;
    private SongsTableCellRenderer songTableCell;
    private JScrollPane scroll;
    private JComboBox comboList;
    private SongsTableModel tableModle;

    public MusicMenuPlaylist(int id, String name) {
        setVisible(false);
        setLayout(new BorderLayout());

        playlistSongsList = Queries.getPlaylistSongsList(id);
        playlistData = Queries.getPlaylistData(User.getUsername());

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
        top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jlTitle = new JLabel("Naam afspeellijst: ");
        jlTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jlTitle.setFont(jlTitle.getFont().deriveFont(20.0f));
        top.add(jlTitle);

        if (playlistData != null) {
            comboList = new JComboBox<String>();
            comboList.setMaximumSize(new Dimension(100, 20));
            for (ArrayList<String> playlistName : playlistData) {
                comboList.addItem(playlistName.get(1));
            }
            comboList.addActionListener(this);
            comboList.setEditable(false);
            top.add(comboList, BorderLayout.NORTH);
        }

        // Center panel table
        center = new JPanel();
        center.setLayout(new BorderLayout());

        // Table songs
        if (playlistSongsList != null) {
            songTableCell = new SongsTableCellRenderer();
            tableModle = new SongsTableModel(playlistSongsList);
            jtSongs = new SongsTableLayout(tableModle, songTableCell);
            center.add(jtSongs.getTableHeader(), BorderLayout.NORTH);
            center.add(jtSongs, BorderLayout.CENTER);
        }

        // Add
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        /* Scroller */
        scroll = new JScrollPane(jtSongs);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setViewportBorder(BorderFactory.createEmptyBorder());
        center.add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboList) {
            String option = (String) comboList.getSelectedItem();
            playlistSongsList = Queries.getPlaylistSongsList(option);
            tableModle.switchTableList(playlistSongsList);
            System.out.println(playlistSongsList);
        }
        revalidate();
        repaint();
    }

}
