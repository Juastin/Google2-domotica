package src.views;
import src.core.*;
import src.components.*;
import src.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class LoginView extends SubPanel implements ActionListener{

    private JLabel jlUser, jlPassword;
    private JPasswordField jbPassword;
    private JButton jbOk, jbTerug;

    public LoginView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout());
        setVisible(false);

        // TOP
        JPanel top = new JPanel();
        top.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Username moet nog goed worden opgehaald
        JPanel gebruikerPanel = new JPanel();
        jlUser = new JLabel("", JLabel.CENTER);
        jlUser.setFont(jlUser.getFont().deriveFont(24.0f));
        gebruikerPanel.add(jlUser, BorderLayout.NORTH);
        top.add(gebruikerPanel, BorderLayout.NORTH);

        add(top, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel();
        center.setBorder(BorderFactory.createEmptyBorder(70, 0, 10, 0));
        JPanel formGrid = new JPanel();
        GridLayout formGridLayout = new GridLayout(3, 1);
        formGridLayout.setHgap(10); formGridLayout.setVgap(10);
        formGrid.setLayout(formGridLayout);

        jlPassword = new JLabel("Wachtwoord", JLabel.CENTER);
        jlPassword.setFont(jlPassword.getFont().deriveFont(20.0f));
        formGrid.add(jlPassword, BorderLayout.CENTER);
        jbPassword = new JPasswordField(20);
        jbPassword.addActionListener(this);
        formGrid.add(jbPassword, BorderLayout.CENTER);
        jbOk = new CButton(this, "OK", Color.black, Color.white);
        formGrid.add(jbOk, BorderLayout.CENTER);

        center.add(formGrid);
        add(center, BorderLayout.CENTER);

        //BOTTOM
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel TerugLabelPanel = new JPanel();
        jbTerug = new JButton("< Terug");
        jbTerug.addActionListener(this);
        jbTerug.setOpaque(false);
        jbTerug.setContentAreaFilled(false);
        jbTerug.setBorderPainted(false);
        jbTerug.setFont(jbTerug.getFont().deriveFont(24.0f));
        TerugLabelPanel.add(jbTerug, BorderLayout.SOUTH);
        bottom.add(TerugLabelPanel, BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbOk || e.getSource() == jbPassword) {
            String password = String.valueOf(jbPassword.getPassword());

            try {
                ArrayList<ArrayList<String>> result = Database.query("SELECT PasswordHash FROM Person WHERE Username = '" + User.getUsername() + "'");
                if (result.get(0).get(0).equals(password)) {
                    Audio.play("../resources/success.wav");
                    User.setLoggedIn(true);
                    changeFocus("MainScreenView");
                } else if (password.equals("")) {
                    JOptionPane.showMessageDialog(this, "Try Again ");
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect Password. Do you understand " + User.getUsername() + "?", "Login Failed", 2);
                }
            } catch (Exception ex) {
                ex.getMessage();
            }
        } else if (e.getSource() == jbTerug) {
            jbPassword.setText("");
            changeFocus("ProfileView");
        }
        Audio.play("../resources/click.wav");
    }

    @Override
    public void onFocus() {
        jlUser.setText("Naam: " + User.getUsername());
    }

}

