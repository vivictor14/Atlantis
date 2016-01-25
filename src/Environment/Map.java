package Environment;

import Enumerations.AreasName;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static Constants.Map.*;

/**
 * A map
 */
public class Map implements java.io.Serializable {

    // Fields

    ArrayList<Area> areas;
    HashMap<Point2D, Room> rooms;

    // Constructors

    public Map() {
        generateMap();
    }

    // Methods

    /**
     * Generate the map
     */
    private void generateMap() {
        for(AreasName areasName : AreasName.values()) {
            generateArea(areasName);
        }
    }

    /**
     * Generate an area
     * @param areasName The name of the area
     */
    private void generateArea(AreasName areasName) {

    }
}
