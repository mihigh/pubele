package org.syswin.fences.webapp.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.syswin.fences.core.UserInfoCreation;
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
    public Response getAllUsers () throws IOException {
        return Response.status (Response.Status.OK).entity (userServices.getAllUserInfo ()).build ();
    }

    @POST
    @Path(MAPPING_VERSION)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser (UserInfoCreation userInfo, @Context HttpServletRequest request) {
        if(userInfo == null ||
           userInfo.getUsername () == null       || userInfo.getUsername ().isEmpty () ||
           userInfo.getFirstName () == null      || userInfo.getFirstName ().isEmpty () ||
           userInfo.getLastName () == null       || userInfo.getLastName ().isEmpty () ||
           userInfo.getPassword () == null       || userInfo.getPassword ().isEmpty () ||
           userInfo.getPermissionName () == null || userInfo.getPermissionName ().isEmpty ()
           ){
            return Response.status (Response.Status.BAD_REQUEST).build ();
        }

        System.out.println (userInfo.toString ());

        return Response.status (Response.Status.OK).build ();
    }

    @PUT
    @Path(MAPPING_VERSION)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser (UserInfoCreation userInfo, @Context HttpServletRequest request) {
        if(userInfo == null ||
           userInfo.getUsername () == null       || userInfo.getUsername ().isEmpty () ||
           userInfo.getFirstName () == null      || userInfo.getFirstName ().isEmpty () ||
           userInfo.getLastName () == null       || userInfo.getLastName ().isEmpty () ||
           userInfo.getPassword () == null       || userInfo.getPassword ().isEmpty () ||
           userInfo.getPermissionName () == null || userInfo.getPermissionName ().isEmpty ()
           ){
            return Response.status (Response.Status.BAD_REQUEST).build ();
        }

        System.out.println (userInfo.toString ());

        return Response.status (Response.Status.OK).build ();
    }
}
