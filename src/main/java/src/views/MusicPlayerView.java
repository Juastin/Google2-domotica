package src.views;
import src.components.MusicButton;
import src.components.PlayMusic;
import src.components.Songs;
import src.core.Audio;
import src.core.Container;
import src.core.View;
import src.core.Navbar;
import src.system.Queries;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.io.IOException;
import java.util.ArrayList;

public class MusicPlayerView extends View implements ActionListener {
    private JPanel jpTop, jpCenter, jpBottom, jpLeft, jpMiddle, jpRight;
    private JButton jbList, jbPrevious, jbPlay, jbNext;
    private JSlider jsPlayTime;
    private JLabel jlTitle, jlCurrentPlayTime, jlMelodyLength;
    private String currentPlayTime, melodyLength;
    private static boolean playing=false;
    private Songs song = new Songs();
    private PlayMusic music = new PlayMusic();

    private static int currentSong;
    private static boolean playMusic = false;

    private static MusicUpdate musicUpdate = new MusicUpdate();

    public MusicPlayerView(Container container, String name) {
        super(container, name);
        setLayout(new BorderLayout());
        setVisible(false);

        // NAVBAR
        Navbar navbar = new Navbar(this);

        // Main Panel
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // TOP
        jpTop = new JPanel();
        jpTop.setLayout(new BorderLayout());
        jpTop.setPreferredSize(new Dimension(jpTop.getWidth(), 400));
        //COMPONENTS
        /* Top components */
        jlTitle = new JLabel("<html><div style='text-align:center;'><p style='margin: 0; font-size: 4em'>♫</p><p style='margin: 0;font-size: 1.5em'>"+ musicUpdate.getCurrentSongName() +"</p></div></html>", JLabel.CENTER);
        /* Add */
        jpTop.add(jlTitle, BorderLayout.CENTER);

        //MIDDLE
        jpCenter = new JPanel();
        jpCenter.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 60));
        jpCenter.setLayout(new BorderLayout());
        jpCenter.setPreferredSize(new Dimension(jpCenter.getWidth(), 70));
        // COMPONENTS
        jsPlayTime = new JSlider(JSlider.HORIZONTAL, musicUpdate.getCurrentSongDuration());
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
        /* Add */
        jpCenter.add(jlCurrentPlayTime, BorderLayout.WEST);
        jpCenter.add(jsPlayTime, BorderLayout.CENTER);
        jpCenter.add(jlMelodyLength, BorderLayout.EAST);

        // BOTTOM
        jpBottom = new JPanel();
        jpBottom.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        jpBottom.setLayout(new BorderLayout());
        // COMPONENTS
        /* Panels */
        jpLeft = new JPanel();
        jpMiddle = new JPanel();
        jpRight = new JPanel();
        jpRight.setPreferredSize(new Dimension(60, 0));
        /* Buttons */
        jbList = new MusicButton(this, "⋮☰", 30);
        jbList.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        jbPrevious = new MusicButton(this, "⏮", 40);
        jbPlay = new MusicButton(this, "⏵", 40);
        jbNext = new MusicButton(this, "⏭", 40);
        /* Add */
        jpLeft.add(jbList);
        jpMiddle.add(jbPrevious);
        jpMiddle.add(jbPlay);
        jpMiddle.add(jbNext);
        /* Added panels */
        jpBottom.add(jpLeft, BorderLayout.WEST);
        jpBottom.add(jpMiddle, BorderLayout.CENTER);
        jpBottom.add(jpRight, BorderLayout.EAST);

        //LAYOUT
        main.add(jpTop, BorderLayout.NORTH);
        main.add(jpCenter, BorderLayout.CENTER);
        main.add(jpBottom, BorderLayout.SOUTH);
        add(main, BorderLayout.CENTER);
        add(navbar, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbList) {
            this.changeFocus("MusicMenuView");
        }
        if (e.getSource() == jbPrevious) {
            musicUpdate.previousSong();
        }
        if (e.getSource() == jbPlay) {
            if (!musicUpdate.isPlaying()) {
                jbPlay.setText("⏸");
                musicUpdate.setPlaying(true);
            } else {
                jbPlay.setText("⏵");
                musicUpdate.setPlaying(false);
            }

            playing = !playing;
        }

        if (e.getSource() == jbNext) {
            musicUpdate.nextSong();
        }
        updateSongInfoView();
        Audio.play("click.wav");
    }

    public void updateSongInfoView() {
        jlTitle.setText("<html><div style='text-align:center;'><p style='margin: 0; font-size: 4em'>♫</p><p style='margin: 0;font-size: 1.5em'>"+ musicUpdate.getCurrentSongName() +"</p></div></html>");
        jsPlayTime.setMaximum(musicUpdate.getCurrentSongDuration());
        melodyLength = String.format("%02d:%02d", (int) Math.floor(jsPlayTime.getMaximum() / 60), jsPlayTime.getMaximum() - (int) Math.floor(jsPlayTime.getMaximum() / 60) * 60);
        jlMelodyLength.setText(melodyLength);
        jsPlayTime.setValue(musicUpdate.getCurrentSongTime());
    }

    public static boolean isPlayMusic() {
        return playMusic;
    }

    public static void setPlayMusic(boolean playMusic) {
        MusicPlayerView.playMusic = playMusic;
    }

    public void onFocus(ArrayList<String> parameters) {
        jsPlayTime.setValue(musicUpdate.getCurrentSongTime());
        if (!musicUpdate.isPlaying()) {
            jbPlay.setText("⏸");
            musicUpdate.setPlaying(true);
        } else {
            jbPlay.setText("⏵");
            musicUpdate.setPlaying(false);
        }
    }

    @Override
    public void onShadow() {}

    @Override
    public void onTick(long now) {
        if(playing){
        try {
            music.sendnotes(song.getNummer1());
    } catch (InterruptedException | IOException interruptedException) {
        interruptedException.printStackTrace();
            }
        } if(!playing) {
            try {
                music.pause();
            } catch (IOException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}