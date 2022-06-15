package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BarchartStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BarchartBoard board = new BarchartBoard();
        Scene scene = new Scene(board);
        stage.setScene(scene);
        stage.show();
    }

//    private void clear() {
//        Platform.runLater(() -> {
//            pane.getChildren().clear();
//        });
//    }
}
