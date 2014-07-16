package org.syswin.fences.webapp.controllers;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.syswin.fences.core.User;

@Path(UserController.MAPPING)
public class UserController {

    public static final String MAPPING = "/user";
    public static final String MAPPING_VERSION = "/";

    public UserController() {
    }

    @GET
    @Path(MAPPING_VERSION)
    @Produces(MediaType.APPLICATION_JSON)
    public User getVersionInfo() throws IOException {

        //TODO: remove constants
        return new User("MihaiC", "qscesz");
    }

}
