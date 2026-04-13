package edu.nob.liceo.triviado_proyectofin_nicolasotero.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "preguntas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pregunta", discriminatorType = DiscriminatorType.STRING)
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 500, nullable = false)
    private String enunciado;

    @Column(nullable = false)
    private String correcta;

    @Column(nullable = false)
    private String falsa1;

    @Column(nullable = false)
    private String falsa2;

    @Column(nullable = false)
    private String categoria;

    // Constructor vacío (Obligatorio para Hibernate)
    public Pregunta() {}

    // Constructor completo
    public Pregunta(String enunciado, String correcta, String falsa1, String falsa2, String categoria) {
        this.enunciado = enunciado;
        this.correcta = correcta;
        this.falsa1 = falsa1;
        this.falsa2 = falsa2;
        this.categoria = categoria;
    }

    public Integer getId() { return id; }

    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }

    public String getCorrecta() { return correcta; }
    public void setCorrecta(String correcta) { this.correcta = correcta; }

    public String getFalsa1() { return falsa1; }
    public void setFalsa1(String falsa1) { this.falsa1 = falsa1; }

    public String getFalsa2() { return falsa2; }
    public void setFalsa2(String falsa2) { this.falsa2 = falsa2; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}