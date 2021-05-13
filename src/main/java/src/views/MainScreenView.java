package src.views;
import src.core.Audio;
import src.core.Container;
import src.core.View;
import src.core.Navbar;
import src.system.User;
import src.system.Queries;
import src.components.MusicButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MainScreenView extends View implements ActionListener {
    private JLabel jlWelcomeMessage, jlHeating, jlTemperature, jlLight, jlLightSmall, jlHPA, jlHumidity;
    // Names are based on position, left upper panel being jpLU, right bottom panel being jpRU
    private JPanel jpLU, jpRU, jpLB, jpRB;
    private long lastFetchTimestamp;
    private MusicButton mbPrevious, mbPlay, mbNext, mbList, mbIcon;

    public MainScreenView(Container container, String name) {
        super(container, name);
        setLayout(new BorderLayout());
        Border myborder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED); // https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        setVisible(false);

        // NAVBAR
        Navbar navbar = new Navbar(this);

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
        jlTemperature = new JLabel("...", JLabel.CENTER);
        jlTemperature.setFont(new Font(jlTemperature.getFont().getFamily(), Font.PLAIN, 48));
        jpLU.add(jlTemperature, BorderLayout.CENTER);
        jlHeating = new JLabel("...", JLabel.RIGHT);
        jlHeating.setFont(new Font(jlHeating.getFont().getFamily(), Font.PLAIN, 18));
        jlHeating.setBorder(new EmptyBorder(0,0,10,2));
        jpLU.add(jlHeating, BorderLayout.SOUTH);

        // RIGHT UPPER
        jpRU = new JPanel();
        jpRU.setLayout(new BorderLayout());
        jpRU.setBorder(myborder);
        // COMPONENTS
        jlLight = new JLabel("...", JLabel.CENTER);
        jlLight.setFont(new Font(jlLight.getFont().getFamily(), Font.PLAIN, 48));
        jlLight.setBorder(new EmptyBorder(32,0,0,0));
        jpRU.add(jlLight, BorderLayout.CENTER);
        jlLightSmall = new JLabel("...", JLabel.RIGHT);
        jlLightSmall.setFont(new Font(jlLightSmall.getFont().getFamily(), Font.PLAIN, 18));
        jlLightSmall.setBorder(new EmptyBorder(0,0,10,10));
        jpRU.add(jlLightSmall, BorderLayout.SOUTH);

        // LEFT BOTTOM
        jpLB = new JPanel();
        jpLB.setLayout(new GridLayout(1, 2));
        jpLB.setBorder(myborder);
        // COMPONENTS
        jlHPA = new JLabel("...", JLabel.CENTER);
        jlHPA.setFont(new Font(jlHPA.getFont().getFamily(), Font.PLAIN, 24));
        jlHumidity = new JLabel("...", JLabel.CENTER);
        jlHumidity.setFont(new Font(jlHumidity.getFont().getFamily(), Font.PLAIN, 24));
        jpLB.add(jlHPA);
        jpLB.add(jlHumidity);

        // RIGHT BOTTOM
        jpRB = new JPanel();
        jpRB.setLayout(new BorderLayout());
        jpRB.setBorder(myborder);
        // COMPONENTS CENTER
        mbIcon = new MusicButton(this, "♫", 90);
        jpRB.add(mbIcon, BorderLayout.CENTER);
        // COMPONENTS BOTTOM
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new BorderLayout());
        toolbar.setBorder(new EmptyBorder(0,10,10,10));
        mbList = new MusicButton(this, "⋮☰", 20);
        mbList.setBorder(new EmptyBorder(0,13,0,0));
        toolbar.add(mbList, BorderLayout.WEST);
        JPanel toolbarCenter = new JPanel();
        toolbarCenter.setLayout(new GridBagLayout());
        toolbarCenter.setBorder(new EmptyBorder(0,0,4,35));
        mbPrevious = new MusicButton(this, "⏮", 30); toolbarCenter.add(mbPrevious);
        mbPlay = new MusicButton(this, "⏵", 30); toolbarCenter.add(mbPlay);
        mbNext = new MusicButton(this, "⏭", 30); toolbarCenter.add(mbNext);
        toolbar.add(toolbarCenter, BorderLayout.CENTER);
        jpRB.add(toolbar, BorderLayout.SOUTH);

        main.add(jpLU);
        main.add(jpRU);
        main.add(jpLB);
        main.add(jpRB);
        add(main, BorderLayout.CENTER);
        add(navbar, BorderLayout.EAST);
    }

    public void fetchSensorData() {
        ArrayList<ArrayList<String>> data = Queries.getSensorData();
        // UPDATE LIVE SENSOR DATA
        jlTemperature.setText("🌡 " + data.get(0).get(1) + "°C");
        jlHPA.setText("<html><p style='text-align:center;font-size:1.5em'>⏲</p><br><span>" + data.get(0).get(2) + " hPa</span></html>");
        jlHumidity.setText("<html><p style='text-align:center;font-size:1.5em'>💧</p><br><span>" + data.get(0).get(3) + "%</span></html>");
        if (data.get(0).get(4).equals("")) {
            jlLight.setText("Arduino not found");
            jlLight.setFont(new Font(jlLight.getFont().getFamily(), Font.PLAIN, 24));
        } else {
            jlLight.setText("💡 " + data.get(0).get(4) + "%");
            jlLight.setFont(new Font(jlLight.getFont().getFamily(), Font.PLAIN, 48));
        }
        // UPDATE USER SETTINGS DATA
        jlHeating.setText("♨️ " + User.getTemperature() + "°C");
        jlLightSmall.setText("🔆 " + User.getLight() + "%");
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        Object source = e.getSource();
        
        if (source==mbIcon) {
            changeFocus("MusicPlayerView");
        } else if (source==mbList) {
            changeFocus("MusicMenuView");
        }
          

        Audio.play("click.wav");
    }

    @Override
    public void onFocus(ArrayList<String> parameters) {
        User.refreshPersonalSettings();

        jlWelcomeMessage.setText("Gebruiker: " + User.getUsername());

        System.out.println("Light: " + User.getLight());
        System.out.println("Temperature: " + User.getTemperature());
        System.out.println("PLaylistId: " + User.getPlaylistID());

        fetchSensorData();
    }

    @Override
    public void onShadow() {}

    @Override
    public void onTick(long now) {
        if (lastFetchTimestamp==0) {lastFetchTimestamp = now;}

        if (now > lastFetchTimestamp + 5) {
            lastFetchTimestamp = now;
            fetchSensorData();
        }
    }

}