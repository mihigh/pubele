package org.syswin.fences.core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocationDetails implements Serializable {

    private String UID;
    private double   lat;
    private double   lng;
    private String state;
    private Date   lastModified;
    private String lastModifiedStr;

    public LocationDetails () {
    }

    public LocationDetails (String UID, double lat, double lng, LocationState state, Date lastModified) {
        this.UID = UID;
        this.lat = lat;
        this.lng = lng;
        this.state = state.toString ().toLowerCase ();
        this.lastModified = lastModified;
        this.lastModifiedStr = (new SimpleDateFormat ("hh:mm:ss dd-M-yyyy")).format(lastModified);
    }

    private void updateLastModified(){
        this.lastModified = new Date();
        this.lastModifiedStr = (new SimpleDateFormat ("hh:mm:ss dd-M-yyyy")).format(lastModified);
    }

    public String getUID () {
        return UID;
    }

    public void setUID (String UID) {
        this.UID = UID;
        updateLastModified();
    }

    public double getLat () {
        return lat;
    }

    public void setLat (double lat) {
        this.lat = lat;
        updateLastModified();
    }

    public double getLng () {
        return lng;
    }

    public void setLng (double lng) {
        this.lng = lng;
        updateLastModified();
    }

    public String getState () {
        return state;
    }

    public void setState (String state) {
        this.state = state;
        updateLastModified();
    }

    public Date getLastModified () {
        return lastModified;
    }

    public void setLastModified (Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedStr () {
        return lastModifiedStr;
    }

    public void setLastModifiedStr (String lastModifiedStr) {
        this.lastModifiedStr = lastModifiedStr;
    }
}