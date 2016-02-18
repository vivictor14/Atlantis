package GameInterface;

import Core.Game;
import Enumerations.ButtonTypes;
import Enumerations.MenuTypes;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    Pane pane;

    // Constructors

    public Menu(ArrayList<ButtonTypes> buttonsToCreate, MenuTypes type, Game game) {
        this.type = type;
        pane = new Pane();
        buttons = new ArrayList<>();
        String path = IMAGE_PATH + type.toString() + MENU;
        imageView = new ImageView(new Image(path));
        pane.getChildren().add(imageView);
        if(imageView.getImage().isError()) {
            ExceptionDialog exceptionDialog = new ExceptionDialog(new Exception("Le fichier " + path + " est manquant."));
            exceptionDialog.setHeaderText("Un fichier est manquant");
            exceptionDialog.setTitle(GAME_NAME);
            exceptionDialog.show();
        }
        createButtons(buttonsToCreate, type, game);
    }

    // Getters and setters
    public Pane getPane() { return pane; }

    // Methods

    /**
     * Create the buttons by using the menu type and the buttons types
     * @param buttonsToCreate The buttons types
     * @param type The menu type
     * @param game The game
     */
    private void createButtons(ArrayList<ButtonTypes> buttonsToCreate, MenuTypes type, Game game) {
        double x = 0;
        double y = 0;
        int i = 0;
        switch(type) {
            case Main:
                x = MAIN_BUTTON_X_POS;
                y = MAIN_BUTTON_Y_POS;
                break;
            case Options:
                break;
            case Pause:
                break;
        }
        for(ButtonTypes buttonToCreate : buttonsToCreate) {
            Button button = new Button(x + i * MAIN_BUTTON_X_SPACING, y + i * MAIN_BUTTON_Y_SPACING, type.toString(), buttonToCreate, game);
            buttons.add(button);
            pane.getChildren().add(button.imageView);
            i++;
        }
    }
}
