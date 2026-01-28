package app.groundstation.serialCommumication;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class HandShake {


    private volatile HandShakeResult handShakeResult;
    private SerialPort serialPort;
    private volatile boolean sessionRecived;
    private static String handShakeTemp = "HSH,";

    public HandShakeResult makeHandShake(String ID) {

        for (SerialPort port : SerialPort.getCommPorts()) {

            sessionRecived = false;
            handShakeResult = null;

            try {
                if (!port.openPort())
                    continue;

                port.setBaudRate(115200);

                port.addDataListener(new SerialPortDataListener() {

                    @Override
                    public int getListeningEvents() {
                        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
                    }

                    @Override
                    public void serialEvent(SerialPortEvent event) {

                        byte[] newData = event.getReceivedData();
                        String message = new String(newData).trim();
                        System.out.println("Message Recive from port "+port.getSystemPortName()+" => "+message);
                        if (message.startsWith("HSHAC")) {
                            try {
                                String[] parts = message.split(" ");
                                int sessionID = Integer.parseInt(parts[1]);

                                handShakeResult = new HandShakeResult(port, true, sessionID);
                                sessionRecived = true;

                            } catch (Exception ignored) {}
                        }
                    }
                });

                // ابعت الهاندشيك
                String msg = handShakeTemp + ID;
                port.writeBytes(msg.getBytes(), msg.length());

                // استنى الرد
                long start = System.currentTimeMillis();
                long timeout = 3000;

                while (!sessionRecived &&
                        System.currentTimeMillis() - start < timeout) {
                    Thread.sleep(10);
                }

                port.removeDataListener();

                if (sessionRecived) {
                    return handShakeResult;
                }

                port.closePort();

            } catch (Exception e) {
                port.closePort();
            }
        }

        return null;
    }


}
