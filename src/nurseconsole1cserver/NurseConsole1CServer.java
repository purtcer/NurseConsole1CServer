package nurseconsole1cserver;

import nurseconsole1cserver.ServersSettings.ServersSettingsForm;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import nurseconsole1cserver.Notifications.EventsSettingsForm;

public class NurseConsole1CServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PopupMenu popup = new PopupMenu();
        
        MenuItem notificationItem = new MenuItem("Настройки уведомлений");
        notificationItem.addActionListener(new NotificationButtonClick());
        popup.add(notificationItem);
        
        MenuItem settingsItem = new MenuItem("Настройки серверов 1С");
        settingsItem.addActionListener(new ServersSettingsButtonClick());
        popup.add(settingsItem);
        
        MenuItem exitItem = new MenuItem("Выход");
        exitItem.addActionListener(new ExitButtonClick());
        popup.add(exitItem);
        
        SystemTray systemTray = SystemTray.getSystemTray();
        URL imageUrl = NurseConsole1CServer.class.getResource("Images/Circle_Green.png");
        Image image = Toolkit.getDefaultToolkit().getImage(imageUrl);
        
        TrayIcon trayIcon = new TrayIcon(image);
        trayIcon.setImageAutoSize(true);
        trayIcon.setPopupMenu(popup);
        try {
            systemTray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(NurseConsole1CServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    static class ExitButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    static class ServersSettingsButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ServersSettingsForm serververSettingsForm = new ServersSettingsForm();
            serververSettingsForm.show();
        }
    }
    
    static class NotificationButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            EventsSettingsForm eventsSettingsForm = new EventsSettingsForm();
            eventsSettingsForm.show();
        }
    }
    
}
