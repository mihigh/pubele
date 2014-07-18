package org.syswin.fences.client.remote;

import javax.ws.rs.GET;

import com.google.gwt.core.client.GWT;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;
import org.syswin.fences.core.User;

public interface PersonResourceAsync extends RestService {

    @GET
    void getPersons(MethodCallback<User> callback);

    /**
     * Utility class to get the instance of the Rest Service
     */
    public static final class Util {

        private static PersonResourceAsync instance;

        public static final PersonResourceAsync get() {
            if (instance == null) {
                instance = GWT.create(PersonResourceAsync.class);
                ((RestServiceProxy) instance).setResource(new Resource("api/v1/user"));
            }
            return instance;
        }

        private Util() {
            // Utility class should not be instantiated
        }
    }

}
