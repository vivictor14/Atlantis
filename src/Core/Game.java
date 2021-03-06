package Core;

import Enumerations.ButtonTypes;
import Environment.Map;
import GameInterface.Menu;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
        double initialWidth = WINDOWED_WINDOW_WIDTH;
        double initialHeight = WINDOWED_WINDOW_HEIGHT;
        initRoot(initialWidth, initialHeight);
        initScene(initialWidth, initialHeight);
        initPrimaryStage(primaryStage);
        initMainMenu();
        show(mainMenu.getPane());
    }

    // Methods

    /**
     * Initialize the root group
     */
    private void initRoot(double initialWidth, double initialHeight) {
        //Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = new Group();
        root.setScaleX(initialWidth / WINDOW_WIDTH);
        root.setScaleY(initialHeight / WINDOW_HEIGHT);
        root.setTranslateX((initialWidth - WINDOW_WIDTH) / 2);
        root.setTranslateY((initialHeight - WINDOW_HEIGHT) / 2);
    }

    /**
     * Initialize the scene
     */
    private void initScene(double initialWidth, double initialHeight) {
        scene = new Scene(root, initialWidth, initialHeight);
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
        primaryStage.setResizable(true);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /**
     * Initialize the main menu
     */
    private void initMainMenu() {
        ArrayList<ButtonTypes> buttonsToCreate;
        if(new File("save.ser").exists()) {
            buttonsToCreate = new ArrayList<>(Arrays.asList(NewGame, Continue, Options, Exit));
        }
        else {
            buttonsToCreate = new ArrayList<>(Arrays.asList(NewGame, Options, Exit));
        }
        mainMenu = new Menu(buttonsToCreate, Main, this);
    }

    /**
     * Show the desired group
     * @param pane The pane to show
     */
    public void show(Pane pane) {
        root.getChildren().add(pane);
    }

    /**
     * Launch a new game
     */
    public void newGame() {
        Test test = new Test();
        root.getChildren().clear();
        show(test.pane);
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
