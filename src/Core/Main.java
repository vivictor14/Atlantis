package Core;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class
 */
public class Main extends Application {

    // Fields

    Game game;

    // Methods

    @Override
    public void start(Stage primaryStage) {
        game = new Game(primaryStage);
    }

    public static void main(String[] args) { launch(args); }
}
