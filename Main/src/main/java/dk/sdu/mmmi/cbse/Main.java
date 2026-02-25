package dk.sdu.mmmi.cbse;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Main extends Application {

    @Override
    public void start(Stage window) throws Exception {
        StackPane root = new StackPane();

        try {
            Image backgroundImage = new Image(getClass().getResourceAsStream("/spaceprojekt.png"));
            ImageView backgroundImageView = new ImageView(backgroundImage);

            backgroundImageView.setFitWidth(800);
            backgroundImageView.setFitHeight(600);
            backgroundImageView.setPreserveRatio(false);

            root.getChildren().add(backgroundImageView);

        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            return;
        }


        var scene = new Scene(root, 800, 600);

        window.setTitle("Jonas' crazy space project");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
