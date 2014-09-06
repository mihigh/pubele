package org.syswin.fences.core;

import java.io.Serializable;
import java.util.Date;

public class LocationDetails implements Serializable {

    private String UID;
    private double   lat;
    private double   lng;
    private String state;
    private Date   lastModified;

    public LocationDetails () {
    }

    public LocationDetails (String UID, double lat, double lng, LocationState state, Date lastModified) {
        this.UID = UID;
        this.lat = lat;
        this.lng = lng;
        this.state = state.toString ().toLowerCase ();
        this.lastModified = lastModified;
    }

    public String getUID () {
        return UID;
    }

    public void setUID (String UID) {
        this.UID = UID;
    }

    public double getLat () {
        return lat;
    }

    public void setLat (double lat) {
        this.lat = lat;
    }

    public double getLng () {
        return lng;
    }

    public void setLng (double lng) {
        this.lng = lng;
    }

    public String getState () {
        return state;
    }

    public void setState (String state) {
        this.state = state;
    }

    public Date getLastModified () {
        return lastModified;
    }

    public void setLastModified (Date lastModified) {
        this.lastModified = lastModified;
    }
}