package src.views;
import src.system.Queries;
import src.components.CButton;
import src.core.Audio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MusicMenuNewPlaylist extends JPanel implements ActionListener {
    private ArrayList<ArrayList<String>> dbSongs, playlistSongs = new ArrayList<ArrayList<String>>();
    private JPanel jpLeft, jpRight, displayedSongs, displayedPlaylist;
    private ArrayList<CButton> inPlaylist, inSongList;

    public MusicMenuNewPlaylist() {
        setVisible(false);
        GridLayout userGridLayout = new GridLayout(1, 2);
        setBorder(new EmptyBorder(5,5,5,5));
        userGridLayout.setHgap(5);
        setLayout(userGridLayout);
        Border myborder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        inPlaylist = new ArrayList<CButton>();
        inSongList = new ArrayList<CButton>();

        // FAKE TABLE SETTINGS
        int height = 15;
        int fw1 = 50;
        int fw2 = 150;
        int fw3 = 150;

        // LEFT
        jpLeft = new JPanel();
        jpLeft.setBorder(myborder);
        JPanel jpLeftTop = new JPanel();
        jpLeftTop.setLayout(new GridLayout(2, 2));
        jpLeftTop.add(new JLabel("Afspeellijst naam:"));
        JTextField jtNewPlaylist = new JTextField();
        jpLeftTop.add(jtNewPlaylist);
        jpLeftTop.add(new JLabel("In afspeellijst"));
        jpLeftTop.add(new JLabel(""));
        jpLeftTop.setPreferredSize(new Dimension(400, 40));
        jpLeft.add(jpLeftTop);
        // SONGS
        JLabel label1 = new JLabel("");
        label1.setPreferredSize(new Dimension(fw1, height));
        JLabel label2 = new JLabel("Naam");
        label2.setBorder(new EmptyBorder(0, 10, 0, 0));
        label2.setPreferredSize(new Dimension(fw2, height));
        JLabel label3 = new JLabel("Lengte");
        label3.setPreferredSize(new Dimension(fw3, height));
        jpLeft.add(label1);
        jpLeft.add(label2);
        jpLeft.add(label3);

        // RIGHT
        jpRight = new JPanel();
        jpRight.setBorder(myborder);
        JPanel jpRightTop = new JPanel();
        jpRightTop.setLayout(new GridLayout(2, 2));
        jpRightTop.add(new JLabel("Toe te voegen nummers"));
        jpRightTop.add(new JLabel(""));
        jpRightTop.add(new JLabel(""));
        jpRightTop.add(new JLabel(""));
        jpRightTop.setPreferredSize(new Dimension(400, 40));
        jpRight.add(jpRightTop);
        // Table songs
        JLabel label4 = new JLabel("");
        label4.setPreferredSize(new Dimension(fw1, height));
        JLabel label5 = new JLabel("Naam");
        label5.setBorder(new EmptyBorder(0, 10, 0, 0));
        label5.setPreferredSize(new Dimension(fw2, height));
        JLabel label6 = new JLabel("Lengte");
        label6.setPreferredSize(new Dimension(fw3, height));
        jpRight.add(label4);
        jpRight.add(label5);
        jpRight.add(label6);

        displayedPlaylist = new JPanel();
        displayedPlaylist.setPreferredSize(new Dimension(400, 290));
        jpLeft.add(displayedPlaylist);
        displayedSongs = new JPanel();
        displayedSongs.setPreferredSize(new Dimension(400, 290));
        jpRight.add(displayedSongs);

        add(jpLeft);
        add(jpRight);
        updateGUI();
    }

    private void updateGUI() {
        displayedPlaylist.removeAll();
        displayedSongs.removeAll();

        dbSongs = Queries.getAllSongs();

        // FAKE TABLE SETTINGS
        int height = 15;
        int fw1 = 50;
        int fw2 = 150;
        int fw3 = 150;

        inPlaylist = new ArrayList<CButton>();
        for (ArrayList<String> song: playlistSongs) {
            CButton button = new CButton(this, "-", Color.black, Color.white);
            inPlaylist.add(button);
            button.setPreferredSize(new Dimension(fw1, height));
            JLabel title = new JLabel(song.get(2));
            title.setBorder(new EmptyBorder(0, 10, 0, 0));
            title.setPreferredSize(new Dimension(fw2, height));
            JLabel length = new JLabel(song.get(3));
            length.setPreferredSize(new Dimension(fw3, height));
            displayedPlaylist.add(button);
            displayedPlaylist.add(title);
            displayedPlaylist.add(length);
        }

        inSongList = new ArrayList<CButton>();
        for (ArrayList<String> song: dbSongs) {
            CButton button = new CButton(this, "+", Color.black, Color.white);
            inSongList.add(button);
            button.setPreferredSize(new Dimension(fw1, height));
            JLabel title = new JLabel(song.get(2));
            title.setBorder(new EmptyBorder(0, 10, 0, 0));
            title.setPreferredSize(new Dimension(fw2, height));
            JLabel length = new JLabel(song.get(3));
            length.setPreferredSize(new Dimension(fw3, height));
            displayedSongs.add(button);
            displayedSongs.add(title);
            displayedSongs.add(length);
        }

        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        int i;

        // CHECK IF PRESSED BUTTON IS A PLAYLIST SONG
        i = 0;
        for (CButton button: inPlaylist) {
            if (source==button) {
                playlistSongs.remove(i);
            }
            i++;
        }

        // CHECK IF PRESSED BUTTON IS A SONG TO BE ADDED
        i = 0;
        for (CButton button: inSongList) {
            if (source==button) {
                playlistSongs.add(dbSongs.get(i));
            }
            i++;
        }

        updateGUI();
        Audio.play("click.wav");
    }
}
