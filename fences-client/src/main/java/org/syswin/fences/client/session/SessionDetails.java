package org.syswin.fences.client.session;

import com.kfuntak.gwt.json.serialization.client.JsonSerializable;

public class SessionDetails implements JsonSerializable {

    private  String username;
    private String password;

    public SessionDetails() {
    }

    public SessionDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    };

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


