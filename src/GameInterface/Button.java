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
    double width;
    double height;
    Image[] images;
    ImageView imageView;

    // Constructors

    public Button(double posX, double posY, double width, double height, String context, ButtonTypes type, Game game) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        setClickEvent(type, game);
        setImages(type, context);
        imageView = new ImageView(images[0]);
        setEvents();
    }

    // Methods

    /**
     *  Set the events of the button depending on the type of the button
     * @param type The type of the button
     * @param game The game
     */
    private void setClickEvent(ButtonTypes type, Game game) {
        switch(type) {
            case Exit:
                break;
            case Save:
                break;
            case Load:
                break;
            case New_Game:
                break;
            case Options:
                break;
        }
    }

    /**
     * Set the images of the button depending on the context and the type of the button
     * @param type The type of the button
     * @param context The context
     */
    private void setImages(ButtonTypes type, String context) {
        images = new Image[2];
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
    private void setEvents() {
        imageView.setOnMouseEntered(mouseEvent -> imageView.setImage(images[1]));
        imageView.setOnMouseExited(mouseEvent -> imageView.setImage(images[0]));
        imageView.setOnMouseClicked(mouseEvent -> imageView.setImage(images[2]));
    }
}
