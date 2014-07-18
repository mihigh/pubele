package org.syswin.fences.client.options.devices.map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * The MAP web page tab.
 */
public class Map extends Composite {

    interface Binder extends UiBinder<Widget, Map> {
    }

    public static final String TOKEN = "map";
    public static final Map instance = new Map();

    private Map() {
        initWidget(GWT.<Binder>create (Binder.class).createAndBindUi(this));
    }
}
