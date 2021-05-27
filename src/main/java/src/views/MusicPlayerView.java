package src.views;
import src.components.MusicButton;
import src.components.MusicPlayerController;
import src.components.PlayMusic;
import src.components.Songs;
import src.core.*;
import src.core.Container;
import src.system.Queries;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.io.IOException;
import java.util.ArrayList;

public class MusicPlayerView extends MusicPlayerController implements ActionListener {
    private JPanel jpTop, jpCenter, jpBottom, jpLeft, jpMiddle, jpRight;

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
        jlTitle = new JLabel();
        jlTitle.setHorizontalAlignment(JLabel.CENTER);
        /* Add */
        jpTop.add(jlTitle, BorderLayout.CENTER);

        //MIDDLE
        jpCenter = new JPanel();
        jpCenter.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 60));
        jpCenter.setLayout(new BorderLayout());
        jpCenter.setPreferredSize(new Dimension(jpCenter.getWidth(), 70));
        // COMPONENTS
        jsPlayTime = new JSlider(JSlider.HORIZONTAL, 0);
        jsPlayTime.setMinorTickSpacing(1);
        jsPlayTime.setSnapToTicks(true);
        jsPlayTime.setEnabled(false);
        jsPlayTime.addChangeListener(e -> {
            currentPlayTime = String.valueOf(jsPlayTime.getValue());
            jlCurrentPlayTime.setText(currentPlayTime);
        });
        melodyLength = String.valueOf(jsPlayTime.getMaximum());
        jlCurrentPlayTime = new JLabel(String.valueOf(currentPlayTime));
        jlMelodyLength = new JLabel(melodyLength);
        /* Add */
        jpCenter.add(jlCurrentPlayTime, BorderLayout.WEST);
        jpCenter.add(jsPlayTime, BorderLayout.CENTER);
        jpCenter.add(jlMelodyLength, BorderLayout.EAST);

        // BOTTOM
        jpBottom = new JPanel();
        jpBottom.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpBottom.setLayout(new BorderLayout());
        // COMPONENTS
        /* Panels */
        jpLeft = new JPanel();
        jpMiddle = new JPanel();
        jpRight = new JPanel();
        jpLeft.setPreferredSize(new Dimension(69, 0));
        /* Buttons */
        jbList = new MusicButton(this, "⋮☰", 30);
        jbList.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 21));
        jbPrevious = new MusicButton(this, "⏮", 40);
        jbPlay = new MusicButton(this, "⏵", 40);
        jbNext = new MusicButton(this, "⏭", 40);
        /* Add */
        jpRight.add(jbList);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbList) {
            changeFocus("MusicMenuView");
        }
        if (e.getSource() == jbPrevious) {
            MusicUpdate.previousSong();
            jbPlay.setText("⏸");
        }
        if (e.getSource() == jbPlay) {
            if (!MusicUpdate.isPlaying()) {
                jbPlay.setText("⏸");
                MusicUpdate.setPlaying(true);
            } else {
                jbPlay.setText("⏵");
                MusicUpdate.setPlaying(false);
            }
        }
        if (e.getSource() == jbNext) {
            MusicUpdate.nextSong();
            jbPlay.setText("⏸");
        }
        updateSongInfoView();
        Audio.play("click.wav");
    }

    public void onFocus(ArrayList<String> parameters) {
        jsPlayTime.setValue(MusicUpdate.getCurrentSongTime());
        if (!MusicUpdate.isPlaying()) {
            jbPlay.setText("⏵");
        } else {
            jbPlay.setText("⏸");
        }
        updateSongInfoView();
    }

    @Override
    public void onShadow() {}

    public void updateSongInfoView() {
        jlTitle.setText("<html><div style='text-align:center;'><p style='margin: 0; font-size: 4em'>♫</p><p style='margin: 0;font-size: 1.5em'>"+ MusicUpdate.getCurrentSongName() +"</p></div></html>");
        jsPlayTime.setMaximum(MusicUpdate.getCurrentSongDuration());
        melodyLength = String.valueOf(jsPlayTime.getMaximum());
        jlMelodyLength.setText(melodyLength);
        jsPlayTime.setValue(MusicUpdate.getCurrentSongTime());

    }

}