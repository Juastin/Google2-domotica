package src.views;

import src.components.CButton;
import src.core.*;
import src.system.Queries;
import src.system.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class PersonalSettingsView extends SubPanel implements ActionListener {

    private JLabel jlTitel, jlLightIntensity, jlHeating;
    private JButton jbCancel, jbSave, jbStandardSettings;
    private JSlider jsLightIntensity;
    private SpinnerModel smHeatingValue;
    private JSpinner spinner;

    public PersonalSettingsView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout(30, 20));
        setVisible(false);

        // TOP
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        jlTitel = new JLabel("Persoonlijke instellingen");
        jlTitel.setFont(jlTitel.getFont().deriveFont(20.0f));
        top.add(jlTitel, BorderLayout.WEST);
        add(top, BorderLayout.NORTH);

        // CENTER
        JPanel center = new JPanel();
//        center.setLayout(new BorderLayout());
        center.setLayout(new GridLayout(2, 1));
        center.setLayout(new GridLayout(2, 1, 15, 30));

        // LightIntensity
        JPanel lightIntensityPanel = new JPanel();
        // Coming soon: Picture light
        jlLightIntensity = new JLabel("Licht aan vanaf lichtintensiteit: ");
        jsLightIntensity = new JSlider(JSlider.HORIZONTAL, 0, 100, 38);
        jsLightIntensity.setMajorTickSpacing(25);
        jsLightIntensity.setMinorTickSpacing(10);
//        jsLightIntensity.setPaintTicks(true);
        jsLightIntensity.setPaintLabels(true);

        Hashtable position = new Hashtable();
        position.put(0, new JLabel("0"));
        position.put(50, new JLabel("50"));
        position.put(100, new JLabel("100"));
        jsLightIntensity.setLabelTable(position);

        lightIntensityPanel.add(jlLightIntensity);
        lightIntensityPanel.add(jsLightIntensity);
        lightIntensityPanel.add(new JLabel(" %"));

        // Heating
        JPanel heatingPanel = new JPanel();
        // Coming soon: Picture heating
        jlHeating = new JLabel("Verwarming aan bij: ");
        smHeatingValue = new SpinnerNumberModel(15, 0, 30, 1);
        spinner = new JSpinner(smHeatingValue);
//        spinner.setBounds(100,100,50,30);

        heatingPanel.add(jlHeating);
        heatingPanel.add(spinner);
        heatingPanel.add(new JLabel(" °C"));
//        heatingPanel.setSize(300,300);
        center.add(lightIntensityPanel);
        center.add(heatingPanel);

        add(center, BorderLayout.WEST);

        // BOTTOM
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel leftBottomPanel = new JPanel();
        jbStandardSettings = new CButton(this, "Standaard instellingen", Color.black, Color.white);
        leftBottomPanel.add(jbStandardSettings);

        JPanel rightBottomPanel = new JPanel();
        jbCancel = new CButton(this, "Annuleren", Color.black, Color.white);
        rightBottomPanel.add(jbCancel);
        jbSave = new CButton(this, "Opslaan", Color.black, Color.white);
        rightBottomPanel.add(jbSave);
        bottom.add(leftBottomPanel, BorderLayout.WEST);
        bottom.add(rightBottomPanel, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancel) {
            int choice = JOptionPane.showConfirmDialog(this, "Weet u het zeker?", "Bevestiging", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                // CANCEL
                smHeatingValue.setValue(15);
                changeFocus("MainScreenView");
            }
        } else if (e.getSource() == jbSave) {
            int choice = JOptionPane.showConfirmDialog(this, "Weet u het zeker?", "Bevestiging", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                // UPDATE SETTINGS
                System.out.println("INSERT NEW SETTINGS");
                int light = 50;
                int heating = 50;
                boolean result = Queries.updatePersonalSettings(light, heating, User.getUsername());
                if (result) {
                    JOptionPane.showMessageDialog(this, "Instelling geüpdated");
                }
            }
        } else if (e.getSource() == jbStandardSettings) {
            int choice = JOptionPane.showConfirmDialog(this, "Weet u het zeker?", "Bevestiging", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                // UPDATE TO STANDARD SETTINGS
                System.out.println("STANDARD SETTINGS");
                boolean result = Queries.setStandardProfileSettings(User.getUsername());
                if (result) {
                    JOptionPane.showMessageDialog(this, "Instelling naar Standaardinstellingen gezet");
                }
            }
        }
        Audio.play("click.wav");
    }

    @Override
    public void onFocus() {
        System.out.println("Light: " + User.getLight());
        System.out.println("Temperature: " + User.getTemperature());
        System.out.println("PLaylistId: " + User.getPlaylistID());
    }

    @Override
    public void onShadow() {}

    @Override
    public void onTick(long now) {}
    
}