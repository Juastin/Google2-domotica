package src.models;

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

    public static void setLight(int light) {
        User.light = light;
    }

    public static int getTemperature() {
        return temperature;
    }

    public static void setTemperature(int temperature) {
        User.temperature = temperature;
    }

    public static int getPlaylistID() {
        return playlistID;
    }

    public static void setPlaylistID(int playlistID) {
        User.playlistID = playlistID;
    }
}