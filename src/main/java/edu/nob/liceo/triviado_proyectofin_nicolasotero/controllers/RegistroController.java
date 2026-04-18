package edu.nob.liceo.triviado_proyectofin_nicolasotero.controllers;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.service.UsuarioService;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.SceneManager;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroController {
    @FXML
    private TextField txtNickname;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtPasswordConfirm;
    @FXML private Label lblInfo;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    protected void handleRegistrar() {
        String nick = txtNickname.getText();
        String pass = txtPassword.getText();

        if (!pass.equals(txtPasswordConfirm.getText())) {
            lblInfo.setStyle("-fx-text-fill: red;");
            lblInfo.setText("Las contraseñas no coinciden.");
            return;
        }

        lblInfo.setStyle("-fx-text-fill: blue;");
        lblInfo.setText("Registrando usuario...");

        Task<Void> registroTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                usuarioService.registrarUsuario(nick, pass, false);
                return null;
            }
        };

        registroTask.setOnSucceeded(e -> {
            lblInfo.setStyle("-fx-text-fill: green;");
            lblInfo.setText("¡Registro exitoso! Ya puedes iniciar sesión.");
            txtNickname.clear(); txtPassword.clear(); txtPasswordConfirm.clear();
        });

        registroTask.setOnFailed(e -> {
            lblInfo.setStyle("-fx-text-fill: red;");
            lblInfo.setText(registroTask.getException().getMessage());
        });

        new Thread(registroTask).start();
    }

    @FXML
    protected void handleVolver() {
        SceneManager.switchScene("/edu/nob/liceo/triviado_proyectofin_nicolasotero/login-view.fxml", "Login");
    }
}
