package src.system;
import src.core.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Queries {
    static Connection verbinding = Database.maakVerbinding();

    public static boolean isPasswordCorrect(String username, String password) {
        try {
            PreparedStatement myStmt = verbinding.prepareStatement("SELECT COUNT(Username) FROM Person WHERE Username = ? AND PasswordHash = ?");
            myStmt.setString(1, username);
            myStmt.setString(2, password);
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            return Integer.parseInt(results.get(0).get(0))>0;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static ArrayList<ArrayList<String>> getProfiles() {
        try {
            PreparedStatement myStmt = verbinding.prepareStatement("SELECT username FROM Person");
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static ArrayList<ArrayList<String>> getPersonalSettings(String username) {
        try {
            PreparedStatement myStmt = verbinding.prepareStatement("SELECT ps.Light, ps.Temperature, ps.PlaylistID FROM PersonalSettings ps JOIN Person p ON ps.ProfileID = p.PersonID WHERE p.Username = ?");
            myStmt.setString(1, username);
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}