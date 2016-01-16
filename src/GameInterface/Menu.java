package GameInterface;

import Enumerations.ButtonTypes;
import Enumerations.MenuTypes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

import static Constants.Paths.*;

/**
 * A menu
 */
public class Menu {

    // Fields

    MenuTypes type;
    ImageView imageView;
    ArrayList<Button> buttons;

    // Constructors

    public Menu(ArrayList<ButtonTypes> buttonsToCreate, MenuTypes type) {
        this.type = type;
        imageView = new ImageView(new Image("IMAGE_PATH" + type.toString() + MENU));
        createButtons(buttonsToCreate, type);
    }

    // Methods

    /**
     * Create the buttons by using the menu type and the buttons types
     * @param buttonsToCreate The buttons types
     * @param type The menu type
     */
    private void createButtons(ArrayList<ButtonTypes> buttonsToCreate, MenuTypes type) {
        double x = 0;
        double y = 0;
        double width = 0;
        double height = 0;
        switch(type) {
            case Principal:
                break;
            case Options:
                break;
            case Pause:
                break;
        }
        for(ButtonTypes buttonToCreate : buttonsToCreate) {
            buttons.add(new Button(x, y, width, height, type.toString(), buttonToCreate));
        }
    }
}
