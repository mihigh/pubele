package syswin.fences.services.base.utilities;

/**
 * @author Alexandru Cozma
 * 
 *         A class with constants used inside the program.
 */
public class Constants {

    public final static OpratingSystem OS                       = OpratingSystem.getOS ();

    public final static String         PROJECT_NAME             = "Fences Alarms";
    public final static String         PROJECT_VERSION          = "1.0.0.3";
    public final static String         PROJECT_HOME_FOLDER      = "project.system.home";

    public final static String         DEBUG_FOLDER_NAME        = "debug";
    public final static String         DEBUG_FILE_NAME          = "SystemProperties.txt";

    public final static String         DB_FOLDER_NAME           = "db";
    public final static String         DB_SYSTEM_PROPERTY       = "derby.system.home";

    public final static String         LOG_FOLDER_NAME          = "logging";
    public final static String         STANDARD_LOG_CONFIG_FILE = "src/log4j2.xml";

    private Constants () {}
}
