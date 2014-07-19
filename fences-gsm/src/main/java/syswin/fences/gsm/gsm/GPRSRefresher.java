package syswin.fences.gsm.gsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class's scope is to refresh the GPRS SMS List.
 *
 * This Class will only be instanced once and will run continuously.
 */
public final class GPRSRefresher extends Thread{

    final static Logger log = LoggerFactory.getLogger (GPRSRefresher.class.getName ());

    private static final GPRSRefresher refreshThread = new GPRSRefresher ();
    private static boolean initDone = false;

    private final static int SECOND = 1000;
    final static int REFRESH_TIME = 10 * SECOND;

    private GPRSRefresher () {}

    /**
     * Inits the Refresh Thread and will return TRUE in case all went well or FALSE otherwise.
     * @return
     */
    synchronized public static boolean init () {
        if(!initDone){
            try {
                refreshThread.start ();
            }catch (Exception e){
                log.error ("An error occurred while starting the refresh thread.", e);
                initDone = false;
                return initDone;
            }

            initDone = true;
            log.info ("The Refresh Thread started.");
        }

        return initDone;
    }

    public void run () {
        while (true) {
            try {
                Thread.sleep (REFRESH_TIME);

                Message refreshMessage = new Message (Message.MessageDirection.REFRESH);
                refreshMessage.setMessage(GPRSCommands.DELETE_READ_SENT.toString ());

                GPRSSender.addMessage (refreshMessage);
            }
            catch (InterruptedException e) {
                log.error ("The GPRS Refresh Thread was interrupted.");
            }
        }
    }
}

