package edu.nob.liceo.triviado_proyectofin_nicolasotero.controllers;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.dao.UsuarioDAO;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Usuario;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class RankingController {
    @FXML
    private ListView<String> listaRanking;
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void initialize() {
        List<Usuario> topJugadores = usuarioDAO.obtenerTopJugadores(10);
        int posicion = 1;
        for (Usuario u : topJugadores) {
            listaRanking.getItems().add(posicion + "º / " + u.getNickname() + " (" + u.getPuntuacionGlobal() + " pts)");
            posicion++;
        }
    }

    @FXML
    protected void handleVolver() {
        SceneManager.switchScene("/edu/nob/liceo/triviado_proyectofin_nicolasotero/menu-view.fxml", "Menú");
    }
}
