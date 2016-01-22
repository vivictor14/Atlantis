package GameInterface;

import Core.Game;
import Enumerations.ButtonTypes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.dialog.ExceptionDialog;

import static Constants.Display.*;
import static Constants.Paths.*;

/**
 * A button
 */
public class Button {

    // Fields

    double posX;
    double posY;
    ButtonTypes type;
    Image[] images;
    ImageView imageView;
    boolean clicked = false;

    // Constructors

    public Button(double posX, double posY, String context, ButtonTypes type, Game game) {
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        setImages(type, context);
        imageView = new ImageView(images[0]);
        imageView.setX(posX);
        imageView.setY(posY);
        setEvents(game);
    }

    // Methods

    /**
     *  Set the events of the button depending on the type of the button
     * @param game The game
     */
    private void setClickEvent(Game game) {
        switch(type) {
            case Exit:
                imageView.setOnMouseReleased(mouseEvent -> System.exit(0));
                break;
            case Save:
                imageView.setOnMouseReleased(mouseEvent -> game.saveGame());
                break;
            case Load:
                imageView.setOnMouseReleased(mouseEvent -> game.loadGame());
                break;
            case NewGame:
                imageView.setOnMouseReleased(mouseEvent -> game.newGame());
                break;
            case Options:
                imageView.setOnMouseReleased(mouseEvent -> System.exit(0));
                break;
        }
    }

    /**
     * Set the images of the button depending on the context and the type of the button
     * @param type The type of the button
     * @param context The context
     */
    private void setImages(ButtonTypes type, String context) {
        images = new Image[3];
        String path = "";
        for (int i = 0; i < images.length; i++) {
            switch(i) {
                case 0:
                    path = IMAGE_PATH + context + type + BUTTON_SLEEP;
                    break;
                case 1:
                    path = IMAGE_PATH + context + type + BUTTON_HOVER;
                    break;
                case 2:
                    path = IMAGE_PATH + context + type + BUTTON_ACTIVE;
                    break;
            }
            images[i] = new Image(path);
            if(images[i].isError()) {
                ExceptionDialog exceptionDialog = new ExceptionDialog(new Exception("Le fichier " + path + " est manquant."));
                exceptionDialog.setHeaderText("Un fichier est manquant");
                exceptionDialog.setTitle(GAME_NAME);
                exceptionDialog.show();
            }
        }
    }

    /**
     * Set the mouse events
     */
    private void setEvents(Game game) {
        imageView.setOnMouseEntered(mouseEvent -> imageView.setImage(images[1]));
        imageView.setOnMouseExited(mouseEvent -> imageView.setImage(images[0]));
        imageView.setOnMouseClicked(mouseEvent -> imageView.setImage(images[2]));
        setClickEvent(game);
    }
}
