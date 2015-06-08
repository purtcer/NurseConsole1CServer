/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurseconsole1cserver;

/**
 *
 * @author User
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NurseConsole1CServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        PopupMenu popup = new PopupMenu();
//
//        MenuItem exitItem = new MenuItem(EXIT_MENU_ITEM_TEXT);
//
//        exitItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        popup.add(exitItem);

        SystemTray systemTray = SystemTray.getSystemTray();

        String imageUrl = new File("").getAbsolutePath() + "\\Good.png";

        Image image = Toolkit.getDefaultToolkit().getImage(imageUrl);

        TrayIcon trayIcon = new TrayIcon(image);

        trayIcon.setImageAutoSize(true);

        try {
            systemTray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(NurseConsole1CServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
