package edu.nob.liceo.triviado_proyectofin_nicolasotero.network.client;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.network.Mensaje;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.ConfigLoader;
import javafx.application.Platform;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;

public class ClienteTrivia {
    private static ClienteTrivia instance;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Consumer<Mensaje> onMensajeRecibido;

    private ClienteTrivia() {}

    public static ClienteTrivia getInstance() {
        if (instance == null) instance = new ClienteTrivia();
        return instance;
    }

    public void conectar() throws Exception {
        socket = new Socket(ConfigLoader.get("server.ip"), ConfigLoader.getInt("server.port"));
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        // Hilo que escucha al servidor continuamente
        new Thread(() -> {
            try {
                while (true) {
                    Mensaje msj = (Mensaje) in.readObject();
                    if (onMensajeRecibido != null) {

                        Platform.runLater(() -> onMensajeRecibido.accept(msj));
                    }
                }
            } catch (Exception e) {
                System.out.println("Desconectado del servidor.");
            }
        }).start();
    }

    public void enviarMensaje(Mensaje msj) {
        try {
            out.writeObject(msj);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnMensajeRecibido(Consumer<Mensaje> listener) {
        this.onMensajeRecibido = listener;
    }
}
