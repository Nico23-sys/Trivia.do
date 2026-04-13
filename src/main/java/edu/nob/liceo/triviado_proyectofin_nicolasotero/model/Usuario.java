package edu.nob.liceo.triviado_proyectofin_nicolasotero.model;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.EncryptionConverter;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;


import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Audited
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "nickname", nullable = false, unique = true, length = 50)
    private String nickname;

    //convertidor AES
    @Column(name = "password", nullable = false)
    @Convert(converter = EncryptionConverter.class)
    private String password;

    @Column(name = "es_admin", nullable = false)
    private Boolean esAdmin = false;

    @Column(name = "puntuacion_global", nullable = false)
    private Long puntuacionGlobal = 0L;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    public Usuario() {
    }

    public Usuario(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
        this.esAdmin = false;
        this.puntuacionGlobal = 0L;
        this.fechaRegistro = LocalDateTime.now();
    }

    public Integer getId() { return id; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getEsAdmin() { return esAdmin; }
    public void setEsAdmin(Boolean esAdmin) { this.esAdmin = esAdmin; }

    public Long getPuntuacionGlobal() { return puntuacionGlobal; }
    public void setPuntuacionGlobal(Long puntuacionGlobal) { this.puntuacionGlobal = puntuacionGlobal; }

     public void sumarPuntos(Long puntos) {
        this.puntuacionGlobal += puntos;
    }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
}