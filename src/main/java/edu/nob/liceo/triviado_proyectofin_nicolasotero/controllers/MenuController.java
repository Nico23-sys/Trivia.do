package edu.nob.liceo.triviado_proyectofin_nicolasotero.controllers;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Usuario;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.SceneManager;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuController {
    @FXML private Label lblBienvenida;
    @FXML
    private Label lblPuntos;
    @FXML private Button btnAdmin;

    @FXML
    public void initialize() {
        Usuario usuario = UserSession.getInstance().getUsuarioActual();
        if (usuario != null) {
            lblBienvenida.setText("¡Hola, " + usuario.getNickname() + "!");
            lblPuntos.setText("Tus puntos: " + usuario.getPuntuacionGlobal());

            if (usuario.getEsAdmin()) {
                btnAdmin.setVisible(true);
                btnAdmin.setManaged(true);
            }
        }
    }

    @FXML
    protected void handleJugarSolitario() {
        SceneManager.switchScene("/edu/nob/liceo/triviado_proyectofin_nicolasotero/juego-view.fxml", "Jugando en Solitario");
    }

    @FXML
    protected void handleLogout() {
        UserSession.getInstance().cerrarSesion();
        SceneManager.switchScene("/edu/nob/liceo/triviado_proyectofin_nicolasotero/login-view.fxml", "Login");
    }
}
