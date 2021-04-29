package src;
import src.core.Frame;
import src.views.*;

public class Main {
    public static boolean enableLogging = false;
    public static boolean enableAudio = true;

    public static void main(String[] args) {
        Frame frame = new Frame();

        // Add custom views here
        frame.addPanel(new MakeProfileView(frame.getMainPanel(), "MakeProfileView"));
        frame.addPanel(new LoginView(frame.getMainPanel(), "LoginView"));
        frame.addPanel(new MainScreenView(frame.getMainPanel(), "MainScreenView"));
        frame.addPanel(new PersonalSettingsView(frame.getMainPanel(), "PersonalSettingsView"));
        frame.addPanel(new MusicPlayerView(frame.getMainPanel(), "MusicPlayerView"));
    }
}