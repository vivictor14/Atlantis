package Environment;

import Enumerations.TerrainTypes;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import java.util.ArrayList;

import static Constants.Display.WINDOW_HEIGHT;
import static Constants.Display.WINDOW_WIDTH;
import static Constants.Paths.IMAGE_PATH;

/**
 * A physic foreground
 */
public class Foreground {

    // Fields

    ArrayList<Double[]> elementsCoordinates;
    transient Group group;

    // Constructors

    public Foreground(ArrayList<Double[]> elementsToContinueCoordinates, TerrainTypes terrainType) {
        elementsCoordinates = new ArrayList<>();

        switch(terrainType) {
            case Flat:
                break;
            case Natural:
                break;
        }

        elementsToContinueCoordinates.stream().forEach(element -> elementsCoordinates.add(generateForegroundElement(element, 50)));
        elementsCoordinates.add(generateForegroundElement(50));

        generateGroup();
    }

    // Methods

    public Group getGroup() { return group; }

    public void generateGroup() {
        group = new Group();
        ArrayList<Polygon> polygons = new ArrayList<>();
        elementsCoordinates.stream().forEach(element -> polygons.add(generatePolygon(element)));
        mergePolygons(polygons);
        polygons.stream().forEach(element -> group.getChildren().addAll(element, generateBorder(element)));
    }

    /**
     * Generate a foreground element
     * @param nbPoints The number of points (level of detail)
     * @return An array of the elements coordinates
     */
    private Double[] generateForegroundElement(int nbPoints)
    {
        Double[] elementCoordinates = new Double[nbPoints * 2];

        double rand1 = Math.random() + 1;
        double rand2 = Math.random() + 2;
        double rand3 = Math.random() + 3;

        double offset = WINDOW_HEIGHT / 2;
        double peakHeight = 50;
        double flatness = 200;
        double x = 0;
        double increment = WINDOW_WIDTH / (nbPoints - 1);
        int index = 0;

        for (int i = 0; i < nbPoints; i++)
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
     * Generate a foreground element
     * @param elementToContinueCoordinates The coordinates of the elements that must be continued
     * @param nbPoints The number of points (level of detail)
     * @return An array of the elements coordinates
     */
    private Double[] generateForegroundElement(Double[] elementToContinueCoordinates, int nbPoints)
    {
        Double[] elementCoordinates = new Double[nbPoints * 2];

        double rand1 = Math.random() + 1;
        double rand2 = Math.random() + 2;
        double rand3 = Math.random() + 3;

        double offset = WINDOW_HEIGHT / 2;
        double peakHeight = 50;
        double flatness = 200;
        double x = 0;
        double increment = WINDOW_WIDTH / (nbPoints - 1);
        int index = 0;

        for (int i = 0; i < nbPoints; i++)
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
        Polyline internalBorder = transformPolyline(externalBorder);

        Polygon element = new Polygon();
        element.getPoints().addAll(externalBorder.getPoints());
        element.getPoints().addAll(
                WINDOW_WIDTH, WINDOW_HEIGHT,
                0.0, WINDOW_HEIGHT
        );
        element.setFill(new ImagePattern(new Image(IMAGE_PATH + "Marbre.png"), 0.0, 0.0, 64.0, 64.0, false));

        Polygon border = new Polygon();
        border.getPoints().addAll(externalBorder.getPoints());
        border.getPoints().addAll(internalBorder.getPoints());
        border.setFill(Color.GREY);

        return element;
        //group.getChildren().addAll(element, border);
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
     * @param polygon The polygon
     * @return The border
     */
    private Polygon generateBorder(Polygon polygon) {
        Polygon border = new Polygon();
        return border;
    }
}
