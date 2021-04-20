package src.models;

public class User {
    private static boolean isLoggedIn = false;
    private static String username;

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
}