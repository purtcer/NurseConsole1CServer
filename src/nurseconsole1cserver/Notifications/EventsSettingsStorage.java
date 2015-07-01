/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurseconsole1cserver.Notifications;

import java.util.prefs.Preferences;

/**
 *
 * @author User
 */
public class EventsSettingsStorage {
    
    String PreferenceEvents = "EventsSettings";
    Preferences PreferencesEvents = Preferences.userRoot().node(PreferenceEvents);
    
    public void setEventsSettings(String[]EventsSettings){
        PreferencesEvents.put("Email", (String)EventsSettings[0]);
        PreferencesEvents.put("ClientId", (String)EventsSettings[1]);
        PreferencesEvents.put("ClientSecret", (String)EventsSettings[2]);
        PreferencesEvents.put("Name", (String)EventsSettings[3]);
        PreferencesEvents.put("TimeCorrection", (String)EventsSettings[4]);
    }
    
    public String[] getEventsSettings(){
       String eMail = PreferencesEvents.get("Email", "");
       String clientId = PreferencesEvents.get("ClientId", "");
       String clientSecret = PreferencesEvents.get("ClientSecret", "");
       String name = PreferencesEvents.get("Name", "");
       String timeCorrection = PreferencesEvents.get("TimeCorrection", "");
       String[] eventsSettings = {eMail, clientId, clientSecret, name, timeCorrection};
       
       return eventsSettings;
    }
}