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
        new edu.nob.liceo.triviado_proyectofin_nicolasotero.network.server.ServidorTrivia().iniciarServidor();
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

        edu.nob.liceo.triviado_proyectofin_nicolasotero.dao.PreguntaDAO pDao = new edu.nob.liceo.triviado_proyectofin_nicolasotero.dao.PreguntaDAO();
        if (pDao.obtenerPreguntasAleatorias(1).isEmpty()) {
            System.out.println("La base de datos está vacía. Inyectando las 10 preguntas oficiales...");

            pDao.save(new edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta("¿Cuál es el planeta más grande del sistema solar?", "Júpiter", "Marte", "Saturno", "Ciencia"));
            pDao.save(new edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta("¿En qué año llegó el hombre a la luna?", "1969", "1958", "1972", "Historia"));
            pDao.save(new edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta("¿Quién pintó la Mona Lisa?", "Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Arte"));
            pDao.save(new edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta("¿Cuál es el metal más caro del mundo?", "Rodio", "Oro", "Platino", "Ciencia"));
            pDao.save(new edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta("¿Cuál es la capital de Australia?", "Canberra", "Sídney", "Melbourne", "Geografía"));
            pDao.save(new edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta("¿Qué órgano del cuerpo humano consume más energía?", "El cerebro", "El corazón", "El hígado", "Biología"));
            pDao.save(new edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta("¿En qué país se encuentra la Torre de Pisa?", "Italia", "Francia", "España", "Geografía"));
            pDao.save(new edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta("¿Quién escribió 'Don Quijote de la Mancha'?", "Miguel de Cervantes", "Lope de Vega", "Gabriel García Márquez", "Literatura"));
            pDao.save(new edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta("¿Cuál es el océano más grande del mundo?", "Océano Pacífico", "Océano Atlántico", "Océano Índico", "Geografía"));
            pDao.save(new edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta("¿Qué gas respiramos para vivir?", "Oxígeno", "Dióxido de Carbono", "Nitrógeno", "Ciencia"));

            System.out.println("Preguntas inyectadas correctamente.");
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
