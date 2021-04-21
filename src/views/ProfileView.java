package src.views;
import src.core.*;
import src.components.*;
import src.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ProfileView extends SubPanel implements ActionListener {
    private JButton jbNieuwProfiel;
    private JLabel jlKies;
    private ArrayList<CButton> userButtons;

    public ProfileView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout());
        setVisible(false);

        ArrayList<ArrayList<String>> gebruikers = Database.query("SELECT username FROM Person");

        // TOP
        JPanel top = new JPanel();
        top.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));

        jlKies = new JLabel("Kies uw profiel:");
        jlKies.setFont(jlKies.getFont().deriveFont(24.0f));
        jbNieuwProfiel = new CButton(this, "＋", Color.black, Color.white);
        jbNieuwProfiel.setFont(new Font(jbNieuwProfiel.getFont().getFamily(), Font.PLAIN, 24));

        top.add(jlKies);
        add(top, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel();
        JPanel userGrid = new JPanel();
        userGrid.setPreferredSize(new Dimension(600, 20*gebruikers.size()));
        GridLayout userGridLayout = new GridLayout((gebruikers.size()/3), 3);
        userGridLayout.setHgap(10); userGridLayout.setVgap(10);
        userGrid.setLayout(userGridLayout);
        userButtons = new ArrayList<CButton>();
        for (ArrayList<String> row: gebruikers) {
            for (String naam: row) {
                CButton button = new CButton(this, naam, Color.black, Color.white);
                button.setFont(new Font(button.getFont().getFamily(), Font.PLAIN, 16));
                userGrid.add(button);
                userButtons.add(button);
            }
        }
        center.add(userGrid);
        add(center, BorderLayout.CENTER);

        // BOTTOM
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel nieuwProfielButtonPanel = new JPanel();
        nieuwProfielButtonPanel.add(jbNieuwProfiel);
        bottom.add(nieuwProfielButtonPanel, BorderLayout.NORTH);

        JPanel nieuwProfielLabelPanel = new JPanel();
        nieuwProfielLabelPanel.add(new JLabel("Profiel toevoegen (" + gebruikers.size() + "/15)"), BorderLayout.SOUTH);
        bottom.add(nieuwProfielLabelPanel, BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbNieuwProfiel) {
            changeFocus("MakeProfileView");
        }

        for (CButton button: userButtons) {
            if (e.getSource() == button) {
                User.setUsername(button.getText());
                changeFocus("LoginView");
            }
        }

        Audio.play("../resources/click.wav");
    }

    @Override
    public void onFocus() {
        
    }

}
