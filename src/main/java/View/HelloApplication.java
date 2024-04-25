package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("manager.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/manager.css").toExternalForm());
        stage.setTitle("Shop Management System");
        stage.setScene(scene);
        stage.setMinWidth(1512);
        stage.setMinHeight(982);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}