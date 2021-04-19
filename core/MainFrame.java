package google2_domotica.core;
import google2_domotica.components.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel main_panel;
    
    public MainFrame() {
        setSize(946, 540);
        setTitle("Google2: MainFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(0, 1));

        main_panel = new MainPanel(this);

        add(main_panel);

        setVisible(true);
    }

}
