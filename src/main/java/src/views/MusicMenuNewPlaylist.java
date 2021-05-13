package src.views;

import javax.swing.*;
import java.awt.*;

public class MusicMenuNewPlaylist extends JPanel {
    public MusicMenuNewPlaylist() {
        setVisible(false);
        setLayout(new GridLayout(1, 2));

        // LEFT
        JPanel left = new JPanel();
        left.setBackground(Color.black);

        // RIGHT
        JPanel right = new JPanel();
        right.setBackground(Color.red);

        add(left);
        add(right);
    }
}
