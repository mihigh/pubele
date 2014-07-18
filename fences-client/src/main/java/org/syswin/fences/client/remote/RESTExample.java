package org.syswin.fences.client.remote;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.syswin.fences.client.remote.PersonResourceAsync;
import org.syswin.fences.core.User;

public class RESTExample implements EntryPoint {

    public static final String TOKEN = "login";
    private FlexTable personsTable;

    static {
        Defaults.setDateFormat(null);
    }

    @Override
    public void onModuleLoad() {
        Button button = new Button("LOGIN -- load person");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                loadPersons();
            }
        });
        personsTable = new FlexTable();
        personsTable.setCellPadding(1);
        personsTable.setCellSpacing(0);
        personsTable.setWidth("600px");
        RootPanel.get().add(button);
        RootPanel.get().add(personsTable);
    }

    private void loadPersons() {
        PersonResourceAsync.Util.get().getPersons(new MethodCallback<User>() {
            @Override
            public void onSuccess(Method method, User person) {
                if (personsTable.getRowCount() == 0) {
                    personsTable.setText(0, 0, "ID");
                    personsTable.setText(0, 1, "NAME");
                    personsTable.setText(0, 2, "DATE OF BIRTH");
                    personsTable.getRowFormatter().addStyleName(0, "tableHeader");
                }
                int rowNum = personsTable.getRowCount();
                personsTable.setText(rowNum, 0, String.valueOf(person.getName()));
                personsTable.setText(rowNum, 1, person.getName());
                personsTable.setText(rowNum, 2, String.valueOf(person.getPassword()));
            }

            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Error while loading persons! Cause: " + exception.getMessage());
            }
        });
    }
}