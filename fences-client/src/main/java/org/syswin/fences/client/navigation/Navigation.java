package org.syswin.fences.client.navigation;

import org.syswin.fences.client.about.About;
import org.syswin.fences.client.contact.Contact;
import org.syswin.fences.client.home.Home;
import org.syswin.fences.client.login.Login;
import org.syswin.fences.client.options.devices.logs.Logs;
import org.syswin.fences.client.options.devices.map.Map;
import org.syswin.fences.client.options.devices.statistics.Statistics;
import org.syswin.fences.client.options.users.Users;

/**
 * This is the Navigation enum used
 */
public enum Navigation {
    ABOUT       (About.instance,      About.TOKEN),
    CONTACT     (Contact.instance,    Contact.TOKEN),
    LOGIN       (Login.instance,      Login.TOKEN),
    HOME        (Home.instance,       Home.TOKEN),
    LOGS        (Logs.instance,       Logs.TOKEN),
    MAP         (Map.instance,        Map.TOKEN),
    STATISTICS  (Statistics.instance, Statistics.TOKEN),
    USERS       (Users.instance,      Users.TOKEN),;

    private Page   instance;
    private String token;

    private Navigation (Page instance, String token) {
        this.instance = instance;
        this.token = token;
    }

    public static Page getInstance (String token) {
        for (Navigation pageNav : Navigation.values ()) {
            if (pageNav.token != null && pageNav.token.equalsIgnoreCase (token)) {
                return pageNav.instance;
            }
        }

        return null;
    }
}
