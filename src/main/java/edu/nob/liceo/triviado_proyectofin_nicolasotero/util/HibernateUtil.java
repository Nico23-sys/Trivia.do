package edu.nob.liceo.triviado_proyectofin_nicolasotero.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            configuration.setProperty("hibernate.connection.url", ConfigLoader.get("db.url"));
            configuration.setProperty("hibernate.connection.username", ConfigLoader.get("db.user"));
            configuration.setProperty("hibernate.connection.password", ConfigLoader.get("db.password"));

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Fallo al inicializar el SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
