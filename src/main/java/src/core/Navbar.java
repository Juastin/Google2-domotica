package src.core;

import src.components.CButton;
import src.system.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Navbar extends JPanel implements ActionListener {
    private View parent;
    private JButton jbLogOut, jbHome, jbMusic, jbGame,jbSettings;

    public Navbar(View parent) {
        this.parent = parent;
        setLayout(new BorderLayout());

        jbLogOut = new CButton(this, "⮊", Color.black, Color.white);
        jbHome = new CButton(this, "⌂", Color.black, Color.white);
        jbMusic = new CButton(this, "♫", Color.black, Color.white);
        jbGame = new CButton(this, "🎮", Color.black, Color.white);
        jbSettings = new CButton(this, "⛭", Color.black, Color.white);

        jbLogOut.setFont(new Font(jbLogOut.getFont().getFamily(), Font.PLAIN, 40));
        jbHome.setFont(new Font(jbHome.getFont().getFamily(), Font.PLAIN, 40));
        jbMusic.setFont(new Font(jbMusic.getFont().getFamily(), Font.PLAIN, 40));
        jbGame.setFont(new Font(jbMusic.getFont().getFamily(), Font.PLAIN, 40));
        jbSettings.setFont(new Font(jbSettings.getFont().getFamily(), Font.PLAIN, 40));

        JPanel menuBar = new JPanel();
        menuBar.setLayout(new BorderLayout(0, 0));

        JPanel formGrid = new JPanel();
        GridLayout formGridLayout = new GridLayout(6, 1);
        formGridLayout.setHgap(0);
        formGridLayout.setVgap(0);
        formGrid.setLayout(formGridLayout);
        formGrid.setBackground(new Color(255, 255, 255));
        formGrid.setPreferredSize(new Dimension(75, 500));

        formGrid.add(jbLogOut);
        formGrid.add(jbHome);
        formGrid.add(jbMusic);
        formGrid.add(jbGame);
        formGrid.add(new JLabel());
        formGrid.add(jbSettings);

        menuBar.add(formGrid);
        add(menuBar, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbLogOut) {
            Audio.play("success_1.wav");
            User.logOut();
            parent.changeFocus("ProfileView");
            JOptionPane.showMessageDialog(this, "U bent afgemeld");
        } else if (e.getSource() == jbHome) {
            parent.changeFocus("MainScreenView");
        } else if (e.getSource() == jbMusic) {
            parent.changeFocus("MusicPlayerView");
        } else if (e.getSource() == jbGame) {
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "src/main/java/src/components/Temple-Run.jar", User.getUsername());
            try {
                pb.start();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println("Er is iets misgegaan met het opstarten van de game.");
            }
        } else if (e.getSource() == jbSettings) {
            parent.changeFocus("PersonalSettingsView");
        }
        Audio.play("click.wav");
    }

}
