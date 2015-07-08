package nurseconsole1cserver;

import nurseconsole1cserver.ServersSettings.ServersSettingsForm;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import nurseconsole1cserver.Notifications.EventsSettingsForm;
import nurseconsole1cserver.ServerAuthentication.ServerAuthenticationForm;

public class NurseConsole1CServer {

    static TrayIcon trayIcon;
    static Integer timerPeriod = 1000 * 60 * 1;
    static Timer timerServersTest = new Timer(timerPeriod, new TimerActivationEvents());
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PopupMenu popup = new PopupMenu();
        
        MenuItem authenticationItem = new MenuItem("Авторизация");
        authenticationItem.addActionListener(new AuthenticationButtonClick());
        popup.add(authenticationItem);
        
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
        Image image = getImageTray(true);
        
        trayIcon = new TrayIcon(image);
        trayIcon.setImageAutoSize(true);
        trayIcon.setPopupMenu(popup);
        try {
            systemTray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(NurseConsole1CServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        timerServersTest.start();
    }
    
    private static Image getImageTray(Boolean isGood){
        String imageName = (isGood)?("Circle_Green.png"):("Circle_Red.png");
        URL imageUrl = NurseConsole1CServer.class.getResource("Images/" + imageName);
        Image image = Toolkit.getDefaultToolkit().getImage(imageUrl);
        
        return image;
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
    
    static class AuthenticationButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ServerAuthenticationForm serverAuthenticationSettingsForm = new ServerAuthenticationForm();
            serverAuthenticationSettingsForm.show();
        }
    }
    
    static class TimerActivationEvents implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timerServersTest.stop();
            Image image = getImageTray(false);
            trayIcon.setImage(image);
            timerServersTest.start();
        }
    }
}
