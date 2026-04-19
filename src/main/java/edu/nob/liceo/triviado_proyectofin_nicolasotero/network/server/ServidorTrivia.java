package edu.nob.liceo.triviado_proyectofin_nicolasotero.network.server;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.ConfigLoader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTrivia {private boolean corriendo = false;
    private Socket jugadorEsperando = null;

    public void iniciarServidor() {
        int puerto = ConfigLoader.getInt("server.port");
        corriendo = true;

        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(puerto)) {
                System.out.println("Servidor multijugador iniciado en el puerto " + puerto);

                while (corriendo) {
                    Socket clienteNuevo = serverSocket.accept();
                    System.out.println("Nuevo jugador conectado.");

                    if (jugadorEsperando == null) {
                        jugadorEsperando = clienteNuevo;
                        System.out.println("Jugador 1 esperando oponente...");
                    } else {
                        System.out.println("Jugador 2 conectado. Emparejando...");
                        Socket jugador1 = jugadorEsperando;
                        Socket jugador2 = clienteNuevo;
                        jugadorEsperando = null;

                        // hilo independiente para gestionar esta partida
                        new GestorPartida(jugador1, jugador2).start();
                    }
                }
            } catch (java.net.BindException e) {
                System.out.println("El servidor ya está corriendo en otra ventana. Actuando solo como cliente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void detenerServidor() {
        corriendo = false;
    }
}
