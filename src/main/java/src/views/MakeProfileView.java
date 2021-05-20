package src.views;
import src.core.Audio;
import src.core.Container;
import src.core.View;
import src.components.*;
import src.system.Queries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.ArrayList;

public class MakeProfileView extends View implements ActionListener {
    private JButton jbBack, jbSave;
    private JTextField jtUsername, jtFirstname, jtLastname;
    private JPasswordField jtPassword;
    private JLabel jlUsernameLabel, jlPasswordLabel, jlFirstnameLabel, jlLastnameLabel, welcomeLabel;

    public MakeProfileView(Container container, String name) {
        super(container, name);
        setLayout(new BorderLayout());
        setVisible(false);

        // TOP
        JPanel top = new JPanel();
        top.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 0));
        welcomeLabel = new JLabel("Profiel toevoegen", JLabel.CENTER);
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(24.0f));
        welcomeLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        welcomeLabel.setPreferredSize(new Dimension(222, 50));
        top.add(welcomeLabel,BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel();
        JPanel centerInner = new JPanel();
        centerInner.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        centerInner.setPreferredSize(new Dimension(400, 140));
        GridLayout customGrid = new GridLayout(4, 2);
        customGrid.setVgap(10);
        centerInner.setLayout(customGrid);
        center.add(centerInner);

        jlUsernameLabel = new JLabel("Gebruikersnaam");
        centerInner.add(jlUsernameLabel);
        jtUsername = new JTextField(20);
        centerInner.add(jtUsername);
        jlFirstnameLabel = new JLabel("Voornaam");
        centerInner.add(jlFirstnameLabel);
        jtFirstname = new JTextField(20);
        centerInner.add(jtFirstname);
        jlLastnameLabel = new JLabel("Achternaam");
        centerInner.add(jlLastnameLabel);
        jtLastname = new JTextField(20);
        centerInner.add(jtLastname);
        jlPasswordLabel = new JLabel("Wachtwoord");
        centerInner.add(jlPasswordLabel);
        jtPassword = new JPasswordField(20);
        centerInner.add(jtPassword);

        jlUsernameLabel.setFont(jlUsernameLabel.getFont().deriveFont(15.0f));
        jlFirstnameLabel.setFont(jlUsernameLabel.getFont().deriveFont(15.0f));
        jlLastnameLabel.setFont(jlUsernameLabel.getFont().deriveFont(15.0f));
        jlPasswordLabel.setFont(jlPasswordLabel.getFont().deriveFont(15.0f));

        JPanel save = new JPanel();
        save.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        save.setPreferredSize(new Dimension(1000, 140));
        JPanel saveInner = new JPanel();
        saveInner.setLayout(new GridLayout(1,1));
        saveInner.setPreferredSize(new Dimension(222, 32));
        jbSave = new CButton(this, "Profiel aanmaken", Color.black, Color.white);
        jbSave.setFont(new Font(jbSave.getFont().getFamily(), Font.PLAIN, jbSave.getFont().getSize()));
        saveInner.add(jbSave);
        save.add(saveInner);
        center.add(save);

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

        add(top,BorderLayout.NORTH);
        add(center,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbBack) {
            changeFocus("ProfileView");
            resetValues();
        } else if (e.getSource() == jbSave) {
            if (Objects.requireNonNull(Queries.getProfiles()).size() >= 15) {
                JOptionPane.showMessageDialog(this,"Er zijn meer dan 15 profielen, verwijder er eerst een.");
            } else if (jtUsername.getText().equals("") || jtFirstname.getText().equals("") || jtLastname.getText().equals("")) {
                JOptionPane.showMessageDialog(this,"Vul alles in!");
            } else {
                if (Queries.isUsernameUsed(jtUsername.getText())) {
                    JOptionPane.showMessageDialog(this, "Gebruikersnaam \"" + jtUsername.getText() + "\" is al gebruikt!");
                } else {
                    String message = checkPwRequirements();
                    if (message.equals("")) {
                        boolean result = Queries.makeNewProfile(jtUsername.getText(), jtFirstname.getText(), jtLastname.getText(), jtPassword.getPassword());
                        if (result) {
                            Audio.play("success_0.wav");
                            JOptionPane.showMessageDialog(this, "Account is aangemaakt!");
                            changeFocus("ProfileView");
                            resetValues();
                        } else {
                            JOptionPane.showMessageDialog(this, "Fout! Het account kon niet aangemaakt worden.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, message);
                    }
                }
            }
        }
        Audio.play("click.wav");
    }

    public void resetValues() {
        jtPassword.setText("");
        jtFirstname.setText("");
        jtLastname.setText("");
        jtUsername.setText("");
    }

    public String checkPwRequirements() {
        String text = "";
        if (jtPassword.getPassword().length < 8 || jtPassword.getPassword() == null) {
            text += "Wachtwoord moet minstens 8 karakters lang zijn";
            return text;
        }

        boolean containsUC = false;
        boolean containsLC = false;
        boolean containsDigit = false;
        for (char letter : jtPassword.getPassword()) {
            if (Character.isDigit(letter)) { containsDigit = true; }
            if (Character.isLowerCase(letter)) { containsLC = true; }
            if (Character.isUpperCase(letter)) { containsUC = true; }
        }

        if (!containsUC) {
                text = "Wachtwoord bevat geen hoofdletters";
        }

        if (!containsLC) {
            if (text.equals("")) {
                text = "Wachtwoord bevat geen kleine letters";
            } else {
                text += ", kleine letters";
            }
        }

        if (!containsDigit) {
            if (text.equals("")) {
                text = "Wachtwoord bevat geen cijfers";
            } else {
                text += " en cijfers";
            }
        }
        return text;
    }

    @Override
    public void onFocus(ArrayList<String> parameters) {}

    @Override
    public void onShadow() {}

    @Override
    public void onTick(long now) {}
    
}
