package edu.nob.liceo.triviado_proyectofin_nicolasotero.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "partidas")
public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_jugador1", nullable = false)
    private Usuario jugador1;

    @ManyToOne
    @JoinColumn(name = "id_jugador2")
    private Usuario jugador2;

    @ManyToOne
    @JoinColumn(name = "id_ganador")
    private Usuario ganador;

    @Column(name = "fecha_inicio", nullable = false, updatable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private String estado;

    public Partida() {
        this.fechaInicio = LocalDateTime.now();
        this.estado = "EN_CURSO";
    }

    public Partida(Usuario jugador1, Usuario jugador2) {
        this();
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }


    public Integer getId() { return id; }

    public Usuario getJugador1() { return jugador1; }
    public void setJugador1(Usuario jugador1) { this.jugador1 = jugador1; }

    public Usuario getJugador2() { return jugador2; }
    public void setJugador2(Usuario jugador2) { this.jugador2 = jugador2; }

    public Usuario getGanador() { return ganador; }
    public void setGanador(Usuario ganador) { this.ganador = ganador; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}