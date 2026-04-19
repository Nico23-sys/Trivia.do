package edu.nob.liceo.triviado_proyectofin_nicolasotero.dao;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Partida;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.HibernateUtil;
import org.hibernate.Session;

public class PartidaDAO {
    public void save(Partida partida) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(partida);
            session.getTransaction().commit();
        }
    }

    public void update(Partida partida) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(partida);
            session.getTransaction().commit();
        }
    }
}
