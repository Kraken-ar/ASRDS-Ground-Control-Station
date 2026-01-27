package app.groundstation.serialCommumication;

import com.fazecast.jSerialComm.SerialPort;

public class ActiveConnection {

    private int sessionID;
    private boolean active;

    private SerialPort port;

    public ActiveConnection(int sessionID, SerialPort port) {
        this.sessionID = sessionID;
        this.port = port;
    }
}
