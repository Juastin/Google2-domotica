package src.views;
import at.favre.lib.crypto.bcrypt.BCrypt;
import src.core.*;
import src.components.*;
import src.system.Authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MakeProfileView extends SubPanel implements ActionListener {
    private JButton jbBack, jbSave;
    private JTextField jtUsername, jtFirstname, jtLastname;
    private JPasswordField jtPassword;
    private JLabel jlUsernameLabel, jlPasswordLabel, welcomeLabel, jlFirstnameLabel, jlLastnameLabel;

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

        jbSave = new CButton(this, "Profiel aanmaken", Color.black, Color.white);
        jbSave.setFont(jbBack.getFont().deriveFont(20.0f));


        jlUsernameLabel = new JLabel("Gebruikersnaam");
        jlPasswordLabel = new JLabel("Wachtwoord");
        jlFirstnameLabel = new JLabel("Voornaam");
        jlLastnameLabel = new JLabel("Achternaam");

        jlUsernameLabel.setFont(jlUsernameLabel.getFont().deriveFont(15.0f));
        jlPasswordLabel.setFont(jlPasswordLabel.getFont().deriveFont(15.0f));
        jlFirstnameLabel.setFont(jlFirstnameLabel.getFont().deriveFont(15.0f));
        jlLastnameLabel.setFont(jlLastnameLabel.getFont().deriveFont(15.0f));

        // Panels to divide labels and textfields.

        JPanel username = new JPanel();
        username.setPreferredSize(new Dimension(1000,50));
        username.add(jlUsernameLabel);
        username.add(jtUsername);

        JPanel firstname = new JPanel();
        firstname.setPreferredSize(new Dimension(1000,50));
        firstname.add(jlFirstnameLabel);
        firstname.add(jtFirstname);

        JPanel lastname = new JPanel();
        lastname.setPreferredSize(new Dimension(1000,50));
        lastname.add(jlLastnameLabel);
        lastname.add(jtLastname);


        JPanel password = new JPanel();
        password.setPreferredSize(new Dimension(1000,60));
        password.add(jlPasswordLabel);
        password.add(jtPassword);

        JPanel save = new JPanel();
        save.setPreferredSize(new Dimension(1000,60));
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
            String usernameText = jtUsername.getText();
            String firstnameText = jtFirstname.getText();
            String lastnameText = jtLastname.getText();
            String passwordText = Authentication.encryptPassword(jtPassword.getPassword());;

            if (usernameText.equals("") || firstnameText.equals("") || lastnameText.equals("") || String.valueOf(jtPassword.getPassword()).equals("")){
                Object[] options = {"OK"};
                int n = JOptionPane.showOptionDialog(this,
                        "Account kan niet worden aangemaakt want niet alle velden zijn ingevuld","Fout!",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
            }
            else {
                
            }
            System.out.println(usernameText+firstnameText+lastnameText+passwordText);

//            String passwordTest = "1234";
//            System.out.println("Check true if password is: "+passwordTest);
//            BCrypt.Result result = Authentication.decryptPassword(passwordTest.toCharArray(),encryptedPassword);
//            System.out.println(result.verified);
        }
        Audio.play("../resources/click.wav");
    }

    @Override
    public void onFocus() {

    }
}
