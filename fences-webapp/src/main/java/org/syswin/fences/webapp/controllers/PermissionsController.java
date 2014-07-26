package org.syswin.fences.webapp.controllers;

import org.slf4j.LoggerFactory;
import org.syswin.fences.models.Permission;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path(PermissionsController.MAPPING)
public class PermissionsController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger (PermissionsController.class);

    public static final String MAPPING         = "/users";
    public static final String PERMISSIONS_HEADER = "/permissions";

    public PermissionsController () {
    }

    @GET
    @Path(PERMISSIONS_HEADER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPermissionsHeader () {
        List<Permission> permissionsList = new ArrayList<> ();

        permissionsList.add (Permission.createAdminGroup ());
        permissionsList.add (new Permission("user1", null, false, new Date(), new Date(), null, true, true, true, true, true, true, true, true, true, true, false, true));
        permissionsList.add (new Permission("user2", null, false, new Date (), new Date(), null, true, true, true, true, true, true, true, true, true, false, false, false));

        return Response.status (Response.Status.OK).entity (permissionsList).build ();
    }
}