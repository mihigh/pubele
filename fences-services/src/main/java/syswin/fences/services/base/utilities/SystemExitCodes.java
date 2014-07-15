package syswin.fences.services.base.utilities;

/**
 * @author Alexandru Cozma
 * 
 *         Contains an enumeration of all the possible failing error.s *
 */
public enum SystemExitCodes {
    UNKNOWN_OS (-100),
    UNKNOWN_PROJECT_PATH (-110),
    UNKNOWN_DB_FOLDER (-120),
    
    INIT_LOGGING (-200),
    SERVER_INIT (-210),
    DB_INIT (-220),
    GPRS_INIT (-230),
    
    ;

    // The error code
    private int errorCode;

    private SystemExitCodes () {}

    private SystemExitCodes (int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Returns the associated error code.
     * 
     * @return Integer
     */
    public int value () {
        return this.errorCode;
    }
}