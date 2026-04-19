package edu.nob.liceo.triviado_proyectofin_nicolasotero.network.server;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.ConfigLoader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTrivia {
    private boolean corriendo = false;

    public void iniciarServidor() {
        int puerto = ConfigLoader.getInt("server.port");
        corriendo = true;

        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(puerto)) {
                System.out.println("Servidor multijugador iniciado en el puerto " + puerto);

                while (corriendo) {
                    Socket cliente = serverSocket.accept();
                    System.out.println("Nuevo jugador conectado desde: " + cliente.getInetAddress() );

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void detenerServidor() {
        corriendo = false;
    }
}
