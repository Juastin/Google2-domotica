package src.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logging {
    
    public static boolean logThis(String description) {
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
            Database.query("INSERT INTO Logging (time, description) VALUES ('" + time + "', '" + description + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
