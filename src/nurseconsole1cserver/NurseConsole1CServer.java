package nurseconsole1cserver;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NurseConsole1CServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PopupMenu popup = new PopupMenu();
        
        MenuItem settingsItem = new MenuItem("Сервера");
        settingsItem.addActionListener(new ServersSettingsButtonClick());
        popup.add(settingsItem);
        
        MenuItem exitItem = new MenuItem("Выход");
        exitItem.addActionListener(new ExitButtonClick());
        popup.add(exitItem);
        
        SystemTray systemTray = SystemTray.getSystemTray();
        String imageUrl = new File("").getAbsolutePath() + "\\Good.png";
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
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    static class ServersSettingsButtonClick implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ServersSettings serververSettingsForm = new ServersSettings();
            serververSettingsForm.show();
        }
    }
    
}
