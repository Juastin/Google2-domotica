package src.system;

import com.fazecast.jSerialComm.SerialPort;

public class User {
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
        if(!isportopen){
            comPort.openPort();
            isportopen=true;
        }
        byte[] b = new byte[5];
        int l = comPort.readBytes(b, 5);
        String s = new String(b);
        try{
            laatstelichtwaarde=Integer.parseInt(s.trim());
            return Integer.parseInt(s.trim());}catch (NumberFormatException e){return laatstelichtwaarde;}
    }

    public static int getTemperature() {
        return temperature;
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