package syswin.fences.gsm.gsm;

public enum GPRSCommands {
    ENTER ("\r\n"),

    OK ("OK"),
    ERROR ("ERROR"),

    AT ("AT"),
    CHECK_ALL_OKEY ("AT" + ENTER.toString ()),

    AT_CMGS ("AT+CMGS"),
    SEND_MESSAGE ("AT+CMGS="),
    SENT_NOTIFICATION ("+CMGS:"),

    INCOMING_MESSAGE ("+CMTI:\"SM\","),

    READ_REQUEST ("AT+CMGR="),
    READ_REQUEST_RESPONSE ("+CMGR:"),

    DELETE_SELECTED ("AT+CMGD="),
    DELETE_READ ("AT+CMGD=1,1"),
    DELETE_READ_SENT("AT+CMGD=1,2"),
    //DELETE_READ_SENT_UNSENT ("AT+CMGD=1,3"),
    //DELETE_ALL ("AT+CMGD=1,4"),

    CTRL_Z ((char) 26 + ENTER.toString ()),
    ;

    private String command;

    private GPRSCommands (String command) {
        this.command = command;
    }

    public String toString () {
        return command;
    }
}
