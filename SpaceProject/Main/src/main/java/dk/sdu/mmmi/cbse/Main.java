package dk.sdu.mmmi.cbse;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;



public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        StackPane root = new StackPane();
        var scene = new Scene(root, 800, 600);

        window.setTitle("CBSE Game");
        window.setScene(scene);
        window.show();
    }
}
