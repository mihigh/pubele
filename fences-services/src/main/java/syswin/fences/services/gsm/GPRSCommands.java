package syswin.fences.services.gsm;

public enum GPRSCommands {
    ENTER ("\r\n"),

    AT ("AT"),
    CHECK_ALL_OKEY ("AT" + ENTER.toString ()),

    AT_CMGS ("AT+CMGS"),
    SEND_MESSAGE ("AT+CMGS="),


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
