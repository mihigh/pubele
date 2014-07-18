package org.syswin.fences.client.session;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Cookies;
import com.kfuntak.gwt.json.serialization.client.Serializer;

public class Session {

    public static final String SESSION_ID = "SESSION_ID";
    public static final String CLASS_NAME = "org.syswin.fences.client.session.SessionDetails";

    Serializer serializer = GWT.create(Serializer.class);
    public static final Session instance = new Session();

    private Session() {
    }

    public SessionDetails getSessionDetails() {
        String jsonText = Cookies.getCookie(SESSION_ID);

        try {
            return (SessionDetails)serializer.deSerialize(jsonText, CLASS_NAME);
        } catch (Throwable e) {
            return null;
        }
    }

    public void setSessionDetails(SessionDetails sessionDetails) {
        JSONValue jsonValue = serializer.serializeToJson(sessionDetails);
        Cookies.setCookie(SESSION_ID, jsonValue.toString());
    }
}
