package syswin.fences.services.base.utilities;

import java.io.File;

public class ServerUtils {

    private final static String PROJECT_HOME_FOLDER = Constants.PROJECT_HOME_FOLDER;

    public static boolean init () {
        String projectHomeFolter = System.getProperty (PROJECT_HOME_FOLDER);

        if (projectHomeFolter == null || projectHomeFolter.isEmpty ()) {
            File localPath = new File ("");
            System.setProperty (PROJECT_HOME_FOLDER, localPath.getAbsolutePath ());
        }

        projectHomeFolter = System.getProperty (PROJECT_HOME_FOLDER);
        if (projectHomeFolter == null || projectHomeFolter.isEmpty ()) {
            return false;
        }

        return true;
    }
}
