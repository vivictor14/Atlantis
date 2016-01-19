package GameInterface;

import Core.Game;
import Enumerations.ButtonTypes;
import Enumerations.MenuTypes;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.dialog.ExceptionDialog;
import java.util.ArrayList;

import static Constants.Paths.*;
import static Constants.Display.*;

/**
 * A menu
 */
public class Menu {

    // Fields

    MenuTypes type;
    ImageView imageView;
    ArrayList<Button> buttons;
    Group group;

    // Constructors

    public Menu(ArrayList<ButtonTypes> buttonsToCreate, MenuTypes type, Game game) {
        this.type = type;
        group = new Group();
        String path = IMAGE_PATH + type.toString() + MENU;
        imageView = new ImageView(new Image(path));
        group.getChildren().add(imageView);
        if(imageView.getImage().isError()) {
            ExceptionDialog exceptionDialog = new ExceptionDialog(new Exception("Le fichier " + path + " est manquant."));
            exceptionDialog.setHeaderText("Un fichier est manquant");
            exceptionDialog.setTitle(GAME_NAME);
            exceptionDialog.show();
        }
        createButtons(buttonsToCreate, type, game);
    }

    // Getters and setters
    public Group getGroup() { return group; }

    // Methods

    /**
     * Create the buttons by using the menu type and the buttons types
     * @param buttonsToCreate The buttons types
     * @param type The menu type
     */
    private void createButtons(ArrayList<ButtonTypes> buttonsToCreate, MenuTypes type, Game game) {
        double x = 0;
        double y = 0;
        double width = 0;
        double height = 0;
        switch(type) {
            case Main:
                break;
            case Options:
                break;
            case Pause:
                break;
        }
        /*for(ButtonTypes buttonToCreate : buttonsToCreate) {
            Button button = new Button(x, y, width, height, type.toString(), buttonToCreate, game)
            group.getChildren().add(button.imageView);
            buttons.add(button);
        }*/
    }
}
