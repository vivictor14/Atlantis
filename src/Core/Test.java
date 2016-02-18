package Core;

import Enumerations.TerrainTypes;
import Environment.Background;
import Environment.Foreground;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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

    Pane pane;

    public Test() {
        pane = new Pane();
        Foreground foreground = new Foreground(null, TerrainTypes.Natural);
        String path = IMAGE_PATH + "Main" + MENU;
        //ImageView background = new ImageView(new Image(path));
        //group.getChildren().addAll(background, foreground.getGroup());
        pane.getChildren().add(foreground.getPane());
    }
}
