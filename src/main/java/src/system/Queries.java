package src.system;
import com.fazecast.jSerialComm.SerialPort;
import src.core.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Queries {
    static Connection connection = Database.maakVerbinding();
    static Arduino ar = new Arduino();
    private static int lightvalue = 0;



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

    public static ArrayList<ArrayList<String>> getPersonalSettings() {
        try {
            PreparedStatement myStmt = connection.prepareStatement("SELECT ps.InstellingenID, ps.Light, ps.Temperature FROM PersonalSettings ps JOIN Profile p ON ps.ProfileID= p.ProfileID JOIN Person pr ON p.PersonID = pr.PersonID WHERE pr.Username = ?");
            myStmt.setString(1, User.getUsername());
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            Logging.logThis("Retrieving personal settings for user " + User.getUsername());
            
            /* [[instellingenID, Light, Temperature]] */
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            Logging.logThis("Failed to retrieve personal settings for user " + User.getUsername());
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
        //Sends command to arduino to send light-value
        try {
            ar.getoutputstream('W');
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try {
            // RETRIEVE LAST SENSORDATA ROW
            PreparedStatement myStmt1 = connection.prepareStatement("SELECT DataCollectionID, Temperature, AirPressure, Humidity FROM DataCollection ORDER BY DataCollectionID DESC LIMIT 1");
            ArrayList<ArrayList<String>> results = Database.query(myStmt1);
            // RETRIEVE LIGHT VALUE
            lightvalue = ar.getlightvalue();
            results.get(0).add(lightvalue+"");

                // UPDATE LIGHT FIELD WITH SPECIFIC ID, WHERE LIGHT IS NULL
                PreparedStatement myStmt2 = connection.prepareStatement("UPDATE DataCollection SET Light = ? WHERE DataCollectionID = ? AND Light IS NULL");
                myStmt2.setInt(1, Integer.parseInt(lightvalue+""));
                myStmt2.setInt(2, Integer.parseInt(results.get(0).get(0)));
                Database.query(myStmt2);
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
  public static ArrayList<ArrayList<String>> getAllSongs() {
        try {
            PreparedStatement myStmt = connection.prepareStatement("SELECT * FROM Song ORDER BY SongID ASC");
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static ArrayList<ArrayList<String>> getPlaylistData(String username) {
        try {
            PreparedStatement myStmt = connection.prepareStatement("SELECT * FROM Playlist WHERE InstellingenID = (SELECT InstellingenID FROM PersonalSettings WHERE ProfileID = (SELECT ProfileID FROM Profile WHERE PersonID = (Select PersonID from Person WHERE username = ?)))");
            myStmt.setString(1, username);
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static ArrayList<ArrayList<String>> getPlaylistSongsList(int id) {
        try {
            PreparedStatement myStmt = connection.prepareStatement("SELECT * FROM Song s JOIN LinkedSong ls ON s.SongID = ls.SongID WHERE PlaylistID = ?");
            myStmt.setInt(1, id);
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static void newPlaylist(String title, ArrayList<ArrayList<String>> songs) {
        try {
            PreparedStatement myStmt_0 = connection.prepareStatement("INSERT INTO Playlist (PlaylistName,InstellingenID) VALUES (?,?)");
            myStmt_0.setString(1, title);
            myStmt_0.setString(2, ""+User.getSettingsID());
            Database.query(myStmt_0);
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }

        try {
            PreparedStatement myStmt_1 = connection.prepareStatement("SELECT PlaylistID FROM Playlist WHERE PlaylistName = ?");
            myStmt_1.setString(1, title);
            String playlist_id = Database.query(myStmt_1).get(0).get(0);

            for (ArrayList<String> song: songs) {
                PreparedStatement myStmt_2 = connection.prepareStatement("INSERT INTO LinkedSong (SongID, PlaylistID) VALUES (?,?)");
                myStmt_2.setString(1, song.get(0));
                myStmt_2.setString(2, playlist_id);
                Database.query(myStmt_2);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }
    }


}