package edu.nob.liceo.triviado_proyectofin_nicolasotero.dao;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Pregunta;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class PreguntaDAO {
    // Num de preguntas aleatorias partida
    public List<Pregunta> obtenerPreguntasAleatorias(int cantidad) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // El ORDER BY RAND() sirve para ordenar los resultados de una consulta de forma completamente aleatoria.
            return session.createQuery("FROM Pregunta ORDER BY rand()", Pregunta.class)
                    .setMaxResults(cantidad)
                    .list();
        }
    }

    public void save(Pregunta pregunta) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(pregunta);
            session.getTransaction().commit();
        }
    }
}
