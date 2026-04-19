package edu.nob.liceo.triviado_proyectofin_nicolasotero.network;

import java.io.Serializable;

public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Tipo {
        BUSCANDO_PARTIDA,
        PARTIDA_ENCONTRADA,
        NUEVA_PREGUNTA,
        ENVIAR_RESPUESTA,
        RESULTADO_FINAL
    }

    private Tipo tipo;
    private Object payload; // texto de la pregunta, la respuesta, etc...

    public Mensaje(Tipo tipo, Object payload) {
        this.tipo = tipo;
        this.payload = payload;
    }

    public Tipo getTipo() { return tipo; }
    public Object getPayload() { return payload; }
}
