package src.views;
import src.core.Audio;
import src.core.Container;
import src.core.View;
import src.components.*;
import src.system.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Index extends View implements ActionListener {
    private JButton jbNewProfile;
    private JLabel jlKies, jlProfileAmount;
    private ArrayList<CButton> userButtons;
    private JPanel center, userGrid;
    private ArrayList<ArrayList<String>> users;

    public Index(Container container, String name) {
        super(container, name);
        setLayout(new BorderLayout());
        setVisible(false);

        // TOP
        JPanel top = new JPanel();
        top.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 0));
        jlKies = new JLabel("Kies uw profiel", SwingConstants.CENTER);
        jlKies.setFont(jlKies.getFont().deriveFont(24.0f));
        jlKies.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jlKies.setPreferredSize(new Dimension(222, 50));
        top.add(jlKies);
        add(top, BorderLayout.NORTH);

        // CENTER
        center = new JPanel();
        userGrid = new JPanel();
        center.add(userGrid);
        add(center, BorderLayout.CENTER);

        // BOTTOM
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel nieuwProfielButtonPanel = new JPanel();
        jbNewProfile = new CButton(this, "＋", Color.black, Color.white);
        jbNewProfile.setFont(new Font(jbNewProfile.getFont().getFamily(), Font.PLAIN, 24));
        nieuwProfielButtonPanel.add(jbNewProfile);
        bottom.add(nieuwProfielButtonPanel, BorderLayout.NORTH);

        JPanel nieuwProfielLabelPanel = new JPanel();
        jlProfileAmount = new JLabel();
        nieuwProfielLabelPanel.add(jlProfileAmount, BorderLayout.SOUTH);
        bottom.add(nieuwProfielLabelPanel, BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);
    }

    public void updateProfileList() {
        // GET PROFILES FROM DB
        users = Queries.getProfiles();

        userGrid.setPreferredSize(new Dimension(600, 20*users.size()));
        GridLayout userGridLayout = new GridLayout((users.size()/3), 3);
        userGridLayout.setHgap(10); userGridLayout.setVgap(10);
        userGrid.setLayout(userGridLayout);

        userGrid.removeAll();
        userButtons = new ArrayList<CButton>();

        for (ArrayList<String> row: users) {
            CButton button = new CButton(this, row.get(0), Color.black, Color.white);
            button.setFont(new Font(button.getFont().getFamily(), Font.PLAIN, 16));
            userGrid.add(button);
            userButtons.add(button);
        }

        jlProfileAmount.setText("Profiel toevoegen (" + users.size() + "/15)");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbNewProfile) {
            changeFocus("MakeProfileView");
        }

        for (CButton button: userButtons) {
            if (e.getSource() == button) {
                User.setUsername(button.getText());
                changeFocus("LoginView");
            }
        }

        Audio.play("click.wav");
    }

    @Override
    public void onFocus() {
        updateProfileList();
    }

    @Override
    public void onShadow() {}

    @Override
    public void onTick(long now) {}

}
