package Environment;

import Enumerations.AreaNames;

import java.awt.geom.Point2D;
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
        for(AreaNames areaName : AreaNames.values()) {
            Area area = new Area(areaName);
            generateArea(area);
            areas.add(area);
        }
    }

    /**
     * Generate an area
     * @param area The area to generate
     */
    private void generateArea(Area area) {
        area.rooms.add(new Room());
    }
}
