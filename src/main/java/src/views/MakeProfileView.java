package src.views;
import src.core.*;
import src.components.*;
import src.system.Queries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class MakeProfileView extends SubPanel implements ActionListener {
    private JButton jbBack, jbSave;
    private JTextField jtUsername, jtFirstname, jtLastname;
    private JPasswordField jtPassword;
    private JLabel jlUsernameLabel, jlPasswordLabel, jlFirstnameLabel, jlLastnameLabel, welcomeLabel;

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
        jtFirstname = new JTextField(20);
        jtLastname = new JTextField(20);
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
        jlFirstnameLabel = new JLabel("Voornaam");
        jlLastnameLabel = new JLabel("Achternaam");
        jlPasswordLabel = new JLabel("Wachtwoord");

        jlUsernameLabel.setFont(jlUsernameLabel.getFont().deriveFont(15.0f));
        jlFirstnameLabel.setFont(jlUsernameLabel.getFont().deriveFont(15.0f));
        jlLastnameLabel.setFont(jlUsernameLabel.getFont().deriveFont(15.0f));
        jlPasswordLabel.setFont(jlPasswordLabel.getFont().deriveFont(15.0f));

        // Panels to divide labels and textfields.

        JPanel username = new JPanel();
        username.setPreferredSize(new Dimension(1000,40));
        username.add(jlUsernameLabel);
        username.add(jtUsername);

        JPanel firstname = new JPanel();
        firstname.setPreferredSize(new Dimension(1000,40));
        firstname.add(jlFirstnameLabel);
        firstname.add(jtFirstname);

        JPanel lastname = new JPanel();
        lastname.setPreferredSize(new Dimension(1000,40));
        lastname.add(jlLastnameLabel);
        lastname.add(jtLastname);

        JPanel password = new JPanel();
        password.setPreferredSize(new Dimension(1000,50));
        password.add(jlPasswordLabel);
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
        add(username,BorderLayout.CENTER);
        add(firstname,BorderLayout.CENTER);
        add(lastname,BorderLayout.CENTER);
        add(password,BorderLayout.CENTER);
        add(save,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbBack) {
            changeFocus("ProfileView");
        } else if (e.getSource() == jbSave) {
            if (Objects.requireNonNull(Queries.getProfiles()).size() >= 15) {
                JOptionPane.showMessageDialog(this,"Er zijn meer dan 15 profielen, verwijder er eerst een.");
            } else {
                boolean result = Queries.makeNewProfile(jtUsername.getText(),jtFirstname.getText(),jtLastname.getText(),jtPassword.getPassword());
                if (result) {
                    Audio.play("success.wav");
                    JOptionPane.showMessageDialog(this,"Account is aangemaakt!");
                    changeFocus("ProfileView");
                    jtPassword.setText("");
                    jtFirstname.setText("");
                    jtLastname.setText("");
                    jtUsername.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(this,"Fout! Het account kon niet aangemaakt worden.");
                }
            }
        }
        Audio.play("click.wav");
    }

    @Override
    public void onFocus() {

    }
}
