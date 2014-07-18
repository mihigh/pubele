package org.syswin.fences.client.session;

public class Session {

    public static final String SESSION_ID = "SESSION_ID";
    private SessionDetails sessionDetails;

    public static final Session instance = new Session();

    private Session() {
        //TODO: deserialize userdata from http session
    }

    public SessionDetails getSessionDetails() {
        return sessionDetails;
    }

    public void setSessionDetails(SessionDetails sessionDetails) {
        this.sessionDetails = sessionDetails;
        //TODO: serialize userdata into http session
    }
}
