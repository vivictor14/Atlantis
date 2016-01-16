package GameInterface;

import Enumerations.ButtonTypes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public Button(double posX, double posY, double width, double height, String context, ButtonTypes type) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        setClickEvent(type);
        setImages(type, context);
        imageView = new ImageView(images[0]);
        setEvents();
    }

    // Methods

    /**
     *  Set the events of the button depending on the type of the button
     * @param type The type of the button
     */
    private void setClickEvent(ButtonTypes type) {
        switch(type) {
            case Quitter:
                break;
            case Sauvegarder:
                break;
            case Charger:
                break;
            case Commencer:
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
        images[0] = new Image(IMAGE_PATH + context + type + BUTTON_SLEEP);
        images[1] = new Image(IMAGE_PATH + context + type + BUTTON_HOVER);
        images[2] = new Image(IMAGE_PATH + context + type + BUTTON_ACTIVE);
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
