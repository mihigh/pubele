package org.syswin.fences.webapp.controllers;

import org.slf4j.LoggerFactory;
import org.syswin.fences.models.PermissionRecord;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
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
        List<PermissionRecord> permissionsList = new ArrayList<> ();

        permissionsList.add (PermissionRecord.createAdminGroup());
        permissionsList.add (new PermissionRecord("user1", null, false, new Date(), new Date(), null, true, true, true, true, true, true, true, true, true, true, false, true));
        permissionsList.add (new PermissionRecord("user2", null, false, new Date (), new Date(), null, true, true, true, true, true, true, true, true, true, false, false, false));

        return Response.status (Response.Status.OK).entity (permissionsList).build ();
    }

    @POST
    @Path(PERMISSIONS_HEADER)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPermission (PermissionRecord permission, @Context HttpServletRequest request) {
        if(permission == null || permission.getName () == null || permission.getName ().isEmpty ()){
            return Response.status (Response.Status.BAD_REQUEST).build ();
        }

        System.out.println (permission.toString ());

        return Response.status (Response.Status.OK).build ();
    }

    @PUT
    @Path(PERMISSIONS_HEADER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePermission (PermissionRecord permission, @Context HttpServletRequest request) {
        if(permission == null || permission.getName () == null || permission.getName ().isEmpty ()){
            return Response.status (Response.Status.BAD_REQUEST).build ();
        }

        return Response.status (Response.Status.OK).build ();
    }
}