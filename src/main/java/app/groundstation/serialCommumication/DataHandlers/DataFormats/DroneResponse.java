package app.groundstation.serialCommumication.DataHandlers.DataFormats;

import org.json.JSONObject;

public class DroneResponse {
    public double lat;
    public double lon;
    public double altitude;
    public int battery;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }



    public JSONObject getJsonObject(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("lat",this.getLat());
        jsonObject.put("lon",this.getLon());
        jsonObject.put("altitude",this.getAltitude());
        jsonObject.put("battery",this.getBattery());

        return jsonObject;
    }

    public void DeserilizeJsonObjec(JSONObject jsonObject){
        this.setAltitude(jsonObject.getDouble("altitude"));
        this.setLat(jsonObject.getDouble("lat"));
        this.setLon(jsonObject.getDouble("lon"));
        this.setBattery(jsonObject.getInt("altitude"));
    }
}
