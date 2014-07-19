package org.syswin.fences.webapp.controllers;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(LoginController.MAPPING)
public class LoginController {

    public static final String MAPPING = "/login";
    public static final String MAPPING_VERSION = "/";

    public LoginController() {
    }

    @GET
    @Path(MAPPING_VERSION)
    @Produces(MediaType.APPLICATION_JSON)
    public String getVersionInfo() throws IOException {
        return "v1.0";
    }

}
