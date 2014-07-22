package org.syswin.fences.webapp.controllers;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path(LogsController.MAPPING)
public class LogsController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger (LogsController.class);

    public static final String MAPPING         = "/logs";
    public static final String MAPPING_VERSION = "/";

    private final static String NO_ELEMENTS_PARAM = "noElements";
    private final static String PAGE_PARAM = "page";

    /**
     * Test Block
     */

    private final static List<MyLog> logsList = new ArrayList<> ();
    static{
        for(int i=0 ; i<1000 ; i+=3){
            logsList.add (new MyLog (i, new Date (), "Info Message " + i, "Info"));
            logsList.add (new MyLog (i+1, new Date (), "Warning Message " + (i+1), "Warning"));
            logsList.add (new MyLog (i+2, new Date (), "Error Message " + (i+2), "Error"));
        }
    }

    /* END OF TEST BLOCK*/

    public LogsController () {
    }

    @GET
    @Path(MAPPING_VERSION)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLogs (@Context HttpServletRequest request) {

        String noElementsStr = request.getParameter (NO_ELEMENTS_PARAM);
        String pageStr = request.getParameter (PAGE_PARAM);

        int noElements = -1;
        int page = 1;

        try{
            noElements = Integer.parseInt (noElementsStr);
            page = Integer.parseInt (pageStr);
        }catch (Exception e){
            page = 1;
        }

        if(noElements == -1){
            return Response.status (Response.Status.OK).entity (logsList).build ();
        }

        int startPosition = (page-1)*noElements;
        int stopPosition = page*noElements;

        if(startPosition > logsList.size ()){
            return Response.status (Response.Status.OK).entity (new ArrayList()).build ();
        }

        if(stopPosition > logsList.size ()){
            stopPosition = logsList.size ();
        }

        return Response.status (Response.Status.OK).entity (logsList.subList (startPosition, stopPosition)).build ();
    }
}

class MyLog implements Serializable {
    private int    id;
    private Date   date;
    private String message;
    private String severity;

    public MyLog () {}

    public MyLog (int id, Date date, String message, String severity) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.severity = severity;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }

    public String getSeverity () {
        return severity;
    }

    public void setSeverity (String severity) {
        this.severity = severity;
    }
}