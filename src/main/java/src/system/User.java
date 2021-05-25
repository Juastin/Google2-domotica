package src.system;

import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;

public class User extends Queries{
    private static String username;
    private static int light;
    private static int temperature;
    private static int settingsID;

    public User() {}

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getUsername() {
        return username;
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
        settingsID = Integer.parseInt(results.get(0));
    }

    public static int getSettingsID() {
        return settingsID;
    }

    public static void setSettingsID(int settingsID) {
        User.settingsID = settingsID;
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
        setUsername("");
        setPersonalSettings(0, 0);
        setSettingsID(0);
    }
}