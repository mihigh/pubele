package org.syswin.fences.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

import org.fusesource.restygwt.client.Defaults;

public class GIGI extends Composite {

    public static final String TOKEN = "gigi";

    static {
        Defaults.setDateFormat(null);
    }

    @Override
    protected void onLoad() {
        initWidget(this);
        Button button = new Button("OK");
        RootPanel.get().add(button);
    }
}