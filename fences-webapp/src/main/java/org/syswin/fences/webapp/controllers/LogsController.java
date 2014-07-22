package org.syswin.fences.webapp.controllers;

import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
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

    public LogsController () {
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

    @GET
    @Path(MAPPING_VERSION)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogs () {
        List<MyLog> logsList = new ArrayList<> ();
        logsList.add (new MyLog (1, new Date (), "Message 1", "Info"));
        logsList.add (new MyLog (2, new Date (), "Message 2", "Info"));
        logsList.add (new MyLog (3, new Date (), "Message 3", "Warning"));
        logsList.add (new MyLog (4, new Date (), "Message 4", "Warning"));
        logsList.add (new MyLog (5, new Date (), "Message 5", "Warning"));
        logsList.add (new MyLog (6, new Date (), "Message 6", "Error"));
        logsList.add (new MyLog (7, new Date (), "Message 7", "Error"));

        return Response.status (Response.Status.OK).entity (logsList).build ();
    }
}