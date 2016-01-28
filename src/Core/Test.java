package Core;

import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.image.Image;
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

    ArrayList<Double> terrainContour;
    Group group;

    public Test() {
        group = new Group();
        terrainContour = generateTerrainContour(50);
        Polyline polyline = new Polyline();
        Polyline polyline1 = new Polyline();
        polyline.getPoints().addAll(terrainContour);
        for(int i = terrainContour.size() - 1; i >= 0; i-=2) {
            polyline1.getPoints().add(terrainContour.get(i - 1));
            polyline1.getPoints().add(terrainContour.get(i) + 20);
        }

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(polyline.getPoints());
        polygon.getPoints().addAll(
                WINDOW_WIDTH, WINDOW_HEIGHT,
                0.0, WINDOW_HEIGHT

        );
        polygon.setFill(new ImagePattern(new Image(IMAGE_PATH + "Marbre.png"), 0.0, 0.0, 64.0, 64.0, false));

        Polygon polygon1 = new Polygon();
        polygon1.getPoints().addAll(polyline.getPoints());
        polygon1.getPoints().addAll(polyline1.getPoints());
        polygon1.setFill(new ImagePattern(new Image(IMAGE_PATH + "Marbre - bordures.png"), 0.0, 0.0, 18.0, 18.0, false));


        group.getChildren().addAll(polygon, polygon1);
    }


    private ArrayList<Double> generateTerrainContour(int nbPoints)
    {
        ArrayList<Double> terrainContour = new ArrayList<>();

        double rand1 = Math.random() + 1;
        double rand2 = Math.random() + 2;
        double rand3 = Math.random() + 3;

        double offset = WINDOW_HEIGHT / 2;
        double peakheight = 50;
        double flatness = 200;
        double x = 0;
        double increment = WINDOW_WIDTH / (nbPoints - 1);

        for (int i = 0; i < nbPoints; i++)
        {
            double height = peakheight / rand1 * Math.sin(x / flatness * rand1 + rand1);
            height += peakheight / rand2 * Math.sin(x / flatness * rand2 + rand2);
            height += peakheight / rand3 * Math.sin(x / flatness * rand3 + rand3);
            height += offset;
            terrainContour.add(x);
            terrainContour.add(height);
            x += increment;
        }
        return terrainContour;
    }
}
