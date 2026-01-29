package app.groundstation.serialCommumication;

import app.groundstation.serialCommumication.DataHandlers.DataContainer;
import app.groundstation.serialCommumication.DataHandlers.DataTransmitter;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class HandShake {


    private volatile HandShakeResult handShakeResult;
    private SerialPort serialPort;
    private DataTransmitter transmitter;
    private volatile boolean sessionRecived;
    private static String handShakeTemp = "HSH,";

    public HandShakeResult makeHandShake(String ID) {
        transmitter = new DataTransmitter();

        for (SerialPort port : SerialPort.getCommPorts()) {

            sessionRecived = false;
            handShakeResult = null;

            try {
                if (!port.openPort())
                    continue;

                System.out.println("Port "+ port.getSystemPortName()+" Opend");

                port.setBaudRate(115200);

                String msg = handShakeTemp + ID;
                port.writeBytes(msg.getBytes(), msg.length());
                transmitter.setSerialPort(port);
                DataContainer container = transmitter.ReciveData();
                if (container == null)
                    continue;

                byte[] newData = container.getRowData();
                String message = new String(newData).trim();
                System.out.println("Message Recive from port "+port.getSystemPortName()+" => "+message);
                if (message.startsWith("HSHAC")) {
                    try {
                        String[] parts = message.split(" ");
                        int sessionID = Integer.parseInt(parts[1]);

                        handShakeResult = new HandShakeResult(port, true, sessionID);
                       return  handShakeResult;

                    } catch (Exception ignored) {}
                }




                port.closePort();

            } catch (Exception e) {
                port.closePort();
            }
        }

        return null;
    }


}
