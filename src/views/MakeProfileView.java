package src.views;
import src.core.*;
import src.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MakeProfileView extends SubPanel implements ActionListener {
    private JButton jbBack, jbSave;
    private JTextField jtFirstName, jtLastName, jtPassword;
    private JLabel jlFirstNameLabel, jlLastNameLabel, jlPasswordLabel, welcomeLabel;

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
        jtFirstName = new JTextField(20);
        jtLastName = new JTextField(20);
        jtPassword = new JTextField(20);
        jbSave = new CButton(this, "opslaan", Color.black, Color.white);
        jbBack = new CButton(this, "terug naar hoofdmenu", Color.black, Color.white);

        jlFirstNameLabel = new JLabel("Vul hier je voornaam in:");
        jlLastNameLabel = new JLabel("Vul hier je achternaam in:");
        jlPasswordLabel = new JLabel("Vul hier je wachtwoord in:");

        jlFirstNameLabel.setFont(jlFirstNameLabel.getFont().deriveFont(15.0f));
        jlLastNameLabel.setFont(jlLastNameLabel.getFont().deriveFont(15.0f));
        jlPasswordLabel.setFont(jlPasswordLabel.getFont().deriveFont(15.0f));

        // Panels to divide labels and textfields.
        JPanel FirstName = new JPanel();
        FirstName.setPreferredSize(new Dimension(1000,40));
        FirstName.add(jlFirstNameLabel);
        FirstName.add(jtFirstName);

        JPanel LastName = new JPanel();
        LastName.setPreferredSize(new Dimension(1000,40));
        LastName.add(jlLastNameLabel);
        LastName.add(jtLastName);

        JPanel Password = new JPanel();
        Password.setPreferredSize(new Dimension(1000,250));
        Password.add(jlPasswordLabel);
        Password.add(jtPassword);

        // BOTTOM
        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(1000,40));
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        bottom.add(jbBack);
        bottom.add(jbSave);

        add(top,BorderLayout.NORTH);
        add(FirstName,BorderLayout.CENTER);
        add(LastName,BorderLayout.CENTER);
        add(Password,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbBack) {
            changeFocus("ProfileView");
        } else if (e.getSource() == jbSave) {
            JOptionPane.showMessageDialog(this, "Wow '" + jtFirstName.getText() + "' is echt een coole naam.");
        }
        Audio.play("../resources/click.wav");
    }

}
