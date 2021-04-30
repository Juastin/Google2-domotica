package src.system;

public class User {
    private static boolean isLoggedIn = false;
    private static String username;
    private static int light;
    private static int temperature;
    private static int playlistID;

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
        User.light = 25;
        User.temperature = 16;
    }

    public static void logOut() {
        setLoggedIn(false);
        setUsername("");
        setPersonalSettings(0, 0);
        setPlaylistID(0);
    }
}