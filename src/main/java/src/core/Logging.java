package src.core;
import src.Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Logging

EXAMPLE USAGE:
Logging.logThis("test");

Will log "test" locally and online using the Database class.
[2021-04-21 11:18:10] test

*/

public class Logging {
    
    public static void logThis(String description) {
        if (Main.enableLogging) {
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // LOG LOCAL
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
                writer.append("[" + time + "] " + description + "\n");
                writer.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
    
            // LOG ONLINE - IF CONNECTED
            try {
                Connection verbinding = Database.maakVerbinding();
                PreparedStatement myStmt = verbinding.prepareStatement("INSERT INTO Logging (TimeInfo, Description) VALUES (?, ?)");
                myStmt.setString(1, time);
                myStmt.setString(2, description);
                Database.query(myStmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
