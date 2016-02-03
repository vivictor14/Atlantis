package Environment;

import Enumerations.TerrainTypes;
import com.sun.istack.internal.Nullable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import java.util.ArrayList;

import static Constants.Display.*;
import static Constants.Paths.*;
import static Constants.Map.*;

/**
 * A physic foreground
 */
public class Foreground {

    // Fields

    ArrayList<Double[]> elementsCoordinates;
    transient Group group;

    // Constructors

    public Foreground(@Nullable ArrayList<Double[]> elementsToContinueCoordinates, TerrainTypes terrainType) {
        elementsCoordinates = new ArrayList<>();

        double peakHeight = 0;
        double flatness = 0;
        int length = 0;
        switch(terrainType) {
            case Flat:
                break;
            case Natural:
                peakHeight = 50;
                flatness = 200;
                length = 1000;
                break;
        }

        if(elementsToContinueCoordinates != null) {
            for (Double[] elementToContinueCoordinates : elementsToContinueCoordinates) {
                elementsCoordinates.add(generateForegroundElement(elementToContinueCoordinates, peakHeight, flatness, length));
            }
        }
        elementsCoordinates.add(generateForegroundElement(peakHeight, flatness, length));

        generateGroup();
    }

    // Methods

    public Group getGroup() { return group; }

    public void generateGroup() {
        group = new Group();
        ArrayList<Polygon> polygons = new ArrayList<>();
        elementsCoordinates.stream().forEach(element -> polygons.add(generatePolygon(element)));
        mergePolygons(polygons);
        int index = 0;
        for(Polygon element : polygons) {
            group.getChildren().addAll(element, generateBorder(index));
            index++;
        }
        //polygons.stream().forEach(element -> group.getChildren().addAll(element, generateBorder(element)));
    }


    private Double[] generateForegroundElement(double peakHeight, double flatness, int length)
    {
        Double[] elementCoordinates = new Double[(int)(LEVEL_OF_DETAIL * length * 2)];

        double rand1 = Math.random() + 1;
        double rand2 = Math.random() + 2;
        double rand3 = Math.random() + 3;

        double offset = WINDOW_HEIGHT / 2;
        double x = 0;
        double increment = 1 / LEVEL_OF_DETAIL;
        int index = 0;

        for (int i = 0; i < LEVEL_OF_DETAIL * length; i++)
        {
            double height = peakHeight / rand1 * Math.sin(x / flatness * rand1 + rand1);
            height += peakHeight / rand2 * Math.sin(x / flatness * rand2 + rand2);
            height += peakHeight / rand3 * Math.sin(x / flatness * rand3 + rand3);
            height += offset;
            elementCoordinates[index] = x;
            elementCoordinates[index + 1] = height;
            index += 2;
            x += increment;
        }
        return elementCoordinates;
    }


    private Double[] generateForegroundElement(Double[] elementToContinueCoordinates, double peakHeight, double flatness, int length)
    {
        Double[] elementCoordinates = new Double[(int)(LEVEL_OF_DETAIL * length * 2)];

        double rand1 = Math.random() + 1;
        double rand2 = Math.random() + 2;
        double rand3 = Math.random() + 3;

        double offset = WINDOW_HEIGHT / 2;
        double x = 0;
        double increment = 1 / LEVEL_OF_DETAIL;
        int index = 0;

        for (int i = 0; i < LEVEL_OF_DETAIL * length; i++)
        {
            double height = peakHeight / rand1 * Math.sin(x / flatness * rand1 + rand1);
            height += peakHeight / rand2 * Math.sin(x / flatness * rand2 + rand2);
            height += peakHeight / rand3 * Math.sin(x / flatness * rand3 + rand3);
            height += offset;
            elementCoordinates[index] = x;
            elementCoordinates[index + 1] = height;
            index += 2;
            x += increment;
        }
        return elementCoordinates;
    }

    /**
     * Generate a polygon with the given coordinates
     * @param elementCoordinates The coordinates of the polygon to generate
     */
    private Polygon generatePolygon(Double[] elementCoordinates) {
        Polyline externalBorder = new Polyline();
        externalBorder.getPoints().addAll(elementCoordinates);

        Polygon element = new Polygon();
        element.getPoints().addAll(externalBorder.getPoints());
        element.getPoints().addAll(
                WINDOW_WIDTH, WINDOW_HEIGHT,
                0.0, WINDOW_HEIGHT
        );
        element.setFill(new ImagePattern(new Image(IMAGE_PATH + "Marbre.png"), 0.0, 0.0, 64.0, 64.0, false));

        return element;
    }

    /**
     * Transform a polyline by mirroring and translating it
     * @param polyline The polyline to transform
     * @return The transformed polyline
     */
    private Polyline transformPolyline(Polyline polyline) {
        Polyline transformedPolyline = new Polyline();

        for(int i = polyline.getPoints().size() - 1; i >= 0; i-=2) {
            transformedPolyline.getPoints().add(polyline.getPoints().get(i - 1));
            transformedPolyline.getPoints().add(polyline.getPoints().get(i) + 20);
        }

        return transformedPolyline;
    }

    /**
     * Merge the overlapping polygons
     * @param polygons The polygons
     */
    private void mergePolygons(ArrayList<Polygon> polygons) {

    }

    /**
     * Generate the border of a polygon
     * @param index The index of the polygon coordinates
     * @return The border
     */
    private Polygon generateBorder(int index) {
        Polygon border = new Polygon();
        Polyline externalBorder = new Polyline();
        externalBorder.getPoints().addAll(elementsCoordinates.get(index));
        Polyline internalBorder = transformPolyline(externalBorder);
        border.getPoints().addAll(externalBorder.getPoints());
        border.getPoints().addAll(internalBorder.getPoints());
        border.setFill(Color.GREY);
        return border;
    }
}
