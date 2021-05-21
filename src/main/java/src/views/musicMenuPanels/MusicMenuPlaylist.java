package src.views.musicMenuPanels;

import src.components.CButton;
import src.components.SongsTableCellRenderer;
import src.components.SongsTableLayout;
import src.components.SongsTableModel;
import src.core.Audio;
import src.core.Logging;
import src.views.MusicMenuView;
import src.system.Queries;
import src.system.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class MusicMenuPlaylist extends JPanel implements ActionListener {
    private JTable jtSongs;
    private JPanel top, center;
    private JLabel jlTitle;
    private ArrayList<ArrayList<String>> playlistSongsList, playlistData;
    private SongsTableCellRenderer songTableCell;
    private JScrollPane scroll;
    private JComboBox comboList;
    private SongsTableModel tableModle;
    private CButton cbDeletePlaylist, toNewPlaylist;
    private MusicMenuView parent;

    public MusicMenuPlaylist(MusicMenuView parent, int id) {
        this.parent = parent;
        setVisible(false);
        setLayout(new BorderLayout());

        playlistSongsList = Queries.getPlaylistSongsList(id);
        playlistData = Queries.getPlaylistData(User.getUsername());

        // Top of panel Title
        top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jlTitle = new JLabel();
        jlTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jlTitle.setFont(jlTitle.getFont().deriveFont(20.0f));
        top.add(jlTitle);

        if (!playlistData.isEmpty()) {
            comboList = new JComboBox<String>();
            comboList.setMaximumSize(new Dimension(100, 20));
            for (ArrayList<String> playlistName : playlistData) {
                comboList.addItem(playlistName.get(1));
            }
            comboList.addActionListener(this);
            comboList.setEditable(false);
            top.add(comboList, BorderLayout.NORTH);

            cbDeletePlaylist = new CButton(this, "Delete " + playlistData.get(0).get(1), Color.red, Color.white);
            top.add(cbDeletePlaylist, BorderLayout.NORTH);

            jlTitle.setText("Naam afspeellijst: ");

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

            /* Scroller */
            scroll = new JScrollPane(jtSongs);
            scroll.setBorder(BorderFactory.createEmptyBorder());
            scroll.setViewportBorder(BorderFactory.createEmptyBorder());
            center.add(scroll);

            // Add
            add(center, BorderLayout.CENTER);
        } else {
            jlTitle.setText("Je hebt nog geen afspeellijsten");
            toNewPlaylist = new CButton(this, "Nieuwe afspeellijst maken!", Color.white, new Color(0, 176, 36));
            top.add(toNewPlaylist);
        }

        add(top, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboList) {
            String option = (String) comboList.getSelectedItem();
            playlistSongsList = Queries.getPlaylistSongsList(option);
            tableModle.switchTableList(playlistSongsList);
            tableModle.fireTableDataChanged();
            cbDeletePlaylist.setText("Verwijder " + comboList.getSelectedItem().toString());
        }

        if (e.getSource() == cbDeletePlaylist) {
            String playlist_name = comboList.getSelectedItem().toString();
            int playlist_id = 0;

            int choice = JOptionPane.showConfirmDialog(this, "Afspeellijst \"" + playlist_name + "\" verwijderen?", "Afspeellijst verwijderen", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // FIND PLAYLIST ID IN ARRAY
                for (ArrayList<String> playlist: playlistData) {
                    if (playlist.get(1).equals(playlist_name)) {
                        playlist_id = Integer.parseInt(playlist.get(0));
                        break;
                    }
                }

                Queries.deletePlaylist(playlist_id);
                Audio.play("success_1.wav");
                JOptionPane.showMessageDialog(this, "Afspeellijst is verwijderd");
                Logging.logThis(User.getUsername() + " deleted playlist " + playlist_name);
                parent.refreshPlaylistView();
            }
        }

        if (e.getSource() == toNewPlaylist) {
            parent.onFocus(new ArrayList<String>(Arrays.asList("show jpNewPlaylist")));
        }

        revalidate();
        repaint();
        Audio.play("click.wav");
    }

}
