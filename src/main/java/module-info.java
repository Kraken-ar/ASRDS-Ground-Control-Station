module app.groundstation {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.gluonhq.maps;
    requires com.fazecast.jSerialComm;


    opens app.groundstation to javafx.fxml;
    exports app.groundstation;
}