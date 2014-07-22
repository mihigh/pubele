package org.syswin.fences.webapp.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;
import org.syswin.fences.core.User;
import org.syswin.fences.services.user.UserServices;

@Path(LoginController.MAPPING)
public class LoginController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);


    public static final String MAPPING = "/login";
    public static final String MAPPING_VERSION = "/";

    @Inject
    UserServices userServices;


    public LoginController() {
    }

    @POST
    @Path(MAPPING_VERSION)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user, @Context HttpServletRequest request) {
        logger.debug("login -- username:{} password:{}", user.getName(), user.getPassword());

        User userDetails = userServices.getUserByUsername(user.getName());
        if (userDetails == null || !userDetails.getPassword().equals(user.getPassword())) {
            return Response.status(Response.Status.NOT_FOUND).build();

        }

        HttpSession session = request.getSession(true);
        Utils.setUser(session, userDetails);

        return Response.status(Response.Status.OK).build();
    }

}
