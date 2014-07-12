package syswin.fences.services.gsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class GPRSReceiver {

    private final static Logger log = LoggerFactory.getLogger (GPRSReceiver.class.getName ());

    private final static Queue<Message> incomingMessagesQueue = new LinkedBlockingQueue<Message> ();

    /**
     * Given a String containing the message received from the GPRS Modem,
     * it creates a Message class instance and adds it to the imcomingMessagesQueue.
     * @param message - String
     */
    protected static void processNewMessager (String message){
        if(message == null && message.isEmpty ()){
            log.error ("Can not process and invalid message: {}", message);
            return;
        }

        log.info ("Processing message {}", message);
        message = message.replaceAll ("\n", "").replaceAll ("\r", "").replaceAll (" ", "");
        if(message.isEmpty ()){
            return;
        }
        System.out.println ("Processing message:\n---------------------------\n" + message + "\n---------------------------\n");

        Message newMessage = new Message (message, Message.MessageDirection.INCOMING);

        //incomingMessagesQueue.add (newMessage);
    }
}
