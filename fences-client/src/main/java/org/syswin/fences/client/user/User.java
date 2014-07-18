package org.syswin.fences.client.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import org.syswin.fences.client.session.Session;

public class User extends Composite {

    interface Binder extends UiBinder<Widget, User> {

    }

    public static final String TOKEN = "user";
    public static final User instance = new User();

    private User() {
        initWidget(GWT.<Binder>create(Binder.class).createAndBindUi(this));
        this.username.setText(Session.instance.getSessionDetails().getUsername());
    }

    @UiField
    public Label username;

    @Override
    protected void onLoad() {
        super.onLoad();
    }
}
