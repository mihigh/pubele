package syswin.fences.services.gsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class GPRSReceiver {

    private final static Logger log = LoggerFactory.getLogger (GPRSReceiver.class.getName ());

    final static LinkedBlockingQueue<Message> incomingMessagesQueue = new LinkedBlockingQueue<Message> ();

    private final static List<ReceiverWorker> WORKERS_LIST = new LinkedList<ReceiverWorker> ();

    private final static int NUMBER_OF_WORKERS = 10;

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

    private static String incompleteMessage = "";
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
                System.out.println ("Received a new message with ID (inside GPRS Modem) " + newMessage.getMessageID() + ".");
                log.info ("Received a new message with ID (inside GPRS Modem) {}.", newMessage.getMessageID());

                Message requestReadMsg = new Message (Message.MessageDirection.OUTGOING, Message.MessageType.READ_REQUEST);
                requestReadMsg.setMessage (GPRSCommands.READ_REQUEST.toString () + newMessage.getMessageID());

                GPRSSender.addMessage (requestReadMsg);
                break;

            case INCOMPLETE:
                incompleteMessage = incompleteMessage + message;
                return;

            case PARENT_MESSAGE:
                System.out.println ("Got Parent message: " + newMessage.getMessage ());
                //incomingMessagesQueue.add (newMessage);
                System.out.println ("-----------------------------------------------");
                System.out.println ();
                break;

            case GRANDPARENT_MESSAGE:
                System.out.println ("Got Grand Parent message: " + newMessage.getMessage ());
                //incomingMessagesQueue.add (newMessage);
                break;

            default:
                log.error ("Something not good...");
                break;
        }
        incompleteMessage = "";
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
