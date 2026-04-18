package edu.nob.liceo.triviado_proyectofin_nicolasotero.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "jugadas_detalle")
public class JugadaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_partida", nullable = false)
    private Partida partida;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_pregunta", nullable = false)
    private Pregunta pregunta;

    @Column(name = "respuesta_elegida", nullable = false)
    private String respuestaElegida;

    @Column(name = "es_correcta", nullable = false)
    private Boolean esCorrecta;

    @Column(name = "tiempo_respuesta_ms", nullable = false)
    private Long tiempoRespuestaMs;


    public JugadaDetalle() {
    }

    public JugadaDetalle(Partida partida, Usuario usuario, Pregunta pregunta, String respuestaElegida, Boolean esCorrecta, Long tiempoRespuestaMs) {
        this.partida = partida;
        this.usuario = usuario;
        this.pregunta = pregunta;
        this.respuestaElegida = respuestaElegida;
        this.esCorrecta = esCorrecta;
        this.tiempoRespuestaMs = tiempoRespuestaMs;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaElegida() {
        return respuestaElegida;
    }

    public void setRespuestaElegida(String respuestaElegida) {
        this.respuestaElegida = respuestaElegida;
    }

    public Boolean getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(Boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public Long getTiempoRespuestaMs() {
        return tiempoRespuestaMs;
    }

    public void setTiempoRespuestaMs(Long tiempoRespuestaMs) {
        this.tiempoRespuestaMs = tiempoRespuestaMs;
    }
}
