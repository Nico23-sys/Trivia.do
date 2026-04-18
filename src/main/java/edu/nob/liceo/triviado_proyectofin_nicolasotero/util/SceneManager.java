package edu.nob.liceo.triviado_proyectofin_nicolasotero.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            primaryStage.setTitle("Trivia.do - " + title);
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println("Error al cargar la escena: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
