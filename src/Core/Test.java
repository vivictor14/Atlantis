package Core;

import Enumerations.TerrainTypes;
import Environment.Foreground;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import java.util.ArrayList;

import static Constants.Paths.*;
import static Constants.Display.*;

/**
 * Created by Victor on 26/01/2016.
 */
public class Test {

    Group group;

    public Test() {
        group = new Group();
        Foreground foreground = new Foreground(null, TerrainTypes.Natural);
        group.getChildren().add(foreground.getGroup());
    }
}
