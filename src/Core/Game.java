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
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            root.setScaleX((double) newSceneWidth / WINDOW_WIDTH);
            root.setTranslateX(((double)newSceneWidth - WINDOW_WIDTH) / 2);
        });
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            root.setScaleY((double)newSceneHeight / WINDOW_HEIGHT);
            root.setTranslateY(((double) newSceneHeight - WINDOW_HEIGHT) / 2);
        });
    }

    // Methods

    /**
     * Initialize the primary stage
     * @param primaryStage The primary stage
     */
    private void initPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle(GAME_NAME);
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /**
     * Initialize the main menu
     */
    private void initMainMenu() {
        ArrayList<ButtonTypes> buttonsToCreate = new ArrayList<>(Arrays.asList(NewGame, Load, Options, Exit));
        mainMenu = new Menu(buttonsToCreate, Main, this);
    }

    public void newGame() {

    }

    public void saveGame() {

    }

    public void loadGame() {

    }
}
