package src.views.musicMenuPanels;

import src.components.*;
import src.core.Audio;
import src.system.Queries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MusicMenuNewPlaylist extends JPanel implements ActionListener {
    private JLabel jlPlaylistName;
    private JTextField jtPlaylistName;
    private JButton jbMaken, jbAnnuleren;
    private JTable jtSongs;
    private JPanel top, center, bottom;
    private ArrayList<ArrayList<String>> songsList;
    private JScrollPane scroll;
    private SongsTableCellRenderer songTableCell;

    public MusicMenuNewPlaylist() {
        setVisible(false);
        setLayout(new BorderLayout());
        setBackground(Color.ORANGE);

        // Top panel
        top =  new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT));
        jlPlaylistName = new JLabel("Naam afspeellijst: ");
        jlPlaylistName.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        jtPlaylistName = new JTextField(20);
        jlPlaylistName.setFont(jlPlaylistName.getFont().deriveFont(20.0f));
        /* Add */
        top.add(jlPlaylistName);
        top.add(jtPlaylistName);

        // Center panel (table)
        center = new JPanel();
        center.setLayout(new BorderLayout());
//        center.setBackground(Color.RED);
        songsList = Queries.getAllSongs();
        songTableCell = new SongsTableCellRenderer();
        jtSongs = new PlaylistTableLayout(new PlaylistTableModel(songsList));
        /* Add */
        center.add(jtSongs.getTableHeader(), BorderLayout.NORTH);
        center.add(jtSongs, BorderLayout.CENTER);

        // Bottom panel
        bottom =  new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jbAnnuleren = new CButton(this, "Annuleren", Color.black, Color.white);
        jbAnnuleren.setFont(new Font(jbAnnuleren.getFont().getFamily(), Font.PLAIN, 16));
        jbMaken = new CButton(this, "Maken", Color.black, Color.white);
        jbMaken.setFont(new Font(jbMaken.getFont().getFamily(), Font.PLAIN, 16));
        /* Add */
        bottom.add(jbAnnuleren);
        bottom.add(jbMaken);

        // MAIN ADD
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbAnnuleren) {
        }
        if (e.getSource() == jbMaken) {
        }
        Audio.play("click.wav");
    }
}
