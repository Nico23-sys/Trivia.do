package edu.nob.liceo.triviado_proyectofin_nicolasotero.controllers;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Partida;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.service.JuegoService;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.SceneManager;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JuegoController {

    @FXML private Label lblContador;
    @FXML private Label lblEnunciado;
    @FXML private Button btnOpcion1, btnOpcion2, btnOpcion3;
    @FXML private Label lblFeedback;

    private final JuegoService juegoService = new JuegoService();
    private List<Pregunta> preguntas;
    private int indiceActual = 0;
    private int aciertos = 0;
    private Partida partidaActual;

    @FXML
    public void initialize() {
        // Iniciamos partida cargando 5 preguntas para probar
        partidaActual = juegoService.iniciarPartidaSolitario(UserSession.getInstance().getUsuarioActual());
        preguntas = juegoService.cargarPreguntasParaPartida(10);

        if (preguntas.isEmpty()) {
            lblEnunciado.setText("¡No hay preguntas en la base de datos!");
            deshabilitarBotones();
        } else {
            mostrarPregunta();
        }
    }

    private void mostrarPregunta() {
        Pregunta p = preguntas.get(indiceActual);
        lblContador.setText("Pregunta " + (indiceActual + 1) + " / " + preguntas.size());
        lblEnunciado.setText(p.getEnunciado());
        lblFeedback.setText("");

        // Mezclamos respuestas para que la correcta no esté siempre en el mismo botón
        List<String> opciones = Arrays.asList(p.getCorrecta(), p.getFalsa1(), p.getFalsa2());
        Collections.shuffle(opciones);

        btnOpcion1.setText(opciones.get(0));
        btnOpcion2.setText(opciones.get(1));
        btnOpcion3.setText(opciones.get(2));
    }

    @FXML
    protected void handleOpcionClick(ActionEvent event) {
        Button btnClicado = (Button) event.getSource();
        String respuestaElegida = btnClicado.getText();
        Pregunta p = preguntas.get(indiceActual);

        boolean esCorrecta = respuestaElegida.equals(p.getCorrecta());
        if (esCorrecta) {
            aciertos += 10;
        }

        juegoService.registrarJugada(partidaActual, UserSession.getInstance().getUsuarioActual(), p, respuestaElegida, esCorrecta, 2000L);

        indiceActual++;

        if (indiceActual < preguntas.size()) {
            mostrarPregunta();
        } else {
            finalizarJuego();
        }
    }

    private void finalizarJuego() {
        lblEnunciado.setText("¡Juego Terminado! Has conseguido " + aciertos + " puntos.");
        deshabilitarBotones();

        juegoService.finalizarPartidaSolitario(partidaActual, UserSession.getInstance().getUsuarioActual(), aciertos);

        btnOpcion2.setText("Volver al Menú");
        btnOpcion2.setDisable(false);
        btnOpcion2.setOnAction(e -> SceneManager.switchScene("/edu/nob/liceo/triviado_proyectofin_nicolasotero/menu-view.fxml", "Menú"));
    }

    private void deshabilitarBotones() {
        btnOpcion1.setDisable(true);
        btnOpcion2.setDisable(true);
        btnOpcion3.setDisable(true);
    }
}
