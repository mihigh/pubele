package org.syswin.fences.client.options.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * The USERS web page tab.
 */
public class Users extends Composite {

    interface Binder extends UiBinder<Widget, Users> {
    }

    public static final String TOKEN = "users";
    public static final Users instance = new Users();

    private Users() {
        initWidget(GWT.<Binder>create (Binder.class).createAndBindUi(this));
    }
}
