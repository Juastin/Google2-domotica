package src.views;
import src.core.*;
import src.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MakeProfileView extends SubPanel implements ActionListener {
    private JButton jbBack, jbSave;
    private JTextField jtUsername, jtPassword;
    private JLabel jlUsernameLabel, jlPasswordLabel, welcomeLabel;

    public MakeProfileView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setVisible(false);

        // TOP
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(1000,100));
        top.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));

        welcomeLabel = new JLabel("Maak hier een profiel aan:");
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(24.0f));
        top.add(welcomeLabel,BorderLayout.NORTH);

        // CENTER
        jtUsername = new JTextField(20);
        jtPassword = new JTextField(20);
        jbSave = new CButton(this, "opslaan", Color.black, Color.white);
        jbBack = new CButton(this, "terug naar hoofdmenu", Color.black, Color.white);

        jlUsernameLabel = new JLabel("Gebruikersnaam");
        jlPasswordLabel = new JLabel("Wachtwoord");

        jlUsernameLabel.setFont(jlUsernameLabel.getFont().deriveFont(15.0f));
        jlPasswordLabel.setFont(jlPasswordLabel.getFont().deriveFont(15.0f));

        // Panels to divide labels and textfields.
        JPanel UsernameLabel = new JPanel();
        UsernameLabel.setPreferredSize(new Dimension(1000,40));
        UsernameLabel.add(jlUsernameLabel);

        JPanel Username = new JPanel();
        Username.setPreferredSize(new Dimension(1000,40));
        Username.add(jtUsername);

        JPanel PasswordLabel = new JPanel();
        PasswordLabel.setPreferredSize(new Dimension(1000,40));
        PasswordLabel.add(jlPasswordLabel);

        JPanel Password = new JPanel();
        Password.setPreferredSize(new Dimension(1000,200));
        Password.add(jtPassword);

        // BOTTOM
        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(1000,40));
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        bottom.add(jbBack);
        bottom.add(jbSave);

        add(top,BorderLayout.NORTH);
        add(UsernameLabel,BorderLayout.CENTER);
        add(Username,BorderLayout.CENTER);
        add(PasswordLabel,BorderLayout.CENTER);
        add(Password,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbBack) {
            changeFocus("ProfileView");
        } else if (e.getSource() == jbSave) {
            JOptionPane.showMessageDialog(this, "Wow '" + jtUsername.getText() + "' is echt een coole naam.");
        }
        Audio.play("../resources/click.wav");
    }

}
