package syswin.fences.gsm.logging;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import syswin.fences.gsm.base.utilities.Constants;


/**
 * @author Alexandru Cozma
 *
 *         Contains the utilities for using the LOG4J.
 */
public class LoggingUtils {

    private final static String LOG_FOLDER_NAME          = Constants.LOG_FOLDER_NAME;
    private final static String STANDARD_LOG_CONFIG_FILE = Constants.STANDARD_LOG_CONFIG_FILE;

    public static boolean init () {
        File loggingFolder = new File (LOG_FOLDER_NAME);
        try {
            FileUtils.forceMkdir (loggingFolder);
        }
        catch (IOException e) {
            e.printStackTrace ();
            return false;
        }

        return true;

    }
}
