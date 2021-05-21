package src.views;
import src.components.MusicButton;
import src.components.MusicMenuButton;
import src.core.Audio;
import src.core.Container;
import src.core.View;
import src.core.Navbar;
import src.system.Queries;
import src.system.User;
import src.views.musicMenuPanels.MusicMenuNewPlaylist;
import src.views.musicMenuPanels.MusicMenuPlaylist;
import src.views.musicMenuPanels.MusicMenuQueue;
import src.views.musicMenuPanels.MusicMenuSongs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MusicMenuView extends View implements ActionListener {
    private JPanel
            jpTop,
            jpCenter, jpSongs, jpPlaylist, jpQueue, jpNewPlaylist,
            jpBottom, jpMiddle, jpMiddleTop, jpMiddleBottom, jpRight;
    private JButton jbList, jbPrevious, jbPlay, jbNext, jbSongs, jbPlaylist, jbQueue, jbNewPlaylist;
    private JSlider jsPlayTime;
    private JLabel jlTitle, jlCurrentPlayTime, jlMelodyLength;
    private String currentPlayTime, melodyLength;
    private Color customGray = new Color(250, 250, 250);
    private Color customGray2 = new Color(189, 188, 188);

    private int id;
    private String name;

    public MusicMenuView(Container container, String name) {
        super(container, name);
        setLayout(new BorderLayout());
        setVisible(false);

        // NAVBAR
        Navbar navbar = new Navbar(this);

        // Main Panel
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // Top bar panel
        jpTop = new JPanel();
        jpTop.setLayout(new GridLayout(1,4));
        jpTop.setPreferredSize(new Dimension(0, 50));
        jpTop.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, customGray2));
        jpTop.setBackground(customGray);
        // COMPONENTS
        jbSongs = new MusicMenuButton(this, "Nummers", 16);
        jbPlaylist = new MusicMenuButton(this, "Afspeellijst nummers", 16);
        jbQueue = new MusicMenuButton(this, "Afspeelwachtrij", 16);
        jbNewPlaylist = new MusicMenuButton(this, "➕ Nieuwe afspeellijst", 16);
        jbNewPlaylist.setBorderPainted(true);
        jbNewPlaylist.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, customGray2));
        /* Add */
        jpTop.add(jbSongs);
        jpTop.add(jbPlaylist);
        jpTop.add(jbQueue);
        jpTop.add(jbNewPlaylist);

        // Center panel (changeable panels with the buttons in top bar panel)
        jpCenter = new JPanel();
        jpCenter.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, customGray2));
        jpCenter.setLayout(new BorderLayout());
        // COMPONENTS
//        jpSongs = new MusicMenuSongs();
//        jpPlaylist = new MusicMenuPlaylist();
//        jpQueue = new MusicMenuQueue();
       jpNewPlaylist = new MusicMenuNewPlaylist();


        // Bottom bar panel
        jpBottom = new JPanel();
        jpBottom.setBackground(customGray);
        jpBottom.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        jpBottom.setLayout(new GridLayout(1,3));
        jpBottom.setPreferredSize(new Dimension(0, 75));
        // COMPONENTS COLUMN 1
        jlTitle = new JLabel("♫ Titel melodie");
        jlTitle.setFont(new Font(jlTitle.getFont().getFamily(), Font.PLAIN, 20));

        // COMPONENTS COLUMN 2
        jpMiddle = new JPanel();
        jpMiddle.setBackground(customGray);
        jpMiddle.setLayout(new BorderLayout());
        /* Components top half */
        jpMiddleTop = new JPanel();
        jpMiddleTop.setBackground(customGray);
        jpMiddleTop.setLayout(new FlowLayout());
        jbPrevious = new MusicButton(this, "⏮", 30);
        jbPlay = new MusicButton(this, "⏵", 30);
        jbNext = new MusicButton(this, "⏭", 30);
        /* Components bottom half */
        jpMiddleBottom = new JPanel();
        jpMiddleBottom.setBackground(customGray);
        jpMiddleBottom.setLayout(new BorderLayout());
        jpMiddleBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 20));
        jsPlayTime = new JSlider(JSlider.HORIZONTAL, 0, 168, 51);
        jsPlayTime.setBackground(customGray);
        jsPlayTime.setMinorTickSpacing(1);
        jsPlayTime.setSnapToTicks(true);
//        jsPlayTime.setEnabled(false);
        jsPlayTime.addChangeListener(e -> {
            currentPlayTime = String.format("%02d:%02d", (int) Math.floor(jsPlayTime.getValue() / 60), jsPlayTime.getValue() - (int) Math.floor(jsPlayTime.getValue() / 60) * 60);
            jlCurrentPlayTime.setText(currentPlayTime);
        });
        melodyLength = String.format("%02d:%02d", (int) Math.floor(jsPlayTime.getMaximum() / 60), jsPlayTime.getMaximum() - (int) Math.floor(jsPlayTime.getMaximum() / 60) * 60);
        currentPlayTime = String.format("%02d:%02d", (int) Math.floor(jsPlayTime.getValue() / 60), jsPlayTime.getValue() - (int) Math.floor(jsPlayTime.getValue() / 60) * 60);
        jlCurrentPlayTime = new JLabel(String.valueOf(currentPlayTime));
        jlMelodyLength = new JLabel(melodyLength);
        jlCurrentPlayTime.setPreferredSize(new Dimension(30,0));
        jlMelodyLength.setPreferredSize(new Dimension(30,0));

        // COMPONENTS COLUMN 3
        jpRight = new JPanel();
        jpRight.setBackground(customGray);
        jpRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jpRight.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jpRight.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        jbList = new MusicButton(this, "⋮☰", 30);

        // Add
        /* Column 1 */
        jpBottom.add(jlTitle);
        /* Column 2 */
        jpBottom.add(jpMiddle);
        jpMiddle.add(jpMiddleTop, BorderLayout.NORTH);
        jpMiddleTop.add(jbPrevious);
        jpMiddleTop.add(jbPlay);
        jpMiddleTop.add(jbNext);
        jpMiddle.add(jpMiddleBottom, BorderLayout.SOUTH);
        jpMiddleBottom.add(jlCurrentPlayTime, BorderLayout.WEST);
        jpMiddleBottom.add(jsPlayTime, BorderLayout.CENTER);
        jpMiddleBottom.add(jlMelodyLength, BorderLayout.EAST);
        /* Column 2 */
        jpBottom.add(jpRight);
        jpRight.add(jbList);

        // LAYOUT
        main.add(jpTop, BorderLayout.NORTH);
        main.add(jpCenter, BorderLayout.CENTER);
        main.add(jpBottom, BorderLayout.SOUTH);
        add(main, BorderLayout.CENTER);
        add(navbar, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbList) {
            this.changeFocus("MusicPlayerView");
        }
        if (e.getSource() == jbPrevious) {
        }
        if (e.getSource() == jbPlay) {
            if (!MusicPlayerView.isPlayMusic()) {
                jbPlay.setText("⏸");
                MusicPlayerView.setPlayMusic(true);
            } else {
                jbPlay.setText("⏵");
                MusicPlayerView.setPlayMusic(false);
            }
        }
        if (e.getSource() == jbNext) {

        }
        if (e.getSource() == jbSongs) {
            jpSongs = new MusicMenuSongs();
            changeMusicPanel(jpSongs);
        }
        if (e.getSource() == jbPlaylist) {
            refreshPlaylistView();
        }
        if (e.getSource() == jbQueue) {
            jpQueue = new MusicMenuQueue();
            changeMusicPanel(jpQueue);
        }
        if (e.getSource() == jbNewPlaylist) {
            changeMusicPanel(jpNewPlaylist);
            ((MusicMenuNewPlaylist)jpNewPlaylist).updateGUI();
        }
        Audio.play("click.wav");
    }

    public void refreshPlaylistView() {
        ArrayList<ArrayList<String>> playlistData = Queries.getPlaylistData(User.getUsername());
        try {
            id = Integer.parseInt(playlistData.get(0).get(0));
            name = playlistData.get(0).get(1);
        } catch (IndexOutOfBoundsException ie){
            id = 0;
            name = "Geen playlist beschikbaar";
        }
        jpPlaylist = new MusicMenuPlaylist(this, id, name);
        changeMusicPanel(jpPlaylist);
    }

    @Override
    public void onFocus(ArrayList<String> parameters) {
            /* Add default visible panel */
            jpSongs = new MusicMenuSongs();
            changeMusicPanel(jpSongs);
            if (MusicPlayerView.isPlayMusic()) {
                jbPlay.setText("⏸");
            } else {
                jbPlay.setText("⏵");
            }
        // DO SOMETHING IF A PARAMETER IS GIVEN
        if (parameters.size()>0) {
            if (parameters.get(0).equals("show jpNewPlaylist")) {
                changeMusicPanel(jpNewPlaylist);
                ((MusicMenuNewPlaylist)jpNewPlaylist).updateGUI();
            }
        }
    }

    @Override
    public void onShadow() {}

    @Override
    public void onTick(long now) {}

    public void changeMusicPanel(JPanel changeToPanel) {
        try {
            jpCenter.getComponent(0).setVisible(false);
            jpCenter.remove(0);
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        jpCenter.add(changeToPanel, BorderLayout.CENTER);
        changeToPanel.setVisible(true);
        repaint();
    }
}