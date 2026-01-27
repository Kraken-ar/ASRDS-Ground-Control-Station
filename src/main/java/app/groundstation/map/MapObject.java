package app.groundstation.map;

import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.scene.control.Button;

public class MapObject {

    private MapView mapView;
    private MapPolygon mapPolygon;
    private Button drowButton;
    private Button clearButton;

    public MapObject(MapView mapView,MapPolygon mapPolygon,Button drowButton,Button clearButton){
        this.mapView = mapView;
        this.mapPolygon = mapPolygon;
        this.drowButton = drowButton;
        this.clearButton = clearButton;

        this.clearButton.setOnMouseClicked(event -> {
            this.mapPolygon.clear();
        });
        this.drowButton.setOnMouseClicked(event -> {
            this.mapPolygon.draw();
        });
        this.mapView.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            MapPoint clickedPoint = this.mapView.getMapPosition(x, y);
            mapPolygon.listenHandle(clickedPoint.getLongitude(),clickedPoint.getLatitude());
        });

        mapView.addLayer(this.mapPolygon);
        mapView.setCenter(29.333938744108426,31.02326206313191);
        mapView.onZoomProperty().addListener((obs, oldZoom, newZoom) -> {
            System.out.println("Zoom changed from " + oldZoom + " to " + newZoom);


        });
        mapView.setZoom(6);
        mapView.setCenter(31.19342,29.90032);
    }

}
