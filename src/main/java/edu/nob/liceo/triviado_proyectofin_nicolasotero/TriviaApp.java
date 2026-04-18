package edu.nob.liceo.triviado_proyectofin_nicolasotero;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.service.UsuarioService;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.HibernateUtil;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TriviaApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        inicializarBaseDeDatos();

        FXMLLoader fxmlLoader = new FXMLLoader(TriviaApp.class.getResource("/edu/nob/liceo/triviado_proyectofin_nicolasotero/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setTitle("Login");
        stage.setScene(scene);
        SceneManager.setPrimaryStage(stage);
        stage.show();
    }

    private void inicializarBaseDeDatos() {
        System.out.println("Conectando a MySQL y verificando tablas...");
        UsuarioService service = new UsuarioService();
        try {
            service.registrarUsuario("admin", "admin123", true);
            System.out.println("Usuario administrador creado por defecto.");
        } catch (Exception e) {
            System.out.println("El usuario administrador ya existe. Todo OK.");
        }
    }

    @Override
    public void stop() throws Exception {
        HibernateUtil.shutdown();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
