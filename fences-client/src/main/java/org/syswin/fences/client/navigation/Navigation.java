package org.syswin.fences.client.navigation;

import com.google.gwt.user.client.ui.Composite;
import org.syswin.fences.client.about.About;
import org.syswin.fences.client.contact.Contact;
import org.syswin.fences.client.home.Home;
import org.syswin.fences.client.login.Login;
import org.syswin.fences.client.options.devices.logs.Logs;
import org.syswin.fences.client.options.devices.map.Map;
import org.syswin.fences.client.options.devices.statistics.Statistics;
import org.syswin.fences.client.options.users.Users;

import java.util.HashMap;

/**
 * This is the Navigation class used for mapping tokens to classes instances for the GWT
 */
public final class Navigation {

    private final static HashMap<String, Composite> navigationMap = new HashMap<String, Composite> ();

    static{
        navigationMap.put (About.TOKEN,      About.instance);
        navigationMap.put (Contact.TOKEN,    Contact.instance);
        navigationMap.put (Login.TOKEN,      Login.instance);
        navigationMap.put (Home.TOKEN,       Home.instance);
        navigationMap.put (Logs.TOKEN,       Logs.instance);
        navigationMap.put (Map.TOKEN,        Map.instance);
        navigationMap.put (Statistics.TOKEN, Statistics.instance);
        navigationMap.put (Users.TOKEN,      Users.instance);
    }

    private Navigation () {}

    public static Composite getInstance (String token) {
        return navigationMap.get (token);
    }
}
