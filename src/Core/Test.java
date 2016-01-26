package Core;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;

import java.awt.geom.Arc2D;

import static Constants.Paths.*;
import static Constants.Display.*;

/**
 * Created by Victor on 26/01/2016.
 */
public class Test {

    Polygon polygon;
    Group group;

    public Test() {
        group = new Group();
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(
                0.0, WINDOW_HEIGHT / 2,
                WINDOW_WIDTH / 2, WINDOW_HEIGHT * 3 / 4,
                WINDOW_WIDTH, WINDOW_HEIGHT / 2
        );
        Polyline newPoly = new Polyline();
        newPoly.getPoints().addAll(polyline.getPoints().get(0), polyline.getPoints().get(1));
        for(int i = 2; i < polyline.getPoints().size() - 2; i+=2) {

        }
        newPoly.getPoints().addAll(polyline.getPoints().get(polyline.getPoints().size() - 2), polyline.getPoints().get(polyline.getPoints().size() - 1));
        polygon = new Polygon();
        polygon.getPoints().addAll(polyline.getPoints());
        polygon.getPoints().addAll(
                WINDOW_WIDTH, WINDOW_HEIGHT,
                0.0, WINDOW_HEIGHT
        );
        polygon.setFill(new ImagePattern(new Image(IMAGE_PATH + "Marbre.png"), 0.0, WINDOW_HEIGHT / 2, 64.0, 64.0, false));
        group.getChildren().add(polygon);
    }
}
