package org.syswin.fences.webapp.controllers;

import org.slf4j.LoggerFactory;
import org.syswin.fences.core.LocationDetails;
import org.syswin.fences.core.LocationState;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path(MapLocationsController.MAPPING)
public class MapLocationsController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger (MapLocationsController.class);

    public static final String MAPPING            = "/map";
    public static final String PERMISSIONS_HEADER = "/locations";

    private final static List<LocationDetails> locationsList = new ArrayList<> ();

    public MapLocationsController () {
    }

    @GET
    @Path(PERMISSIONS_HEADER)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocations () {
        if(locationsList.isEmpty ()){
            locationsList.add (new LocationDetails ("UID123456789", 44.424676000000005, 26.082204000000047 , LocationState.STABLE, new Date ()));
        }
        return Response.status (Response.Status.OK).entity (locationsList).build ();
    }
}

