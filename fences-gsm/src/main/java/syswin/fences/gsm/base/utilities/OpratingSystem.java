package syswin.fences.gsm.base.utilities;

/**
 * @author Alexandru Cozma
 *
 *         Contains the enumeration of operation systems supported by this application.
 */
public enum OpratingSystem {
    LINUX,
    WINDOWS,
    MAC,
    NONE,
    ;

    private OpratingSystem () {}

    /**
     * Returns the Operating System of the current machine or NONE if it can't be determined.
     * 
     * @return OpratingSystem
     */
    public static OpratingSystem getOS () {
        String osStr = System.getProperty ("os.name");

        for (OpratingSystem os : OpratingSystem.values ()) {
            if (osStr.toLowerCase ().startsWith (os.name ().toLowerCase ())) {
                return os;
            }
        }

        return NONE;
    }

    /**
     * Returns a String of the current Operating System.
     */
    public String toString () {
        return this.name ();
    }
}
