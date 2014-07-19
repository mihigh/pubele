package syswin.fences.gsm.gsm;

import jssc.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import syswin.fences.gsm.base.utilities.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GPRSUtilities extends Thread {

    private final static Logger log = LoggerFactory.getLogger (GPRSUtilities.class.getName ());

    private final static String GPRS_PROPERTIES = Constants.GPRS_PROPERTIES;

    // The default values used for the GPRS Serial RS232 Communication
    private final static String DEFAULT_LINUX_TTY = "/dev/ttyUSB0";
    private final static int    DEFAULT_BAUD_RATE = 115200;
    private final static int    DEFAULT_DATA_BITS = 8;
    private final static int    DEFAULT_STOP_BITS = 1;
    private final static int    DEFAULT_PARITY    = 0;

    // The actual values used for the GPRS Serial RS232 Communication
    private static String linuxTTY = null;
    private static int    baudRate = 0;
    private static int    dataBits = 0;
    private static int    stopBits = 0;
    private static int    parity   = 0;

    // The keys that are used for the properties GPRS file
    private static final String LINUX_TTY_KEY = "linuxTTYKey";
    private static final String BAUD_RATE_KEY = "baudRateKey";
    private static final String DATA_BITS_KEY = "dataBitsKey";
    private static final String STOP_BITS_KEY = "stopBitsKey";
    private static final String PARITY_KEY    = "parityKey";

    // The actual RS232 Serial Port communicating with the GPRS Modem
    private static SerialPort serialPort = null;
    private static final Object SERIAL_PORT_LOCK = new Object();

    // Listener lock, we don't listen to anythig if we are writing to the Serial COM
    private static final Object SERIAL_LISTEN_LOCK = new Object();
    private static String SERIAL_LISTEN_IGNORE = "";

    private final static int SECOND = 1000;
    private final static int INIT_SLEEP_TIME = 5 * SECOND;

    private final static int ECHO_WAIT_TIME = 2000;
    private final static int RX_READ_TIMEOUT = 2000;

    synchronized public static boolean init (){

        String projectHomeFolder = System.getProperty (Constants.PROJECT_HOME_FOLDER);
        if (projectHomeFolder == null || projectHomeFolder.isEmpty ()) {
            return false;
        }

        File gprsConfigFile = new File (projectHomeFolder + "/" + GPRS_PROPERTIES);

        if (!gprsConfigFile.exists () ){
            log.info ("There is no GPRS Properties file located at {}.", gprsConfigFile);

            linuxTTY = DEFAULT_LINUX_TTY;
            baudRate = DEFAULT_BAUD_RATE;
            dataBits = DEFAULT_DATA_BITS;
            stopBits = DEFAULT_STOP_BITS;
            parity   = DEFAULT_PARITY;
        }
        else {
            log.info ("Fond a GPRS Properties file located at {}.", gprsConfigFile);

            Properties gprsProperties = new Properties ();

            try (InputStream is = new FileInputStream (gprsConfigFile);) {
                gprsProperties.load (is);
            }
            catch (IOException ex) {
                log.error ("Error while loading the GPRS Properties file {}.", gprsProperties, ex);
            }

            linuxTTY = gprsProperties.getProperty (LINUX_TTY_KEY, DEFAULT_LINUX_TTY);
            baudRate = Integer.parseInt (gprsProperties.getProperty (BAUD_RATE_KEY, String.valueOf (DEFAULT_BAUD_RATE)));
            dataBits = Integer.parseInt (gprsProperties.getProperty (DATA_BITS_KEY, String.valueOf (DEFAULT_DATA_BITS)));
            stopBits = Integer.parseInt (gprsProperties.getProperty (STOP_BITS_KEY, String.valueOf (DEFAULT_STOP_BITS)));
            parity   = Integer.parseInt (gprsProperties.getProperty (PARITY_KEY, String.valueOf (DEFAULT_PARITY)));
        }

        log.info ("GPRS RS232 Init Starting on {} with: Port: {} , BaudRate: {} , DataBits: {} , StopBits: {} , Parity: {} ",
                Constants.OS, linuxTTY, baudRate, dataBits, stopBits, parity);

        if(!initGPRS() || !initEventGPRSListener()){
            return false;
        }

        if(!GPRSRefresher.init ()){
            return false;
        }

        return true;
    }

    private static boolean initGPRS() {

        // Only one operation allowed on the serialPort.
        synchronized (SERIAL_PORT_LOCK) {
            serialPort = new SerialPort (linuxTTY);

            try {
                boolean openStatus = serialPort.openPort ();
                log.info ("Serial RS232 Port ({}) opened [{}].", linuxTTY, openStatus ? "SUCCESS" : "FAIL");
                if( !openStatus){
                    return false;
                }
                Thread.sleep(INIT_SLEEP_TIME);

                log.info ("Setting RS232 Port Rates executed [{}].", serialPort.setParams (baudRate, dataBits, stopBits, parity) ? "SUCCESS" : "FAIL");
                Thread.sleep(INIT_SLEEP_TIME);

                int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;
                log.info ("Setting RS232 Port Rates executed [{}].", serialPort.setEventsMask (mask) ? "SUCCESS" : "FAIL");
                //System.out.println ("Setting RS232 Port MASK executed " + (serialPort.setEventsMask (mask) ? "SUCCESS" : "FAIL"));
                Thread.sleep(INIT_SLEEP_TIME);

                serialPort.writeBytes((GPRSCommands.CHECK_ALL_OKEY.toString ()).getBytes());
                Thread.sleep(INIT_SLEEP_TIME);
                String replyCheck = serialPort.readString ();
                if(!replyCheck.contains (GPRSCommands.AT.toString ())){
                    return false;
                }
            }
            catch (SerialPortException ex) {
                log.error ("Failed to open the port.");
                return false;
            }
            catch (InterruptedException e) {
                log.error ("The thread was woke up from the sleep.");
                return false;
            }
        }

        log.info ("The RS232 GPRS Modem initialized successfully.");
        System.out.println ("The RS232 GPRS Modem initialized successfully.");
        return true;
    }

    private static boolean initEventGPRSListener(){
        SerialPortReader gprsListener = new SerialPortReader ();
        gprsListener.start ();

        try {
            serialPort.addEventListener(gprsListener);
        }
        catch (SerialPortException e) {
            log.error ("Failed to add the GPRS Listener.");
            return false;
        }

        return true;
    }

    public static String getGPRSLinuxTTY(){
        return linuxTTY;
    }

    public static String getGPRSLinuxTDefaultTY(){
        return DEFAULT_LINUX_TTY;
    }

    public static int getGPRSDataBits(){
        return dataBits;
    }

    public static int getGPRSDefaultDataBits(){
        return DEFAULT_DATA_BITS;
    }

    public static int getGPRSBaudRate(){
        return baudRate;
    }

    public static int getGPRSDefaultBaudRate(){
        return DEFAULT_BAUD_RATE;
    }

    public static int getGPRSStopBits(){
        return stopBits;
    }

    public static int getGPRSDefaultStopBits(){
        return DEFAULT_STOP_BITS;
    }

    public static int getGPRSParity(){
        return parity;
    }

    public static int getGPRSDefaultParity(){
        return DEFAULT_PARITY;
    }

    synchronized public static void sendCommandToGPRS(Message msg){
        if(msg == null){
            log.error ("Invalid message command.");
        }

        if(serialPort == null || !serialPort.isOpened ()){
            log.error ("Invalid serial port.");
            return;
        }

        synchronized (SERIAL_LISTEN_LOCK) {
            StringBuilder toBeIgnored = new StringBuilder ();
            try {
                switch (msg.getDirection ()) {

                    // OUTGOING MESSAGES
                    case OUTGOING:
                        switch (msg.getType ()) {
                            case SEND_MESSAGE_TO:
                                log.info ("Sending message \"{}\" to \"{}\"", msg.getMessage (), msg.getDestination ());

                                // Send the phone number
                                serialPort.writeBytes ((GPRSCommands.SEND_MESSAGE.toString () + "\"" + msg.getDestination () + "\"\r\n").getBytes ());
                                serialPort.readString ((GPRSCommands.SEND_MESSAGE.toString () + "\"" + msg.getDestination () + "\"\r\n").getBytes ().length, ECHO_WAIT_TIME);
                                //System.out.println ("1\'" + serialPort.readString ((GPRSCommands.SEND_MESSAGE.toString () + "\"" + msg.getDestination () + "\"\r\n").getBytes ().length, ECHO_WAIT_TIME) + "\'");

                                // Send the message
                                serialPort.writeBytes ((msg.getMessage () + "\r\n").getBytes ());
                                serialPort.readString (("> " + msg.getMessage () + "\r\n").getBytes ().length, ECHO_WAIT_TIME);
                                //System.out.println ("2\'" + serialPort.readString (("> " + msg.getMessage () + "\r\n").getBytes ().length, ECHO_WAIT_TIME) + "\'");

                                // Send the CTRL+Z to end the message
                                serialPort.writeBytes (GPRSCommands.CTRL_Z.toString ().getBytes ());
                                serialPort.readString (("> \r\n").getBytes ().length, ECHO_WAIT_TIME);
                                //System.out.println ("3\'" + serialPort.readString (("> \r\n").getBytes ().length, ECHO_WAIT_TIME) + "\'");

                                serialPort.readString (("> \r\n").getBytes ().length);
                                //System.out.println ("4\'" + serialPort.readString (("> \r\n").getBytes ().length) + "\'");
                                break;

                            case READ_REQUEST:
                                log.info ("Sending read message request to GPRS Modem: {}", msg.getMessage ());

                                serialPort.writeBytes ((msg.getMessage () + "\r\n").getBytes ());
                                //toBeIgnored.append (msg.getMessage () + "\r\n");
                                serialPort.readBytes ((msg.getMessage () + "\r\n").getBytes ().length, ECHO_WAIT_TIME);

                                break;

                            case DELETE_SELECTED:
                                serialPort.writeBytes ((msg.getMessage () + "\r\n").getBytes ());
                                serialPort.readBytes ((msg.getMessage () + "\r\n").getBytes ().length, ECHO_WAIT_TIME);
                                serialPort.readBytes ((GPRSCommands.AT.toString () + "\r\n").getBytes ().length, ECHO_WAIT_TIME);

                                GPRSReceiver.unlockReadIncomingMessages ();
                                break;

                            default:
                                break;
                        }

                        SERIAL_LISTEN_IGNORE = SERIAL_LISTEN_IGNORE + toBeIgnored.toString ().replaceAll ("\n", "").replaceAll ("\r", "").replaceAll (" ", "");
                        break;
                        // OUTGOING MESSAGES -- END

                    // REFRESH MESSAGE
                    case REFRESH:
                        //System.out.println ("REFRESH: " + msg.getMessage ());
                        break;
                        // REFRESH MESSAGE -- END

                    default:
                        break;

                }
            }
            catch (SerialPortException e) {
                log.error ("Failed to send command to GPRS Modem.", e);
            }
            catch (SerialPortTimeoutException e) {
                e.printStackTrace ();
            }
        }

        try {
            Thread.sleep (2000);
        }
        catch (InterruptedException e) {
            log.error ("Error occurred while waiting for message to be sent.");
        }
    }

    /**
     * This Thread class is the one responsible for listening to the RS232 Serial Communication.
     */
    private static final class SerialPortReader extends Thread implements SerialPortEventListener {
        public void run () {
            log.info ("The GPRS Listener started.");
            while(true){}
        }

        synchronized public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR()) {

                synchronized (SERIAL_LISTEN_LOCK) {

                    try {
                        Thread.sleep (50);
                        byte[] buffer = serialPort.readBytes (event.getEventValue (), RX_READ_TIMEOUT);
                        String origMessage = new String (buffer);

                        GPRSReceiver.processNewMessager (origMessage);
                    }
                    catch (SerialPortException ex) {
                        log.error ("A serial port exception occurred.", ex);
                    }
                    catch (InterruptedException e) {
                        log.error ("A interruption occurred on Listener.", e);
                    }
                    catch (SerialPortTimeoutException e) {
                        e.printStackTrace ();
                    }
                }
            }
            else if (event.isCTS()) {// If CTS line has changed state
                if (event.getEventValue() == 1) {// If line is ON
                    System.out.println("CTS - ON");
                } else {
                    System.out.println("CTS - OFF");
                }
            }
            else if (event.isDSR()) {// /If DSR line has changed state
                if (event.getEventValue() == 1) {// If line is ON
                    System.out.println("DSR - ON");
                } else {
                    System.out.println("DSR - OFF");
                }
            }
        }

    }
}