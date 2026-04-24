package app.groundstation.serialCommumication.DataHandlers.DataConverters;

public interface Converter<Request, Response> {

     public byte[] encode(Request data);

     public Response decode(byte[] data);

}
