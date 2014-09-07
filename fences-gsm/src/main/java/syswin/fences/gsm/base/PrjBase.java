package syswin.fences.gsm.base;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import syswin.fences.gsm.gsm.GPRSUtilities;
import syswin.fences.gsm.logging.LoggingUtils;


import syswin.fences.gsm.base.utilities.Constants;
import syswin.fences.gsm.base.utilities.DBUtils;
import syswin.fences.gsm.base.utilities.OpratingSystem;
import syswin.fences.gsm.base.utilities.ServerUtils;
import syswin.fences.gsm.base.utilities.SystemExitCodes;

public class PrjBase {

    private final static Logger log = LoggerFactory.getLogger(PrjBase.class.getName());

    public PrjBase(){
        //runServer();
    }

    public static void runServer() {
        init();

        doCStartUpCheckUps();

        printStartUp();

        saveStartUpLog();

        try {
            /*GPRSSender.sendMessageTo ("Server1", "0722481227");
            GPRSSender.sendMessageTo ("Server2", "0722481227");
            GPRSSender.sendMessageTo ("Server3", "0722481227");
            GPRSSender.sendMessageTo ("Server4", "0722481227");*/
        }
        catch (Exception projectError) {
            System.err.println ("CRITICAL ERROR: " + projectError);
            System.err.println ("STACK TRACE: \n");
            projectError.printStackTrace ();
        }
    }

    /**
     * Initialize the server.
     */
    private static void init() {
        // The Logging Initialization
        if (!LoggingUtils.init()) {
            System.err.println("Error while initializing the logging.");
            System.exit(SystemExitCodes.INIT_LOGGING.value());
        }

        // The Server Initialization
        if (!ServerUtils.init()) {
            System.err.println("Error while initializing the server.");
            System.exit(SystemExitCodes.SERVER_INIT.value());
        }

        // The DB Initialization
        if (!DBUtils.init()) {
            System.err.println("Error while initializing the DB.");
            System.exit(SystemExitCodes.DB_INIT.value());
        }

        // The GSM GPRS Initialization
        if (!GPRSUtilities.init ()) {
            System.err.println ("Error while initializing the GSM GPRS Modem.");
            System.exit (SystemExitCodes.GPRS_INIT.value ());
        }
    }

    /**
     * Does the start up checks
     */
    private static void doCStartUpCheckUps() {
        if (OpratingSystem.NONE.equals(Constants.OS)) {
            System.err.println("Unknown Operating System. Closing application.");
            log.error("Unknown Operating System. Closing application.");
            System.exit(SystemExitCodes.UNKNOWN_OS.value());
        }

        String projectHomeFolder = System.getProperty (Constants.PROJECT_HOME_FOLDER);
        if (projectHomeFolder == null || projectHomeFolder.isEmpty ()) {
            System.err.println ("Unknown Project Path. Closing application.");
            log.error ("Unknown Project Path. Closing application.");
            System.exit (SystemExitCodes.UNKNOWN_PROJECT_PATH.value ());
        }

        String dbDir = System.getProperty(Constants.DB_SYSTEM_PROPERTY);
        if (dbDir == null || dbDir.isEmpty()) {
            System.err.println("Unknown DB Folder. Closing application.");
            log.error("Unknown DB Folder. Closing application.");
            System.exit(SystemExitCodes.UNKNOWN_DB_FOLDER.value());
        }
    }

    /**
     * Saves the System Properties of the current machine.
     */
    private static void saveStartUpLog() {
        try {
            File startUpLogFolder = new File(Constants.DEBUG_FOLDER_NAME);
            File startUpLogFile = new File(startUpLogFolder + "/" + Constants.DEBUG_FILE_NAME);
            FileUtils.forceMkdir(startUpLogFolder);
            PrintStream ps = new PrintStream(startUpLogFile);

            System.getProperties().list(ps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the 1st steps of the start up.
     */
    private static void printStartUp() {
        System.out.println("Starting...");
        System.out.println("Project: " + Constants.PROJECT_NAME);
        System.out.println("Project version: " + Constants.PROJECT_VERSION);
        System.out.println("Operating system: " + Constants.OS);
        System.out.println("Project Path: " + System.getProperty(Constants.PROJECT_HOME_FOLDER));
        System.out.println("Project DataBase Path: " + System.getProperty(Constants.DB_SYSTEM_PROPERTY));

        System.out.println("\n-----------------------------------");
        System.out.println("|      STARTING APPLICATION       |");
        System.out.println("-----------------------------------\n");
    }
}
