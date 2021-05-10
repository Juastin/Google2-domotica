package src.views;

import src.components.CButton;
import src.components.MusicButton;
import src.core.Audio;

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
    private ArrayList<ArrayList<String>> SongsList;
    private JScrollPane scroll;

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

        // Center panel


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

        // Center panel table
        center = new JPanel();
        center.setLayout(new BorderLayout());

        // MAIN ADD
        add(top, BorderLayout.NORTH);
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
