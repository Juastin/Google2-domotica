package src.views;
import src.core.*;
import src.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MakeProfileView extends SubPanel implements ActionListener {
    private JButton jbBack, jbSave;
    private JTextField jtUsername;
    private JPasswordField jtPassword;
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
        jtPassword = new JPasswordField(20);

        JPanel backLabelPanel = new JPanel();
        jbBack = new JButton("< Terug");
        jbBack.addActionListener(this);
        jbBack.setOpaque(false);
        jbBack.setContentAreaFilled(false);
        jbBack.setBorderPainted(false);
        jbBack.setFont(jbBack.getFont().deriveFont(24.0f));
        backLabelPanel.add(jbBack, BorderLayout.SOUTH);

        JPanel savePanel = new JPanel();
        jbSave = new CButton(this, "Profiel aanmaken", Color.black, Color.white);
        jbSave.setFont(jbBack.getFont().deriveFont(20.0f));


        jlUsernameLabel = new JLabel("Gebruikersnaam");
        jlPasswordLabel = new JLabel("Wachtwoord");

        jlUsernameLabel.setFont(jlUsernameLabel.getFont().deriveFont(15.0f));
        jlPasswordLabel.setFont(jlPasswordLabel.getFont().deriveFont(15.0f));

        // Panels to divide labels and textfields.
        JPanel usernameLabel = new JPanel();
        usernameLabel.setPreferredSize(new Dimension(1000,40));
        usernameLabel.add(jlUsernameLabel);

        JPanel username = new JPanel();
        username.setPreferredSize(new Dimension(1000,40));
        username.add(jtUsername);

        JPanel passwordLabel = new JPanel();
        passwordLabel.setPreferredSize(new Dimension(1000,40));
        passwordLabel.add(jlPasswordLabel);

        JPanel password = new JPanel();
        password.setPreferredSize(new Dimension(1000,50));
        password.add(jtPassword);

        JPanel save = new JPanel();
        save.setPreferredSize(new Dimension(1000,100));
        save.add(jbSave);

        // BOTTOM
        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(1000,100));
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        bottom.add(backLabelPanel, BorderLayout.SOUTH);


        add(top,BorderLayout.NORTH);
        add(usernameLabel,BorderLayout.CENTER);
        add(username,BorderLayout.CENTER);
        add(passwordLabel,BorderLayout.CENTER);
        add(password,BorderLayout.CENTER);
        add(save,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbBack) {
            changeFocus("ProfileView");
        } else if (e.getSource() == jbSave) {
            String usernameText = jtUsername.getText();
            char[] passwordText = jtPassword.getPassword();
            System.out.println(usernameText+" "+passwordText);
        }
        Audio.play("../resources/click.wav");
    }

    @Override
    public void onFocus() {

    }
}
