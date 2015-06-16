/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurseconsole1cserver;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Properties {
    
    Preferences PreferencesCentralServers;
    Preferences PreferencesCentralServerNode;
    String PreferenceServer = "CentralServers";
    String serverNodeName = "Server";
    
    /**
     * Записывает список серверов 1С, которые необходимо провверять
     * @param CentralServers список центральных серверов 1С, которые необходимо хранить
     */
    public void setCentralServers(Vector CentralServers){
        ClearCentralServers();
        PreferencesCentralServers = Preferences.userRoot().node(PreferenceServer);
        int leightCentralServers = CentralServers.size();
        for (int i = 0; i < leightCentralServers; i++) {
            String NumServer = Integer.toString(i);
            String ServerNum = serverNodeName + NumServer;
            PreferencesCentralServerNode = PreferencesCentralServers.node(ServerNum);
            AddCentralServerToPreferences(PreferencesCentralServerNode, (Object[])(CentralServers.get(i)));
        }
    }
    
    /**
     * Получает ранее сохраненный список серверов 1С,  которые необходимо провверять
     * @return список центральных серверов 1С, которые необходимо хранить
     */
    public Vector getCentralServers(){
        
        PreferencesCentralServers = Preferences.userRoot().node(PreferenceServer);
        int leightCentralServers = 0;
        String[] centralServersPreference;
        try {
            centralServersPreference = PreferencesCentralServers.childrenNames();
            leightCentralServers = centralServersPreference.length;
        } catch (BackingStoreException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
        Vector CentralServers = new Vector();
        if (leightCentralServers > 0){
            for (int i = 0; i < leightCentralServers; i++) {
                String NumServer = Integer.toString(i);
                String ServerNum = serverNodeName + NumServer;
                PreferencesCentralServerNode = PreferencesCentralServers.node(ServerNum);
                Object[] nodeServer = serverSettings(PreferencesCentralServerNode);
                CentralServers.add(nodeServer);
            }
        }        
        
        return CentralServers;        
    }
    
    private void ClearCentralServers(){
        PreferencesCentralServers = Preferences.userRoot().node(PreferenceServer);
        try {
            PreferencesCentralServers.clear();
        } catch (BackingStoreException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void AddCentralServerToPreferences(Preferences PreferencesCentralServerNode, Object[] CentralServerElement) {
        PreferencesCentralServerNode.put("ServerVersion", (String)CentralServerElement[0]);
        PreferencesCentralServerNode.put("ServerPC", (String)CentralServerElement[1]);
        PreferencesCentralServerNode.put("ServerPort", (String)CentralServerElement[2]);
    }
    
    private Object[] serverSettings(Preferences PreferencesCentralServerNode){
        
        String serverVersion =  PreferencesCentralServerNode.get("ServerVersion", "");
        String serverPC =  PreferencesCentralServerNode.get("ServerPC", "");
        String serverPort =  PreferencesCentralServerNode.get("ServerPort", "");
        Object[] nodeServer = {serverVersion, serverPC, serverPort};
        
        return nodeServer;
    }
}
