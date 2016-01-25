package Core;

import Enumerations.ButtonTypes;
import Environment.Map;
import GameInterface.Menu;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.dialog.ExceptionDialog;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static Enumerations.ButtonTypes.Options;
import static Enumerations.MenuTypes.*;
import static Enumerations.ButtonTypes.*;
import static Constants.Display.*;

/**
 * The game class
 */
public class Game implements java.io.Serializable {

    // Fields

    transient Group root;
    transient Scene scene;
    transient Menu mainMenu;
    Map map;

    // Constructors

    public Game(Stage primaryStage) {
        initRoot();
        initScene();
        initPrimaryStage(primaryStage);
        initMainMenu();
        show(mainMenu.getGroup());
    }

    // Methods

    /**
     * Initialize the root group
     */
    private void initRoot() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = new Group();
        root.setScaleX(screenBounds.getWidth() / WINDOW_WIDTH);
        root.setScaleY(screenBounds.getHeight() / WINDOW_HEIGHT);
        root.setTranslateX((screenBounds.getWidth() - WINDOW_WIDTH) / 2);
        root.setTranslateY((screenBounds.getHeight() - WINDOW_HEIGHT) / 2);
    }

    /**
     * Initialize the scene
     */
    private void initScene() {
        scene = new Scene(root, WINDOWED_WINDOW_WIDTH, WINDOWED_WINDOW_HEIGHT);
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            root.setScaleX((double) newSceneWidth / WINDOW_WIDTH);
            root.setTranslateX(((double)newSceneWidth - WINDOW_WIDTH) / 2);
        });
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            root.setScaleY((double)newSceneHeight / WINDOW_HEIGHT);
            root.setTranslateY(((double) newSceneHeight - WINDOW_HEIGHT) / 2);
        });
    }
    /**
     * Initialize the primary stage
     * @param primaryStage The primary stage
     */
    private void initPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle(GAME_NAME);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /**
     * Initialize the main menu
     */
    private void initMainMenu() {
        ArrayList<ButtonTypes> buttonsToCreate;
        /*if(new File("save.ser").exists()) {
            buttonsToCreate = new ArrayList<>(Arrays.asList(NewGame, Continue, Options, Exit));
        }
        else {
            buttonsToCreate = new ArrayList<>(Arrays.asList(NewGame, Options, Exit));
        }*/
        buttonsToCreate = new ArrayList<>(Arrays.asList(NewGame, Save, Continue, Options, Exit));
        mainMenu = new Menu(buttonsToCreate, Main, this);
    }

    /**
     * Show the desired group
     * @param group The group to show
     */
    public void show(Group group) {
        root.getChildren().add(group);
    }

    /**
     * Launch a new game
     */
    public void newGame() {
    }

    /**
     * Save the game
     */
    public void saveGame() {
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream file = new FileOutputStream("save.ser");
            oos = new ObjectOutputStream(file);
            oos.writeObject(this);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Load a save
     */
    public void loadGame() {
        ObjectInputStream ois = null;
        Game loadedGame = null;
        try {
            final FileInputStream file = new FileInputStream("save.ser");
            ois = new ObjectInputStream(file);
            loadedGame = (Game) ois.readObject();
        } catch (final java.io.IOException e) {
            ExceptionDialog exceptionDialog = new ExceptionDialog(e);
            exceptionDialog.setHeaderText("Le fichier de sauvegarde est introuvable");
            exceptionDialog.setTitle(GAME_NAME);
            exceptionDialog.show();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        map = loadedGame.map;
    }
}
