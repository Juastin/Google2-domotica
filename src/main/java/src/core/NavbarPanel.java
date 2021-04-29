package src.core;

import src.components.CButton;
import src.system.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavbarPanel extends SubPanel implements ActionListener {
    private JButton jbLogOut, jbHome, jbMusic, jbSettings;
    private JLabel white, white2;

    public NavbarPanel(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout());
        setVisible(false);

        jbLogOut = new CButton(this, "Logout", Color.black, Color.white);
        jbHome = new CButton(this, "⌂", Color.black, Color.white);
        jbMusic = new CButton(this, "♫", Color.black, Color.white);
        jbSettings = new CButton(this, "⛭", Color.black, Color.white);

//        jbLogOut.setFont(new Font("Arial", Font.PLAIN, 40));
        jbHome.setFont(new Font("Arial", Font.PLAIN, 40));
        jbMusic.setFont(new Font("Arial", Font.PLAIN, 40));
//        jbSettings.setFont(new Font("Arial", Font.PLAIN, 40));

        // Nog netter maken
        white = new JLabel(".");
        white.setForeground(new Color(255, 255, 255));
        white2 = new JLabel(".");
        white2.setForeground(new Color(255, 255, 255));
        //................

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
        formGrid.add(white);
        formGrid.add(white2);
        formGrid.add(jbSettings);

        menuBar.add(formGrid);
        add(menuBar, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbLogOut) {
            Audio.play("success_1");
            User.logOut();
            changeFocus("ProfileView");
            JOptionPane.showMessageDialog(this, "U bent afgemeld");
        } else if (e.getSource() == jbHome) {
            changeFocus("MainScreenView");
        } else if (e.getSource() == jbMusic) {
            changeFocus("MusicPlayerView");
        } else if (e.getSource() == jbSettings) {
            changeFocus("PersonalSettingsView");
        }
        Audio.play("click.wav");
    }

    @Override
    public void onFocus() {

    }
}
