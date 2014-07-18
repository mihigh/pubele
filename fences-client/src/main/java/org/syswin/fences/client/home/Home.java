package org.syswin.fences.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import org.syswin.fences.client.login.Login;

public class Home extends Composite {

    interface Binder extends UiBinder<Widget, Home> {

    }

    public static final String TOKEN = "home";
    public static final Home instance = new Home();

    private Home() {
        initWidget(GWT.<Binder>create(Binder.class).createAndBindUi(this));
    }

    @UiField
    Button login;

    @Override
    protected void onLoad() {
        super.onLoad();
        login.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                History.newItem(Login.TOKEN);
            }
        });

    }
}