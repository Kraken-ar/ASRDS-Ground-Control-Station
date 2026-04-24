package app.groundstation.serialCommumication.DataHandlers.DataConverters;

import app.groundstation.serialCommumication.DataHandlers.DataFormats.DroneResponse;

public class ResponseBinaryConverter implements Converter<DroneResponse,DroneResponse>{


    @Override
    public byte[] encode(DroneResponse data) {
        return new byte[0];
    }

    @Override
    public DroneResponse decode(byte[] data) {
        return null;
    }
}
