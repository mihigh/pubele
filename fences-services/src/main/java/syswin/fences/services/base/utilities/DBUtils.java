package syswin.fences.services.base.utilities;

import java.io.File;


public class DBUtils {

    public final static String DB_FOLDER_NAME = Constants.DB_FOLDER_NAME;
    private final static String PROJECT_HOME_FOLDER = Constants.PROJECT_HOME_FOLDER;
    public final static String DB_SYSTEM_PROPERTY = Constants.DB_SYSTEM_PROPERTY;

    public static boolean init() {

        // Check that the project home folder has been set
        String projectHomeFolter = System.getProperty(PROJECT_HOME_FOLDER);
        if (projectHomeFolter == null || projectHomeFolter.isEmpty()) {
            return false;
        }

        // Set the database folder
        String dbDirPath = projectHomeFolter + "/" + DB_FOLDER_NAME;
        System.setProperty(DB_SYSTEM_PROPERTY, dbDirPath);

        // Check that it was set
        dbDirPath = System.getProperty(DB_SYSTEM_PROPERTY);
        if (dbDirPath == null || dbDirPath.isEmpty()) {
            return false;
        }

        File dbDir = new File(dbDirPath);
        if (!dbDir.exists() && !dbDir.mkdirs() && !dbDir.exists()) {
            return false;
        }

        return true;
    }
}
