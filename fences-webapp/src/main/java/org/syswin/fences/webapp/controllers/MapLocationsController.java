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
            locationsList.add (new LocationDetails ("D-0000-0000-0001", 44.424676, 26.082204 , LocationState.STABLE, new Date ()));
            locationsList.add (new LocationDetails ("D-0000-0000-0002", 44.454740, 26.063001, LocationState.ALARM, new Date ()));
            locationsList.add (new LocationDetails ("V-0000-0000-0001", 44.434518, 26.038797 , LocationState.VEHICLE, new Date ()));
        }

        locationsList.get (2).setLat ( locationsList.get (2).getLat () + 0.0001);

        return Response.status (Response.Status.OK).entity (locationsList).build ();
    }
}

