/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurseconsole1cserver.Notifications;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;
/**
 *
 * @author User
 */
public class ServerEvents {
    
    private static final String APPLICATION_NAME = "purtcer84@gmail.com";
    private static com.google.api.services.calendar.Calendar client;
    private static HttpTransport httpTransport;
    private static FileDataStoreFactory dataStoreFactory;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final java.io.File DATA_STORE_DIR =
      new java.io.File(new File("").getAbsolutePath() + "\\calendar_sample");
    private static final String CLIENT_SECRETS = "{\n" +
        "  \"installed\": {\n" +
        "    \"client_id\": \"23912429867-s8or91g0ontvjdt1aeobi4s6uflpsdki.apps.googleusercontent.com\",\n" +
        "    \"client_secret\": \"mX9QCErbdXptT5gBUWRWvE7r\"\n" +
        "  }\n" +
        "}";
    
    private static Credential authorize() throws Exception {
        // load client secrets
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
//            new InputStreamReader(CalendarNotification.class.getResourceAsStream("\\client_secrets.json")));
        
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
            new StringReader(CLIENT_SECRETS));
        
        // set up authorization code flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            httpTransport, JSON_FACTORY, clientSecrets,
            Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(dataStoreFactory)
            .build();
        // authorize
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
    
    private static void addEvent(Calendar calendar) throws IOException {    
        Event event = newEvent();
        Event result = client.events().insert(calendar.getId(), event).execute();
    }

    public ServerEvents(){
        try {
            // initialize the transport
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            // initialize the data store factory
            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
            Credential credential = authorize();
            client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, 
                JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
            Calendar calendar = addCalendar();    
            addEvent(calendar);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        } 
        catch (Throwable t) {
            System.err.println(t.getMessage());
        }
    }
    private static Calendar addCalendar() throws IOException {
        Calendar entry = new Calendar();
        entry.setSummary("Calendar for Testing 3");
        Calendar result = client.calendars().insert(entry).execute();
        return result;
    }   
    
    private static Event newEvent() {
        Event event = new Event();
        event.setSummary("New Event");
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000);
        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
        event.setStart(new EventDateTime().setDateTime(start));
        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
        event.setEnd(new EventDateTime().setDateTime(end));
        return event;
    }    
}