package src.views;
import src.components.CButton;
import src.core.*;
import src.system.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.*;

public class MainScreenView extends SubPanel implements ActionListener {
    private JLabel jlWelcomeMessage, jlHeating, jlTemperature, jlLight, jlLightSmall, jlHPA, jlHumidity;
    private JButton jbLogOut;
    // Names are based on position, left upper panel being jpLU, right bottom panel being jpRU
    private JPanel jpLU, jpRU, jpLB, jpRB;

    public MainScreenView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout());
        Border myborder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED); // https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        setVisible(false);

        // NAVBAR GOES HERE v
        JPanel navbar = new JPanel();
        navbar.setBackground(Color.gray);
        navbar.add(new JLabel("navbar"));

        // Main Panel
        JPanel main = new JPanel();
        main.setLayout(new GridLayout(2, 2));

        // LEFT UPPER
        jpLU = new JPanel();
        jpLU.setLayout(new BorderLayout());
        jpLU.setBorder(myborder);
        // COMPONENTS
        jlWelcomeMessage = new JLabel("");
        jlWelcomeMessage.setFont(new Font(jlWelcomeMessage.getFont().getFamily(), Font.PLAIN, 18));
        jlWelcomeMessage.setBorder(new EmptyBorder(8,10,0,0));
        jpLU.add(jlWelcomeMessage, BorderLayout.NORTH);
        jlTemperature = new JLabel("🌡 18°C", JLabel.CENTER);
        jlTemperature.setFont(new Font(jlTemperature.getFont().getFamily(), Font.PLAIN, 48));
        jpLU.add(jlTemperature, BorderLayout.CENTER);
        jlHeating = new JLabel("♨️ 19°C", JLabel.RIGHT);
        jlHeating.setFont(new Font(jlHeating.getFont().getFamily(), Font.PLAIN, 18));
        jlHeating.setBorder(new EmptyBorder(0,0,10,2));
        jpLU.add(jlHeating, BorderLayout.SOUTH);

        // RIGHT UPPER
        jpRU = new JPanel();
        jpRU.setLayout(new BorderLayout());
        jpRU.setBorder(myborder);
        // COMPONENTS
        jlLight = new JLabel("💡 75%", JLabel.CENTER);
        jlLight.setFont(new Font(jlLight.getFont().getFamily(), Font.PLAIN, 48));
        jlLight.setBorder(new EmptyBorder(32,0,0,0));
        jpRU.add(jlLight, BorderLayout.CENTER);
        jlLightSmall = new JLabel("🔆 38%", JLabel.RIGHT);
        jlLightSmall.setFont(new Font(jlLightSmall.getFont().getFamily(), Font.PLAIN, 18));
        jlLightSmall.setBorder(new EmptyBorder(0,0,10,10));
        jpRU.add(jlLightSmall, BorderLayout.SOUTH);

        // LEFT BOTTOM
        jpLB = new JPanel();
        jpLB.setLayout(new GridLayout(1, 2));
        jpLB.setBorder(myborder);
        // COMPONENTS
        jlHPA = new JLabel("<html><p style='text-align:center;font-size:1.5em'>⏲</p><br><span>1019 hPa</span></html>", JLabel.CENTER);
        jlHPA.setFont(new Font(jlHPA.getFont().getFamily(), Font.PLAIN, 24));
        jlHumidity = new JLabel("<html><p style='text-align:center;font-size:1.5em'>💧</p><br><span>31%</span></html>", JLabel.CENTER);
        jlHumidity.setFont(new Font(jlHumidity.getFont().getFamily(), Font.PLAIN, 24));
        jpLB.add(jlHPA);
        jpLB.add(jlHumidity);

        // RIGHT BOTTOM
        jpRB = new JPanel();
        jpRB.setBorder(myborder);

        main.add(jpLU);
        main.add(jpRU);
        main.add(jpLB);
        main.add(jpRB);
        add(main, BorderLayout.CENTER);
        add(navbar, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == jbLogOut) {
            User.logOut();
            changeFocus("ProfileView");
            JOptionPane.showMessageDialog(this, "U bent afgemeld");
        }
    }

    @Override
    public void onFocus() {
        jlWelcomeMessage.setText("Gebruiker: " + User.getUsername());

        System.out.println("Light: " + User.getLight());
        System.out.println("Temperature: " + User.getTemperature());
        System.out.println("PLaylistId: " + User.getPlaylistID());
    }

}

