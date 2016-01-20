package Core;

import Enumerations.ButtonTypes;
import GameInterface.Menu;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;

import static Enumerations.ButtonTypes.Options;
import static Enumerations.MenuTypes.*;
import static Enumerations.ButtonTypes.*;
import static Constants.Display.*;

/**
 * The game class
 */
public class Game {

    // Fields

    Group root;
    Scene scene;
    Menu mainMenu;

    // Constructors

    public Game(Stage primaryStage) {
        root = new Group();
        scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        initPrimaryStage(primaryStage);
        initMainMenu();
        root.getChildren().add(mainMenu.getGroup());
        //scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> modele.redimensionner());
        //scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> modele.redimensionner());
    }

    // Methods

    /**
     * Initialize the primary stage
     * @param primaryStage The primary stage
     */
    private void initPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle(GAME_NAME);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /**
     * Initialize the main menu
     */
    private void initMainMenu() {
        ArrayList<ButtonTypes> buttonsToCreate = new ArrayList<>(Arrays.asList(New_Game, Load, Options, Exit));
        mainMenu = new Menu(buttonsToCreate, Main, this);
    }
}