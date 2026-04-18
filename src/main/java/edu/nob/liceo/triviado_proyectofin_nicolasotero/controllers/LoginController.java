package edu.nob.liceo.triviado_proyectofin_nicolasotero.controllers;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Usuario;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.service.UsuarioService;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.SceneManager;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.UserSession;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Optional;

public class LoginController {
    @FXML private TextField txtNickname;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    protected void handleLogin() {
        String nick = txtNickname.getText();
        String pass = txtPassword.getText();

        lblError.setStyle("-fx-text-fill: blue;");
        lblError.setText("Conectando con el servidor...");
        txtNickname.setDisable(true);
        txtPassword.setDisable(true);

        // ¡Usamos Task para no bloquear la UI como pide el profe!
        Task<Optional<Usuario>> loginTask = new Task<>() {
            @Override
            protected Optional<Usuario> call() throws Exception {
                return usuarioService.login(nick, pass);
            }
        };

        loginTask.setOnSucceeded(e -> {
            txtNickname.setDisable(false);
            txtPassword.setDisable(false);
            Optional<Usuario> usuario = loginTask.getValue();

            if (usuario.isPresent()) {
                lblError.setStyle("-fx-text-fill: green;");
                lblError.setText("¡Bienvenido " + usuario.get().getNickname() + "!");
                javafx.application.Platform.runLater(() -> {
                    UserSession.getInstance().setUsuarioActual(usuario.get());
                    SceneManager.switchScene("/edu/nob/liceo/triviado_proyectofin_nicolasotero/menu-view.fxml", "Menú Principal");
                });
            } else {
                lblError.setStyle("-fx-text-fill: red;");
                lblError.setText("Credenciales incorrectas.");
            }
        });

        new Thread(loginTask).start();
    }

    @FXML
    protected void handleRegistro() {
    }
}
