package src.system;

import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;

public class User extends Queries{
    private static boolean isLoggedIn = false;
    private static String username;
    private static int light;
    private static int temperature;
    private static int playlistID;

    private static boolean isportopen=false;
    final static SerialPort comPort = SerialPort.getCommPort("COM3");
    static int laatstelichtwaarde=0;

    public User() {}

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getUsername() {
        return username;
    }

    public static void setLoggedIn(boolean isLoggedIn) {
        User.isLoggedIn = isLoggedIn;
    }

    public static boolean getLoggedIn() {
        return isLoggedIn;
    }

    public static int getLight() {
        return light;
    }

    public static int getTemperature() {
        return temperature;
    }

    public static void refreshPersonalSettings() {
        ArrayList<String> results = getPersonalSettings().get(0);
        light = Integer.parseInt(results.get(1));
        temperature = Integer.parseInt(results.get(2));
        try {
            playlistID = Integer.parseInt(results.get(3));
        } catch (Exception ex) {}
    }

    public static int getPlaylistID() {
        return playlistID;
    }

    public static void setPlaylistID(int playlistID) {
        User.playlistID = playlistID;
    }

    public static void setPersonalSettings(int light, int temperature) {
        User.light = light;
        User.temperature = temperature;
    }

    public static void setStandardPersonalSettings() {
        User.light = 40;
        User.temperature = 17;
    }

    public static void logOut() {
        setLoggedIn(false);
        setUsername("");
        setPersonalSettings(0, 0);
        setPlaylistID(0);
    }
}