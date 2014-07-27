package org.syswin.fences.webapp.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.syswin.fences.services.user.UserServices;

@Path(UsersController.MAPPING)
public class UsersController {

    public static final String MAPPING = "/users";
    public static final String MAPPING_VERSION = "/";

    public UsersController () {
    }

    @Inject
    UserServices userServices;

    @GET
    @Path(MAPPING_VERSION)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVersionInfo () throws IOException {
        return Response.status (Response.Status.OK).entity (userServices.getAllUserInfo ()).build ();
    }

}
