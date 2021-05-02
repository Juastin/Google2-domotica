package src.views;
import src.components.CButton;
import src.components.MusicButton;
import src.core.Audio;
import src.core.Container;
import src.core.View;
import src.core.Navbar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicPlayerView extends View implements ActionListener {
    private JPanel jpTop, jpCenter, jpBottom, jpLeft, jpMiddle, jpRight;
    private JButton jbList, jbPrevious, jbPlay, jbNext;
    private JSlider jsPlayTime;
    private JLabel jlTitle, jlCurrentPlayTime, jlMelodyLength;
    private String currentPlayTime, melodyLength;

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
        jlTitle = new JLabel("<html><div style='text-align:center;'><p style='margin: 0; font-size: 4em'>♫</p><p style='margin: 0;font-size: 1.5em'>Titel melodie</p></div></html>", JLabel.CENTER);
        /* Add */
        jpTop.add(jlTitle, BorderLayout.CENTER);

        //MIDDLE
        jpCenter = new JPanel();
        jpCenter.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 60));
        jpCenter.setLayout(new BorderLayout());
        jpCenter.setPreferredSize(new Dimension(jpCenter.getWidth(), 70));
        // COMPONENTS
        jsPlayTime = new JSlider(JSlider.HORIZONTAL, 0, 168, 51);
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
        /* Add */
        jpCenter.add(jlCurrentPlayTime, BorderLayout.WEST);
        jpCenter.add(jsPlayTime, BorderLayout.CENTER);
        jpCenter.add(jlMelodyLength, BorderLayout.EAST);

        // BOTTOM
        jpBottom = new JPanel();
        jpBottom.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
        jpBottom.setLayout(new BorderLayout());
        // COMPONENTS
        /* Panels */
        jpLeft = new JPanel();
        jpMiddle = new JPanel();
        jpRight = new JPanel();
        jpRight.setPreferredSize(new Dimension(50, 0));
        /* Buttons */
        jbList = new MusicButton(this, "⋮☰");
        jbPrevious = new MusicButton(this, "⏮");
        jbPlay = new MusicButton(this, "⏵");
        jbNext = new MusicButton(this, "⏭");
        /* Add */
        jpLeft.add(jbList);
        jpMiddle.add(jbPrevious);
        jpMiddle.add(jbPlay);
        jpMiddle.add(jbNext);
        /* Added panels */
        jpBottom.add(jpLeft, BorderLayout.WEST);
        jpBottom.add(jpMiddle, BorderLayout.CENTER);
        jpBottom.add(jpRight, BorderLayout.EAST);

        //DEFAULT LAYOUT
        main.add(jpTop, BorderLayout.NORTH);
        main.add(jpCenter, BorderLayout.CENTER);
        main.add(jpBottom, BorderLayout.SOUTH);
        add(main, BorderLayout.CENTER);
        add(navbar, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbList) {

        }
        if (e.getSource() == jbPrevious) {

        }
        if (e.getSource() == jbPlay) {
            if (jbPlay.getText().equals("⏸")) {
                jbPlay.setText("⏵");
            } else {
                jbPlay.setText("⏸");
            }
        }
        if (e.getSource() == jbNext) {

        }
        Audio.play("click.wav");
    }

    @Override
    public void onFocus() {}

    @Override
    public void onShadow() {}

    @Override
    public void onTick(long now) {}
    
}