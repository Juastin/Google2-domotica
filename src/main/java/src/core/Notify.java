package src.core;
import java.awt.*;
import java.awt.TrayIcon.MessageType;

import src.Main;

public class Notify {

    /**
    * <pre>
    * Used to display a Windows notification.
    * 
    * Example usage:
    * Notify.notification("My App", "Hello World", "This is a notification", "info");
    * </pre>
    *
    * @param  app_name      tooltip displayed when the tray icon is hovered
    * @param  title         title to be displayed on the notification
    * @param  description   description to be displayed on the notification
    * @param  type          determines the displayed icon, use either: info, error or warning
    */
    public static void notification(String app_name, String title, String description, String type) {
        if (System.getProperty("os.name").equals("Windows 10") && Main.enableNotifications) {
            try {
                SystemTray tray = SystemTray.getSystemTray();
                Image image = Toolkit.getDefaultToolkit().createImage("some-icon.png");
                TrayIcon trayIcon = new TrayIcon(image, app_name);
                trayIcon.setImageAutoSize(true);
                trayIcon.setToolTip(app_name);
                tray.add(trayIcon);
            
                MessageType messageType = MessageType.INFO;
                if (type.equals("info")) {
                    messageType = MessageType.INFO;
                } else if (type.equals("error")) {
                    messageType = MessageType.ERROR;
                } else if (type.equals("warning")) {
                    messageType = MessageType.WARNING;
                }
    
                trayIcon.displayMessage(title, description, messageType);
            } catch(Exception ex) {
                System.err.print(ex);
            }
        } else {
            System.out.println("Notify.java: Current OS does not support this kind of notification.");
        }
    }
}
