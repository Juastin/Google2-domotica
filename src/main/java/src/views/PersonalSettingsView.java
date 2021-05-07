package src.views;
import src.components.CButton;
import src.core.Audio;
import src.core.Container;
import src.core.Navbar;
import src.core.View;
import src.system.Queries;
import src.system.User;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class PersonalSettingsView extends View implements ActionListener, ChangeListener {

    private JLabel jlTitel, jlLightIntensity, jlHeating, jlCurrenLightValue, jlLightIcon, jlHeatingIcon;
    private JButton jbCancel, jbSave, jbStandardSettings, jbDeleteProfile;
    private JSlider jsLightIntensity;
    private SpinnerModel smHeatingValue;
    private JSpinner spinner;

    public PersonalSettingsView(Container container, String name) {
        super(container, name);
        setLayout(new BorderLayout());
        setVisible(false);

        // NAVBAR
        Navbar navbar = new Navbar(this);
        add(navbar, BorderLayout.EAST);

        // MAIN
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // TOP
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        jlTitel = new JLabel("Persoonlijke instellingen");
        jlTitel.setFont(jlTitel.getFont().deriveFont(20.0f));
        top.add(jlTitel, BorderLayout.WEST);

        // CENTER
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2, 1));

        // LightIntensity
        JPanel lightIntensityPanel = new JPanel();
        jlLightIcon = new JLabel("\uD83D\uDCA1");
        jlLightIntensity = new JLabel(" Licht aan vanaf lichtintensiteit: ");
        jlLightIntensity.setFont(new Font(jlLightIntensity.getFont().getFamily(), Font.PLAIN, 13));
        jlLightIcon.setFont(new Font(jlLightIcon.getFont().getFamily(), Font.PLAIN, 30));

        jsLightIntensity = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        jsLightIntensity.setMajorTickSpacing(25);
        jsLightIntensity.setMinorTickSpacing(10);
        jsLightIntensity.setPaintTicks(true);
        jsLightIntensity.setPaintLabels(true);

        Hashtable position = new Hashtable();
        position.put(0, new JLabel("0"));
        position.put(50, new JLabel("50"));
        position.put(100, new JLabel("100"));
        jsLightIntensity.setLabelTable(position);
        jsLightIntensity.addChangeListener(this);

        jlCurrenLightValue = new JLabel("");

        lightIntensityPanel.add(jlLightIcon);
        lightIntensityPanel.add(jlLightIntensity);
        lightIntensityPanel.add(jsLightIntensity);
        lightIntensityPanel.add(jlCurrenLightValue);

        // Heating
        JPanel heatingPanel = new JPanel();
        jlHeatingIcon = new JLabel("♨");
        jlHeating = new JLabel(" Verwarming aan bij: ");
        jlHeating.setFont(new Font(jlHeating.getFont().getFamily(), Font.PLAIN, 13));
        jlHeatingIcon.setFont(new Font(jlHeatingIcon.getFont().getFamily(), Font.PLAIN, 30));
        smHeatingValue = new SpinnerNumberModel(0, 0, 30, 1);
        spinner = new JSpinner(smHeatingValue);

        heatingPanel.add(jlHeatingIcon);
        heatingPanel.add(jlHeating);
        heatingPanel.add(spinner);
        heatingPanel.add(new JLabel(" °C"));

        center.add(lightIntensityPanel);
        center.add(heatingPanel);

        // BOTTOM
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel leftBottomPanel = new JPanel();
        jbDeleteProfile = new CButton(this, "Verwijder profiel", Color.black, Color.red);
        leftBottomPanel.add(jbDeleteProfile);
        jbStandardSettings = new CButton(this, "Standaard instellingen", Color.black, Color.white);
        leftBottomPanel.add(jbStandardSettings);


        JPanel rightBottomPanel = new JPanel();
        jbCancel = new CButton(this, "Annuleren", Color.black, Color.white);
        rightBottomPanel.add(jbCancel);
        jbSave = new CButton(this, "Opslaan", Color.black, Color.white);
        rightBottomPanel.add(jbSave);
        bottom.add(leftBottomPanel, BorderLayout.WEST);
        bottom.add(rightBottomPanel, BorderLayout.EAST);

        main.add(top, BorderLayout.NORTH);
        main.add(center, BorderLayout.WEST);
        main.add(bottom, BorderLayout.SOUTH);
        add(main);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean change = true; // Change the values with the (new) user PersonalSettings
        String confirmationText = "Weet u het zeker?";
        if (e.getSource() == jbCancel) {
            int choice = JOptionPane.showConfirmDialog(this, confirmationText, "Bevestiging annuleren", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.NO_OPTION){
                change = false; // Don't set the values with the user PersonalSettings
            }
        } else if (e.getSource() == jbSave) {
            int choice = JOptionPane.showConfirmDialog(this, confirmationText, "Bevestiging opslaan", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                int heating = (int) spinner.getValue();
                int light = jsLightIntensity.getValue();
                boolean result = Queries.updatePersonalSettings(light, heating, User.getUsername());
                if (result) {
                    User.setPersonalSettings(light, heating);
                    JOptionPane.showMessageDialog(this, "Instellingen geüpdated");
                }
            }
        } else if (e.getSource() == jbStandardSettings) {
            int choice = JOptionPane.showConfirmDialog(this, confirmationText, "Bevestiging naar standaardinstellingen", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                boolean result = Queries.updatePersonalSettings(25 ,16, User.getUsername());
                if (result) {
                    User.setStandardPersonalSettings();
                    JOptionPane.showMessageDialog(this, "Instellingen teruggezet naar standaardinstellingen");
                }
            }
        } else if (e.getSource() == jbDeleteProfile) {
            int choice = JOptionPane.showConfirmDialog(this, confirmationText, "Bevestiging verwijderen profiel", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                boolean result = Queries.deleteProfile(User.getUsername());
                if (result) {
                    String user = User.getUsername();
                    User.logOut();
                    changeFocus("ProfileView");
                    JOptionPane.showMessageDialog(this, "Profiel \"" + user + "\" is verwijderd");
                }
            }
        }
        if (change) {
            onFocus();
        }
        Audio.play("click.wav");
    }

    @Override
    public void onFocus() {
        jsLightIntensity.setValue(User.getLight());
        smHeatingValue.setValue(User.getTemperature());
    }

    @Override
    public void onShadow() {}

    @Override
    public void onTick(long now) {}

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == jsLightIntensity) {
            // update text field when the value of the slider changes
            JSlider source = (JSlider) e.getSource();
            jlCurrenLightValue.setText(source.getValue() + " %");
        }
    }
}