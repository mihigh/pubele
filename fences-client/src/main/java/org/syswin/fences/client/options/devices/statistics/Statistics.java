package org.syswin.fences.client.options.devices.statistics;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * The STATISTICS web page tab.
 */
public class Statistics extends Composite {

    interface Binder extends UiBinder<Widget, Statistics> {
    }

    public static final String TOKEN = "statistics";
    public static final Statistics instance = new Statistics();

    private Statistics() {
        initWidget(GWT.<Binder>create (Binder.class).createAndBindUi(this));
    }
}
