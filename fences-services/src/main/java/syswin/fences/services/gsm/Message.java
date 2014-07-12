package syswin.fences.services.gsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Message {

    private final static Logger log = LoggerFactory.getLogger (Message.class.getName ());

    public enum MessageDirection {
        NONE,
        OUTGOING,
        INCOMING,;
    }

    public enum MessageType{
        NONE,
        SEND_MESSAGE_TO,
    }

    private MessageDirection messageDirection = MessageDirection.NONE;
    private MessageType messageType = MessageType.NONE;

    private String message = null;
    private String destination = null;

    public Message(MessageDirection messageDirection){
        if(messageDirection == null || MessageDirection.NONE.equals (messageDirection)){
            log.error ("Invalid message direction.");
            return;
        }

        this.messageDirection = messageDirection;
    }

    /**
     * Creates a Message class instance based on the messageStr reveived from the GPRS Modem
     * @param messageStr - String
     * @param messageDirection - MessageDirection
     */
    public Message (String messageStr, MessageDirection messageDirection){
        if(messageStr == null || messageStr.isEmpty () || messageDirection == null || MessageDirection.NONE.equals (messageDirection)){
            log.error ("Invalid message string or message direction.");
            return;
        }

        this.messageDirection = messageDirection;
    }

    /**
     * Sets the message and destination for this Message instance. It also prepares the command to be send to the GPRS modem.
     * @param txtMsg - String
     * @param destination - String
     */
    public void setSMSTextAndDestination(String txtMsg, String destination){
        if(txtMsg == null || txtMsg.isEmpty () || destination == null || destination.isEmpty ()){
            log.error ("Invalid text message or destination");
            return;
        }

        this.messageType = MessageType.SEND_MESSAGE_TO;
        this.messageDirection = MessageDirection.OUTGOING;

        this.message = txtMsg;
        this.destination = destination;
    }

    public MessageDirection getDirection(){
        return this.messageDirection;
    }

    public MessageType getType(){
        return this.messageType;
    }

    public String getDestination () {
        return this.destination;
    }

    public String getTxtMessage () {
        return this.message;
    }
}

