package app.groundstation.serialCommumication;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.Serial;

public class Connection {
//    private Serial serial ;
    private SerialPort serialPort;

    public static void main(String[] args) throws InterruptedException {
        for (SerialPort port: SerialPort.getCommPorts()){
            String portName = port.getSystemPortName();
            System.out.println("Port "+portName+" is Open : "+port.isOpen());
            if (!port.isOpen()){
                port.openPort();
                System.out.println("Port Opend");
                String msg = "HSH,RPI001";
                port.addDataListener(new SerialPortDataListener() {
                    @Override
                    public int getListeningEvents() {
                        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
                    }

                    @Override
                    public void serialEvent(SerialPortEvent serialPortEvent) {
                        byte[] newData = serialPortEvent.getReceivedData();
                        System.out.println("Data Received (Length: " + newData.length + ")");

                        // Convert bytes to String
                        String message = new String(newData).trim();
                        System.out.println("Content: " + message);

                        // Check if it is the handshake ACK
                        if(message.startsWith("HSHAC")) {
                            System.out.println(">> HANDSHAKE SUCCESSFUL! <<");
                        }
                    }
                });
                port.writeBytes(msg.getBytes(),msg.length());
                Thread.sleep(1500);

                port.closePort();
                System.out.println("Port Closed");

            }
        }
    }


}
