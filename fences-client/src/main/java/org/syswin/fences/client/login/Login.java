package org.syswin.fences.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import org.syswin.fences.client.navigation.Page;
import org.syswin.fences.client.session.Session;
import org.syswin.fences.client.session.SessionDetails;
import org.syswin.fences.client.user.User;

public class Login extends Page {

    interface Binder extends UiBinder<Widget, Login> {

    }

    public static final String TOKEN = "login";
    public static final Login instance = new Login();

    private Login() {
        initWidget(GWT.<Binder>create(Binder.class).createAndBindUi(this));
    }

    @UiField
    public InputElement username;

    @UiField
    InputElement password;

    @UiField
    Button login;

    @Override
    protected void onLoad() {
        super.onLoad();
        login.addMouseDownHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                if (username.getValue().equals("root") && password.getValue().equals("root")) {
                    Session.instance.setSessionDetails(new SessionDetails(username.getValue(), password.getValue()));
                    History.newItem(User.TOKEN);
                } else {
                    Window.alert("Please use root & root!");
                }
            }
        });

    }
}