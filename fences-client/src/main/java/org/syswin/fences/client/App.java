package org.syswin.fences.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

import org.fusesource.restygwt.client.Defaults;
import org.syswin.fences.client.home.Home;
import org.syswin.fences.client.login.Login;
import org.syswin.fences.client.session.Session;
import org.syswin.fences.client.user.User;

public class App implements EntryPoint {

    static {
        Defaults.setDateFormat(null);
    }

    @Override
    public void onModuleLoad() {
        String token = History.getToken();
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                navigate(event.getValue());
            } // onValueChange
        });
        if (token == null || token.length() == 0) {
            History.newItem(Login.TOKEN); // no token
        } else {
            navigate(token); // restore app state
        }
    }

    public static void navigate(String token) {
        RootPanel rootPanel = RootPanel.get();
        if (rootPanel.getWidgetCount() > 0) {
            rootPanel.clear(); // clear the page
        }

        if (Session.instance.getSessionDetails() == null) {
            History.newItem(Login.TOKEN);
            rootPanel.add(Login.instance);
            return;
        }

        if (Login.TOKEN.equals(token)) {
            rootPanel.add(Login.instance);
            return;
        }
        if (User.TOKEN.equals(token)) {
            rootPanel.add(User.instance);
            return;
        }



        rootPanel.add(Home.instance);
//          page.setAuthenticated(true);
//          page.setUsername(email);

    }
}
