package src;
import src.core.Frame;
import src.views.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static boolean enableLogging = false;
    public static boolean enableAudio = true;

    public static void main(String[] args) {
        Frame frame = new Frame();

        // Add custom views here
        frame.addView(new MakeProfileView(frame.getContainer(), "MakeProfileView"));
        frame.addView(new LoginView(frame.getContainer(), "LoginView"));
        frame.addView(new MainScreenView(frame.getContainer(), "MainScreenView"));
        frame.addView(new PersonalSettingsView(frame.getContainer(), "PersonalSettingsView"));
        frame.addView(new MusicPlayerView(frame.getContainer(), "MusicPlayerView"));
        frame.addView(new MusicMenuView(frame.getContainer(), "MusicMenuView"));
        frame.addView(new MusicMenuView(frame.getContainer(), "MusicMenuNewPlaylist"));

        frame.getContainer().switchView(frame.getContainer().getCurrentView(), "MusicMenuView", new ArrayList<String>(Arrays.asList("show jpNewPlaylist")));
    }
}