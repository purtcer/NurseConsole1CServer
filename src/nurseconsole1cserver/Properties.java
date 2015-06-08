/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurseconsole1cserver;

import java.util.ArrayList;
import java.util.List;
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
    public void setCentralServers(ArrayList<List<String>> CentralServers){
        ClearCentralServers();
        PreferencesCentralServers = Preferences.userRoot().node(PreferenceServer);
        int leightCentralServers = CentralServers.size();
        for (int i = 0; i < leightCentralServers; i++) {
            String NumServer = Integer.toString(i);
            String ServerNum = serverNodeName + NumServer;
            PreferencesCentralServerNode = PreferencesCentralServers.userRoot().node(ServerNum);
            AddCentralServerToPreferences(PreferencesCentralServerNode, CentralServers.get(i));
        }
    }
    
    public ArrayList<List<String>> getCentralServers(){
        
        PreferencesCentralServers = Preferences.userRoot().node(PreferenceServer);
        int leightCentralServers = 0;
        String[] centralServersPreference;
        try {
            centralServersPreference = PreferencesCentralServers.keys();
            leightCentralServers = centralServersPreference.length;
        } catch (BackingStoreException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<List<String>> CentralServers = new ArrayList<List<String>>();
        if (leightCentralServers > 0){
            for (int i = 0; i < leightCentralServers; i++) {
                String NumServer = Integer.toString(i);
                String ServerNum = serverNodeName + NumServer;
                PreferencesCentralServerNode = PreferencesCentralServers.userRoot().node(ServerNum);
                ArrayList<String> nodeServer = getNodeServer(PreferencesCentralServerNode);
                CentralServers.add(nodeServer);
            }
        }        
        
        return CentralServers;        
    }
    
    /**
     * Очищает список ранее хранимых серверов
     */
    private void ClearCentralServers(){
        PreferencesCentralServers = Preferences.userRoot().node(PreferenceServer);
        try {
            PreferencesCentralServers.clear();
        } catch (BackingStoreException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Записывает сервер 1С в настройки приложения
     * @param PreferencesCentralServerNode Узел настройки сервера
     * @param CentralServerElement данные о сохраняемом сервере
     */
    private void AddCentralServerToPreferences(Preferences PreferencesCentralServerNode, List<String> CentralServerElement) {
        PreferencesCentralServerNode.put("ServerVersion", CentralServerElement.get(0));
        PreferencesCentralServerNode.put("NetProtocol", CentralServerElement.get(1));
        PreferencesCentralServerNode.put("ServerPC", CentralServerElement.get(2));
        PreferencesCentralServerNode.put("ServerPort", CentralServerElement.get(3));
    }
    
    private ArrayList<String> getNodeServer(Preferences PreferencesCentralServerNode){
        
        ArrayList<String> nodeServer = new ArrayList<String>();
        String nodeValue = "";
        PreferencesCentralServerNode.get("ServerVersion", nodeValue);
        nodeServer.add(nodeValue);
        PreferencesCentralServerNode.get("NetProtocol", nodeValue);
        nodeServer.add(nodeValue);
        PreferencesCentralServerNode.get("ServerPC", nodeValue);
        nodeServer.add(nodeValue);
        PreferencesCentralServerNode.get("ServerPort", nodeValue);
        nodeServer.add(nodeValue);
        
        return nodeServer;
    }
}
