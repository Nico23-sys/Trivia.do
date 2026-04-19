package edu.nob.liceo.triviado_proyectofin_nicolasotero.dao;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.JugadaDetalle;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.util.HibernateUtil;
import org.hibernate.Session;

public class JugadaDetalleDAO {
    public void save(JugadaDetalle jugada) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(jugada);
            session.getTransaction().commit();
        }
    }
}
