package syswin.fences.gsm.gsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public final class GPRSReceiver {

    private final static Logger log = LoggerFactory.getLogger (GPRSReceiver.class.getName ());

    final static LinkedBlockingQueue<Message> incomingMessagesQueue = new LinkedBlockingQueue<Message> ();

    private final static List<ReceiverWorker> WORKERS_LIST = new LinkedList<ReceiverWorker> ();

    private final static int NUMBER_OF_WORKERS = 5;

    private static final Object READ_INCOMING_MESSAGE_LOCK_OBJECT = new Object ();
    private static boolean READ_INCOMING_MESSAGE_LOCK = false;
    private static int INCOMING_MESSAGE_ID = -1;

    private static String incompleteMessage = "";


    private GPRSReceiver() {}

    static{
        int failCounter = 0;
        for(int id = 0; id < NUMBER_OF_WORKERS ; id++){
            ReceiverWorker newWorker = new ReceiverWorker(id);

            try {
                newWorker.start ();
            }catch (Exception e){
                log.error ("Failed to start Receiver Worker no. {}", id);
                failCounter++;
                continue;
            }

            WORKERS_LIST.add (newWorker);
        }

        log.info ("The number of Receiver Workers that started SUCCESSFULLY is {}. Number of start fails equals {}.", (NUMBER_OF_WORKERS - failCounter), failCounter);
    }

    /**
     * Given a String containing the message received from the GPRS Modem,
     * it creates a Message class instance and adds it to the imcomingMessagesQueue.
     * @param message - String
     */
    synchronized protected static void processNewMessager (String message){
        if(message == null && message.isEmpty ()){
            log.error ("Can not process and invalid message: {}", message);
            return;
        }

        log.info ("Processing message {}", message);
        message = message.replaceAll ("\n", "").replaceAll ("\r", "").replaceAll (" ", "");
        if(message.isEmpty ()){
            return;
        }

        String composedMessage = incompleteMessage + message;
        System.out.println ("message: " + message);
        System.out.println ("composedMessage: " + composedMessage);
        System.out.println ();
        Message newMessage = new Message (composedMessage, Message.MessageDirection.INCOMING);

        switch(newMessage.getType ()){
            case SENT_NOTIFICATION:
                System.out.println ("Received a send notification.");
                log.info ("Received a send notification.");
                break;

            case INCOMING_MESSAGE:
                synchronized (READ_INCOMING_MESSAGE_LOCK_OBJECT) {
                    if (!READ_INCOMING_MESSAGE_LOCK) {
                        System.out.println ("Received a new message with ID (inside GPRS Modem) " + newMessage.getMessageID () + ".");
                        log.info ("Received a new message with ID (inside GPRS Modem) {}.", newMessage.getMessageID ());

                        Message requestReadMsg = new Message (Message.MessageDirection.OUTGOING, Message.MessageType.READ_REQUEST);
                        requestReadMsg.setMessage (GPRSCommands.READ_REQUEST.toString () + newMessage.getMessageID ());

                        if (!lockReadIncomingMessages (newMessage.getMessageID ())) {
                            log.error ("Error locking the READ right on the incoming messages.");
                        }

                        GPRSSender.addMessage (requestReadMsg);
                    }
                }
                break;

            case INCOMPLETE:
                incompleteMessage = incompleteMessage + message;
                return;

            case PARENT_MESSAGE:
            case GRANDPARENT_MESSAGE:
                System.out.println ("Got Parent message: " + newMessage.getMessage ());
                //incomingMessagesQueue.add (newMessage);
                System.out.println ("-----------------------------------------------");
                System.out.println ();

                deleteLastReadMessage ();
                break;

            default:
                log.error ("Something not good...");
                break;
        }
        incompleteMessage = "";
    }

    /**
     * Locks the READ Right of any thread on a given message ID.
     * @param messageID - Integer - The last read message.
     * @return boolean - TRUE if the lock was a success or FALSE otherwise.
     */
    protected static boolean lockReadIncomingMessages(int messageID){
        synchronized (READ_INCOMING_MESSAGE_LOCK_OBJECT) {
            if (READ_INCOMING_MESSAGE_LOCK) {
                return false;
            }

            READ_INCOMING_MESSAGE_LOCK = true;
            INCOMING_MESSAGE_ID = messageID;

            return true;
        }
    }

    /**
     * Unlocks the READ Right of any thread. If this is a success, a new incoming message can be read.
     * @return boolean - TRUE if the lock was disabled or FALSE otherwise OR FALSE if the lock wasn't locked.
     */
    protected static boolean unlockReadIncomingMessages(){
        synchronized (READ_INCOMING_MESSAGE_LOCK_OBJECT) {
            if (!READ_INCOMING_MESSAGE_LOCK) {
                return false;
            }

            READ_INCOMING_MESSAGE_LOCK = false;
            INCOMING_MESSAGE_ID = -1;

            return true;
        }
    }

    /**
     * Returns the ID of the last read message.
     * @return Integer - The ID of the last read message.
     */
    protected static int getIncomingMessageLockedID(){
        synchronized (READ_INCOMING_MESSAGE_LOCK_OBJECT) {
            if (!READ_INCOMING_MESSAGE_LOCK) {
                return -1;
            }

            return INCOMING_MESSAGE_ID;
        }
    }

    /**
     * This will ask the GPRS Modem to delete the last read message from it's memory.
     */
    protected static void deleteLastReadMessage(){
        Message deleteRequestMsg = new Message (Message.MessageDirection.OUTGOING, Message.MessageType.DELETE_SELECTED);

        int messageID = getIncomingMessageLockedID ();
        if(messageID > 0) {
            deleteRequestMsg.setMessage (GPRSCommands.DELETE_SELECTED.toString () + messageID);
            GPRSSender.addMessage (deleteRequestMsg);
        }
    }
}

/**
 * This is a Receiver Worker Thread Class.
 *
 * It's scope is to consume incoming messages.
 */
final class ReceiverWorker extends Thread{

    private final static Logger log = LoggerFactory.getLogger (ReceiverWorker.class.getName ());

    private int id;

    public ReceiverWorker(int id){
        this.id = id;
    }

    /**
     * Consumes incoming messages when they are available.
     */
    public void run(){
        log.info ("Receiver Worker no. " + this.id + " started.");

        while(true){
            try {
                Message incMessage = GPRSReceiver.incomingMessagesQueue.take();

                // TODO: To process the incomming message
            }
            catch (InterruptedException e) {
                log.error ("Receiver Worker no. {} was interrupted while receiving a message from the queue.", this.id);
            }
        }
    }
}
