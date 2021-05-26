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
    private static int endpercentage=0;

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
            PreparedStatement myStmt = connection.prepareStatement("SELECT ps.PersonalSettingsID, ps.Light, ps.Temperature FROM PersonalSettings ps JOIN Profile p ON ps.ProfileID= p.ProfileID JOIN Person pr ON p.PersonID = pr.PersonID WHERE pr.Username = ?");
            myStmt.setString(1, User.getUsername());
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            Logging.logThis("Retrieving personal settings for user " + User.getUsername());
            
            /* [[PersonalSettingsID	, Light, Temperature]] */
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

    public static boolean isUsernameUsed(String username) {
        try {
            PreparedStatement myStmt = connection.prepareStatement("SELECT Username FROM Person WHERE Username = ?");
            myStmt.setString(1, username);
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            if (results.isEmpty()) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return true;
        }
        return true;
    }

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
        // Sends command to arduino to send light-value
        try {
            ar.getoutputstream('W');
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try {
            // RETRIEVE LAST SENSORDATA ROW
            PreparedStatement myStmt1 = connection.prepareStatement("SELECT Temperature, AirPressure, Humidity FROM DataCollectionRaspberry ORDER BY DataCollectionRaspberryID DESC LIMIT 1");
            ArrayList<ArrayList<String>> results = Database.query(myStmt1);

            // RETRIEVE LIGHT VALUE
            lightvalue = ar.getlightvalue();
            double topercent = lightvalue;
            double percent = (topercent/1024)*100;
            endpercentage = (int)percent;
            results.get(0).add(endpercentage+"");

            // INSERT LIGHT DATA
            PreparedStatement myStmt2 = connection.prepareStatement("INSERT INTO DataCollectionArduino (Light) VALUES (?)");
            myStmt2.setInt(1, Integer.parseInt(endpercentage+""));
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
            PreparedStatement myStmt = connection.prepareStatement("SELECT * FROM Playlist WHERE PersonalSettingsID	 = (SELECT PersonalSettingsID FROM PersonalSettings WHERE ProfileID = (SELECT ProfileID FROM Profile WHERE PersonID = (Select PersonID from Person WHERE username = ?)))");
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
            PreparedStatement myStmt = connection.prepareStatement("SELECT s.SongID, s.SongName, s.Duration FROM Song s JOIN LinkedSong ls ON s.SongID = ls.SongID JOIN Playlist p ON ls.PlaylistID = p.PlaylistID WHERE  ls.PlaylistID = ? AND p.PersonalSettingsID = ? ORDER BY ls.LinkedSongID ASC");
            myStmt.setInt(1, id);
            myStmt.setInt(2, User.getSettingsID());
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    // Not complete
    public static ArrayList<ArrayList<String>> getPlaylistSongsList(String playlistname) {
        try {
            PreparedStatement myStmt = connection.prepareStatement("SELECT s.SongID, s.SongName, s.Duration FROM Song s JOIN LinkedSong ls ON s.SongID = ls.SongID JOIN Playlist p ON p.PlaylistID = ls.PlaylistID WHERE p.PlaylistName = ? AND p.PersonalSettingsID = ? ORDER BY ls.LinkedSongID ASC");
            myStmt.setString(1, playlistname);
            myStmt.setInt(2, User.getSettingsID());
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            return results;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static boolean newPlaylist(String title, ArrayList<ArrayList<String>> songs) {
        if (!isPlaylistNameUsed(title)) {
            try {
                PreparedStatement myStmt_0 = connection.prepareStatement("INSERT INTO Playlist (PlaylistName,PersonalSettingsID) VALUES (?,?)");
                myStmt_0.setString(1, title);
                myStmt_0.setString(2, "" + User.getSettingsID());
                Database.query(myStmt_0);
            } catch (Exception ex) {
                System.out.println(ex);
                return false;
            }

            try {
                PreparedStatement myStmt_1 = connection.prepareStatement("SELECT PlaylistID FROM Playlist WHERE PlaylistName = ? AND PersonalSettingsID = ?");
                myStmt_1.setString(1, title);
                myStmt_1.setString(2, "" + User.getSettingsID());
                String playlist_id = Database.query(myStmt_1).get(0).get(0);

                for (ArrayList<String> song : songs) {
                    PreparedStatement myStmt_2 = connection.prepareStatement("INSERT INTO LinkedSong (SongID, PlaylistID) VALUES (?,?)");
                    myStmt_2.setString(1, song.get(0));
                    myStmt_2.setString(2, playlist_id);
                    Database.query(myStmt_2);
                }
                return true;
            } catch (Exception ex) {
                System.out.println(ex);
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean deletePlaylist(int playlist_id) {
        try {
            PreparedStatement myStmt_0 = connection.prepareStatement("DELETE FROM Playlist WHERE PlaylistID = ?");
            myStmt_0.setInt(1, playlist_id);
            Database.query(myStmt_0);
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }

        try {
            PreparedStatement myStmt_1 = connection.prepareStatement("DELETE FROM LinkedSong WHERE PlaylistID = ?");
            myStmt_1.setInt(1, playlist_id);
            Database.query(myStmt_1);
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }

        return true;
    }

    public static boolean isPlaylistNameUsed(String newPlaylistName) {
        try {
            PreparedStatement myStmt = connection.prepareStatement("SELECT PlaylistName FROM Playlist WHERE PlaylistName = ? AND PersonalSettingsID = ?");
            myStmt.setString(1, newPlaylistName);
            myStmt.setInt(2, User.getSettingsID());
            ArrayList<ArrayList<String>> results = Database.query(myStmt);
            if (results.isEmpty()) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return true;
        }
        return true;
    }

    public static int getEndpercentage() {
        return endpercentage;
    }
}