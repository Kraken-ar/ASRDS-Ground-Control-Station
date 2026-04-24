package app.groundstation.serialCommumication;

import app.groundstation.serialCommumication.DataHandlers.DataTransmitter;
import com.fazecast.jSerialComm.SerialPort;

public class ActiveConnection {

    private int sessionID;
    private boolean active;

    private DataTransmitter transmitter;

    private SerialPort port;

    public ActiveConnection(int sessionID, SerialPort port) {
        this.sessionID = sessionID;
        this.port = port;
    }
}
