package syswin.fences.gsm.base;

import org.syswin.fences.core.LocationDetails;

import java.util.ArrayList;
import java.util.List;

public class MessagesList {

    public final static List<LocationDetails> locationsList = new ArrayList<> ();

    public static void addLocation(LocationDetails location){
        locationsList.add(location);
    }
}
