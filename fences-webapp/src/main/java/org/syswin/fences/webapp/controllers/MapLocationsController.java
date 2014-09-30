package org.syswin.fences.webapp.controllers;

import org.slf4j.LoggerFactory;
import org.syswin.fences.core.LocationDetails;
import org.syswin.fences.core.LocationState;
import syswin.fences.gsm.base.MessagesList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path(MapLocationsController.MAPPING)
public class MapLocationsController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger (MapLocationsController.class);

    public static final String MAPPING            = "/map";
    public static final String PERMISSIONS_HEADER = "/locations";

    public MapLocationsController () {
    }

    @GET
    @Path(PERMISSIONS_HEADER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocations () {

        if(MessagesList.locationsList.isEmpty ()) {
            MessagesList.addLocation (new LocationDetails ("1234", 12.11, 11.13, LocationState.STABLE, new Date ()));
        }

        return Response.status (Response.Status.OK).entity (MessagesList.locationsList).build ();
    }

}

