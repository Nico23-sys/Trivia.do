package edu.nob.liceo.triviado_proyectofin_nicolasotero.util;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Usuario;

public class UserSession {
    private static UserSession instance;
    private Usuario usuarioActual;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }
}
