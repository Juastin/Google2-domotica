package src.views;
import src.core.Audio;
import src.core.Container;
import src.core.View;
import src.core.Logging;
import src.components.*;
import src.system.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginView extends View implements ActionListener{

    private JLabel jlUser, jlPassword;
    private JPasswordField jbPassword;
    private JButton jbOk, jbBack;

    public LoginView(Container container, String name) {
        super(container, name);
        setLayout(new BorderLayout());
        setVisible(false);

        // TOP
        JPanel top = new JPanel();
        top.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Username moet nog goed worden opgehaald
        JPanel gebruikerPanel = new JPanel();
        jlUser = new JLabel("", JLabel.CENTER);
        jlUser.setFont(jlUser.getFont().deriveFont(24.0f));
        jlUser.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jlUser.setPreferredSize(new Dimension(222, 50));

        gebruikerPanel.add(jlUser, BorderLayout.NORTH);
        top.add(gebruikerPanel, BorderLayout.NORTH);

        add(top, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel();
        center.setBorder(BorderFactory.createEmptyBorder(70, 0, 10, 0));
        JPanel formGrid = new JPanel();
        GridLayout formGridLayout = new GridLayout(3, 1);
        formGridLayout.setVgap(10);
        formGrid.setLayout(formGridLayout);

        jlPassword = new JLabel("Wachtwoord", JLabel.CENTER);
        jlPassword.setFont(new Font(jlPassword.getFont().getFamily(), Font.PLAIN, 24));
        formGrid.add(jlPassword, BorderLayout.CENTER);
        jbPassword = new JPasswordField(20);
        jbPassword.setHorizontalAlignment(SwingConstants.CENTER);
        jbPassword.addActionListener(this);
        formGrid.add(jbPassword, BorderLayout.CENTER);
        jbOk = new CButton(this, "Inloggen", Color.black, Color.white);
        jbOk.setFont(new Font(jbOk.getFont().getFamily(), Font.PLAIN, jbOk.getFont().getSize()));
        formGrid.add(jbOk, BorderLayout.CENTER);

        center.add(formGrid);
        add(center, BorderLayout.CENTER);

        //BOTTOM
        JPanel bottom = new JPanel();
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        jbBack = new JButton("< Terug");
        jbBack.setFont(new Font(jbBack.getFont().getFamily(), Font.PLAIN, 20));
        jbBack.addActionListener(this);
        jbBack.setOpaque(false);
        jbBack.setContentAreaFilled(false);
        jbBack.setBorderPainted(false);
        bottom.add(jbBack, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbOk || e.getSource() == jbPassword) {
            String password = String.valueOf(jbPassword.getPassword());

            boolean result = Queries.isPasswordCorrect(User.getUsername(), password);
            if (result) {
                Logging.logThis("Successful login attempt for user " + User.getUsername());
                Audio.play("success_0.wav");
                User.setLoggedIn(true);

                try {
                    ArrayList<ArrayList<String>> resultPersonalSettings = Queries.getPersonalSettings(User.getUsername());
                    User.setPersonalSettings(Integer.parseInt(resultPersonalSettings.get(0).get(0)), Integer.parseInt(resultPersonalSettings.get(0).get(1)));
                    if (resultPersonalSettings.get(0).get(2) != null){
                        User.setPlaylistID(Integer.parseInt(resultPersonalSettings.get(0).get(2)));
                        // if null -> PlaylistID = 0;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                changeFocus("MainScreenView");
            } else {
                Audio.play("error.wav");
                Logging.logThis("Failed login attempt for user " + User.getUsername());
                if (password.equals("")) {
                    JOptionPane.showMessageDialog(this, "Try again ");
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect password. Try Again", "Login Failed", 2);
                }
            }
        } else if (e.getSource() == jbBack) {
            changeFocus("ProfileView");
        }
        jbPassword.setText("");
        Audio.play("click.wav");
    }

    @Override
    public void onFocus(ArrayList<String> parameters) {
        jlUser.setText(User.getUsername());
        jbPassword.requestFocus();
    }

    @Override
    public void onShadow() {}

    @Override
    public void onTick(long now) {}

}

