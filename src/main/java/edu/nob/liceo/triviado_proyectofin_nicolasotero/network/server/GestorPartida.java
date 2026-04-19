package edu.nob.liceo.triviado_proyectofin_nicolasotero.network.server;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.network.Mensaje;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.service.JuegoService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class GestorPartida extends Thread{
    private Socket j1, j2;
    private JuegoService juegoService = new JuegoService();

    public GestorPartida(Socket j1, Socket j2) {
        this.j1 = j1;
        this.j2 = j2;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream out1 = new ObjectOutputStream(j1.getOutputStream());
                ObjectInputStream in1 = new ObjectInputStream(j1.getInputStream());
                ObjectOutputStream out2 = new ObjectOutputStream(j2.getOutputStream());
                ObjectInputStream in2 = new ObjectInputStream(j2.getInputStream())
        ) {
            out1.writeObject(new Mensaje(Mensaje.Tipo.PARTIDA_ENCONTRADA, "Oponente encontrado. ¡Prepárate!"));
            out2.writeObject(new Mensaje(Mensaje.Tipo.PARTIDA_ENCONTRADA, "Oponente encontrado. ¡Prepárate!"));

            List<Pregunta> preguntas = juegoService.cargarPreguntasParaPartida(10);
            int puntosJ1 = 0, puntosJ2 = 0;

            for (Pregunta p : preguntas) {

                out1.writeObject(new Mensaje(Mensaje.Tipo.NUEVA_PREGUNTA, p));
                out2.writeObject(new Mensaje(Mensaje.Tipo.NUEVA_PREGUNTA, p));


                Mensaje r1 = (Mensaje) in1.readObject();
                Mensaje r2 = (Mensaje) in2.readObject();

                if (r1.getPayload().equals(p.getCorrecta())) puntosJ1 += 10;
                if (r2.getPayload().equals(p.getCorrecta())) puntosJ2 += 10;
            }

            out1.writeObject(new Mensaje(Mensaje.Tipo.RESULTADO_FINAL, "Fin. Tus puntos: " + puntosJ1 + " | Oponente: " + puntosJ2));
            out2.writeObject(new Mensaje(Mensaje.Tipo.RESULTADO_FINAL, "Fin. Tus puntos: " + puntosJ2 + " | Oponente: " + puntosJ1));

        } catch (Exception e) {
            System.out.println("Un jugador se ha desconectado.");
        }
    }
}
