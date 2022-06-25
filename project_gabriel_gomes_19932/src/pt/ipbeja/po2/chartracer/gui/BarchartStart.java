package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Base class for actions
 *
 * @author Gabriel Alexandre Teixeira Gomes
 * @version 2022-06-22
 */
public class BarchartStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * set up the stage (window) and its menu
     *
     * @param   stage   application stage
     */
    @Override
    public void start(Stage stage) {
        stage.setOnCloseRequest(e -> exit());
        BarchartBoard board = new BarchartBoard();
        board.setPrefSize(1400, 900);
        Scene scene = new Scene(board);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * action to exit the program
     */
    private void exit() {
        Platform.runLater(() -> {

            Platform.exit();
            System.exit(0);
        });
    }
}
