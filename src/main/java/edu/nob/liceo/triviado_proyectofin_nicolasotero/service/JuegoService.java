package edu.nob.liceo.triviado_proyectofin_nicolasotero.service;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.dao.PartidaDAO;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.dao.PreguntaDAO;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.dao.UsuarioDAO;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Partida;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Usuario;

import java.util.List;

public class JuegoService {
    private final PreguntaDAO preguntaDAO = new PreguntaDAO();
    private final PartidaDAO partidaDAO = new PartidaDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public List<Pregunta> cargarPreguntasParaPartida(int cantidad) {
        return preguntaDAO.obtenerPreguntasAleatorias(cantidad);
    }

    public Partida iniciarPartidaSolitario(Usuario jugador) {
        Partida nuevaPartida = new Partida(jugador, null);
        partidaDAO.save(nuevaPartida);
        return nuevaPartida;
    }

    public void finalizarPartidaSolitario(Partida partida, Usuario jugador, int puntuacionObtenida) {
        partida.setEstado("FINALIZADA");
        partida.setGanador(jugador);
        partidaDAO.update(partida);

        // Sumar puntos al perfil del jugador
        jugador.sumarPuntos((long) puntuacionObtenida);
        usuarioDAO.update(jugador);
    }
}
