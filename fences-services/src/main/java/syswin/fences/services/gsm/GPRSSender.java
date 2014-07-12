package syswin.fences.services.gsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class GPRSSender {

    private final static Logger log = LoggerFactory.getLogger (GPRSSender.class.getName ());

    private final static LinkedBlockingQueue<Message> outgoingMessagesQueue = new LinkedBlockingQueue<Message> ();

    private static final Sender sender;

    static{
        sender = new Sender();
        sender.start ();
    }

    /**
     * Creates a Message class instance based on the message and the destination and adds it to the outgoing message queue.
     * @param messageStr - String
     * @param destinationNumber - String
     */
    public static void sendMessageTo (String messageStr, String destinationNumber){
        if(messageStr == null || messageStr.isEmpty () || destinationNumber == null || destinationNumber.isEmpty ()){
            log.error ("Invalid text message or destination");
            return;
        }

        Message newTextMessage = new Message (Message.MessageDirection.OUTGOING);
        newTextMessage.setSMSTextAndDestination (messageStr, destinationNumber);
        outgoingMessagesQueue.add (newTextMessage);
    }

    private final static class Sender extends Thread{
        public void run(){
            System.out.println ("Sender started...");
            while(true){
                Message msg = null;
                try {
                    msg = outgoingMessagesQueue.take ();

                    if( !Message.MessageDirection.OUTGOING.equals (msg.getDirection ()) ||
                         Message.MessageType.NONE.equals (msg.getType ())){
                        continue;
                    }

                    GPRSUtilities.sendCommandToGPRS(msg);
                }
                catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
        }
    }
}
