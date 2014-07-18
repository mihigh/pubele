package org.syswin.fences.client.options.devices.logs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.syswin.fences.client.navigation.Page;

/**
 * The LOGS web page tab.
 */
public class Logs extends Page {

    interface Binder extends UiBinder<Widget, Logs> {
    }

    public static final String TOKEN = "logs";
    public static final Logs instance = new Logs();

    private Logs() {
        initWidget(GWT.<Binder>create (Binder.class).createAndBindUi(this));
    }
}
