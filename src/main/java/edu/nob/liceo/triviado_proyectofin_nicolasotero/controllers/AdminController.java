package edu.nob.liceo.triviado_proyectofin_nicolasotero.controllers;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.dao.PreguntaDAO;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminController {
    @FXML
    private TextField txtEnunciado, txtCorrecta, txtFalsa1, txtFalsa2, txtCategoria;
    @FXML private Label lblEstado;

    private final PreguntaDAO preguntaDAO = new PreguntaDAO();

    @FXML
    protected void handleAnadirPregunta() {
        if (txtEnunciado.getText().isEmpty() || txtCorrecta.getText().isEmpty()) {
            lblEstado.setStyle("-fx-text-fill: red;");
            lblEstado.setText("Faltan campos por rellenar.");
            return;
        }

        Pregunta nueva = new Pregunta(txtEnunciado.getText(), txtCorrecta.getText(), txtFalsa1.getText(), txtFalsa2.getText(), txtCategoria.getText());
        preguntaDAO.save(nueva);

        lblEstado.setStyle("-fx-text-fill: green;");
        lblEstado.setText("¡Pregunta guardada en la Base de Datos!");

        txtEnunciado.clear(); txtCorrecta.clear(); txtFalsa1.clear(); txtFalsa2.clear(); txtCategoria.clear();
    }

    @FXML
    protected void handleVolver() {
        SceneManager.switchScene("/edu/nob/liceo/triviado_proyectofin_nicolasotero/menu-view.fxml", "Menú Principal");
    }
}
