package org.syswin.fences.client.contact;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.syswin.fences.client.navigation.Page;

/**
 * The CONTACT web page tab.
 */
public class Contact extends Page {

    interface Binder extends UiBinder<Widget, Contact> {
    }

    public static final String TOKEN = "contact";
    public static final Contact instance = new Contact();

    private Contact() {
        initWidget(GWT.<Binder>create (Binder.class).createAndBindUi(this));
    }
}
