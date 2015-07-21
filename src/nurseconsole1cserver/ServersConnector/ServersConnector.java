/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurseconsole1cserver.ServersConnector;

import com.jacob.activeX.ActiveXComponent;
import java.util.Vector;
import nurseconsole1cserver.ServersSettings.ServersSettingsStorage;
/**
 *
 * @author User
 */
public class ServersConnector {
    
    ActiveXComponent serverConnector;
    Vector serversList;
    
    public void ServersConnector(){
        ServersSettingsStorage serversSettings = new ServersSettingsStorage();
        serversList = serversSettings.getCentralServers();
        Integer serversCount = serversList.size();
        for (int i = 0; i < serversCount; i++) {
            ServerConnect((Object[]) serversList.get(i));
        }
    }
    
    private void ServerConnect(Object[] serverSettings){
        serverConnector = new ActiveXComponent(serverSettings[0] + ".COMConnector");
        
    }
    
}
