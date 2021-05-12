package src.system;
import com.fazecast.jSerialComm.SerialPort;
import src.core.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Queries {
    static Connection connection = Database.maakVerbinding();
    private static boolean isportopen=false;
    final static SerialPort comPort = SerialPort.getCommPort("COM3");
    static int laatstelichtwaarde=0;


    public static boolean isPasswordCorrect(String username, String password) {
        try {
            PreparedStatement myStmt = connection.prepareStatement("SELECT PasswordHash FROM Person WHERE Username = ?");
            myStmt.setString(1, username);
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            
            return Authentication.checkPassword(password, results.get(0).get(0));
        } catch (Exception ex) {
            System.out.println(ex);
            Logging.logThis("Failed login attempt for user " + username);
            return false;
        } 
    }

    public static ArrayList<ArrayList<String>> getProfiles() {
        try {
            PreparedStatement myStmt = connection.prepareStatement("SELECT username FROM Person");
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static ArrayList<ArrayList<String>> getPersonalSettings(String username) {
        try {
            PreparedStatement myStmt = connection.prepareStatement("SELECT ps.InstellingenID, ps.Light, ps.Temperature, ps.PlaylistID FROM PersonalSettings ps JOIN Profile p ON ps.ProfileID= p.ProfileID JOIN Person pr ON p.PersonID = pr.PersonID WHERE pr.Username = ?");
            myStmt.setString(1, username);
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            Logging.logThis("Retrieving personal settings for user " + username);
            
            /* [[instellingenID, Light, Temperature, PlaylistID]] */
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            Logging.logThis("Failed to retrieve personal settings for user " + username);
            return null;
        }
    }

    public static boolean makeNewProfile(String username, String firstname, String lastname, char[] password) {
        String hashed_password = Authentication.encryptPassword(password);

        try {
            PreparedStatement myStmt_0 = connection.prepareStatement("INSERT INTO Person (Username, FirstName, LastName, PasswordHash) VALUES (?,?,?,?)");
            myStmt_0.setString(1, username);
            myStmt_0.setString(2, firstname);
            myStmt_0.setString(3, lastname);
            myStmt_0.setString(4, hashed_password);
            Database.query(myStmt_0);
        } catch (Exception ex) {
            System.out.println(ex);
            Logging.logThis("Failed to make a new profile: (" + username + ", " + firstname + ", " + lastname + ")");
            return false;
        }

        Logging.logThis("New profile created for user " + username);
        return true;
    }

    // Standard values for function are: light 25, heating 16
    public static boolean updatePersonalSettings(int light, int heating, String username) {
        try {
            PreparedStatement myStmt = connection.prepareStatement("UPDATE PersonalSettings set Light = ?, Temperature = ? WHERE ProfileID = (SELECT ProfileID FROM Profile WHERE PersonID = (SELECT PersonID FROM Person WHERE Username = ?))");
            myStmt.setInt(1, light);
            myStmt.setInt(2, heating);
            myStmt.setString(3, username);
            Database.query(myStmt);
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public static boolean deleteProfile(String username) {
        try {
            PreparedStatement myStmt = connection.prepareStatement("DELETE FROM Person WHERE username = ?");
            myStmt.setString(1, username);
            Database.query(myStmt);
            Logging.logThis("Profile deleted for " + username);
        } catch (Exception ex) {
            System.out.println(ex);
            Logging.logThis("Could not delete profile for " + username);
            return false;
        }
        return true;
    }

    public static ArrayList<ArrayList<String>> getSensorData() {
        try {
            // HAAL LAATSTE ROW OP
            PreparedStatement myStmt1 = connection.prepareStatement("SELECT DataCollectionID, Temperature, AirPressure, Humidity FROM DataCollection ORDER BY DataCollectionID DESC LIMIT 1");
            ArrayList<ArrayList<String>> results = Database.query(myStmt1);

            // HAAL LICHT OP UIT ARDUINO
            if(!isportopen){
                comPort.openPort();
                isportopen = true;
            }

            byte[] b = new byte[5];
            int l = comPort.readBytes(b, 5);
            String s = new String(b);
            String lichtwaarde = "";

            try{
                laatstelichtwaarde = Integer.parseInt(s.trim());
                lichtwaarde = s;
                results.get(0).add(lichtwaarde);
            }catch (NumberFormatException e){
                lichtwaarde = laatstelichtwaarde + "";
                results.get(0).add(laatstelichtwaarde + "");
            }

            // UPDATE LICHT FIELD IN LAATSTE ROW MET SPECIFIEK ID
            if (lichtwaarde.equals("")) {
                Logging.logThis("Unable to access Arduino for user " + User.getUsername());
            } else {
                System.out.println("ee");
                PreparedStatement myStmt2 = connection.prepareStatement("UPDATE DataCollection SET Light = ? WHERE DataCollectionID = ? AND Light IS NULL");
                myStmt2.setInt(1, Integer.parseInt(lichtwaarde));
                myStmt2.setInt(2, Integer.parseInt(results.get(0).get(0)));
            }

            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}