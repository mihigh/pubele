package org.syswin.fences.client.about;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.syswin.fences.client.navigation.Page;

/**
 * The ABOUT web page tab.
 */
public class About extends Page {

    interface Binder extends UiBinder<Widget, About> {
    }

    public static final String TOKEN = "about";
    public static final About instance = new About();

    private About() {
        initWidget(GWT.<Binder>create (Binder.class).createAndBindUi(this));
    }
}
