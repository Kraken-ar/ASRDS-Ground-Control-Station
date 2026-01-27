package app.groundstation;

import app.groundstation.mainBrain.Brain;
import app.groundstation.map.MapObject;
import app.groundstation.map.MapPolygon;
import app.groundstation.map.PolygonPoint;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.gluonhq.maps.MapView;
import javafx.scene.layout.HBox;
import com.gluonhq.maps.MapPoint;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {



    @FXML
    private TextField x1_lat;

    @FXML
    private TextField x2_lat;
    @FXML
    private TextField x3_lat;
    @FXML
    private TextField x4_lat;

    @FXML
    private TextField x1_long;
    @FXML
    private TextField x2_long;
    @FXML
    private TextField x3_long;
    @FXML
    private TextField x4_long;

    @FXML
    private AnchorPane MapContainer;

    @FXML
    private Button x1_listen_button;

    @FXML
    private Button x2_listen_button;

    @FXML
    private Button x3_listen_button;

    @FXML
    private Button x4_listen_button;

    @FXML
    private Button DrowButton;

    @FXML
    private Button ClearButton;



    private PolygonLayer polygonLayer;

    private MapObject mapObject;

    private Brain brain;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MapView mapView = new MapView();


        this.polygonLayer = new PolygonLayer();
        MapPolygon polygon = getMapPolygon();
        this.mapObject = new MapObject(mapView,polygon,this.DrowButton,this.ClearButton);


        AnchorPane.setTopAnchor(mapView, 0.0);
        AnchorPane.setBottomAnchor(mapView, 0.0);
        AnchorPane.setLeftAnchor(mapView, 0.0);
        AnchorPane.setRightAnchor(mapView, 0.0);

        this.MapContainer.getChildren().add(mapView);

        this.brain = new Brain(this.mapObject);
    }

    private MapPolygon getMapPolygon() {
        PolygonPoint point1 = new PolygonPoint(x1_long,x1_lat,x1_listen_button);
        PolygonPoint point2 = new PolygonPoint(x2_long,x2_lat,x2_listen_button);
        PolygonPoint point3 = new PolygonPoint(x3_long,x3_lat,x3_listen_button);
        PolygonPoint point4 = new PolygonPoint(x4_long,x4_lat,x4_listen_button);
        List<PolygonPoint> list = new ArrayList<>();
        list.add(point1);
        list.add(point2);
        list.add(point3);
        list.add(point4);

        MapPolygon polygon = new MapPolygon(list);
        return polygon;
    }
}