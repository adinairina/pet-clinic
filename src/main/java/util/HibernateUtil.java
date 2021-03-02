package util;


import model.Consult;
import model.Pet;
import model.Veterinarian;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");              //vezi ca baza de date e de tip MySQL
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/pet_clinic?serverTimezone=UTC");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Bomboane1213");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");   //prin dialect ii spunem ce versiune de mySQL vrem sa foloseasca
                settings.put(Environment.SHOW_SQL, "true");             //sa fie afisate queri-urile
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");           //o proprietate importanta - prin ea putem sa ajungem sa ne dam baza de date peste cap
                //cum sa interactioneze entitatile  noastre din Java cu baza de date
                //putem sa-i spunem sa stearga tabelele dupa fiecare utilizare
                //create-drop - se va uita tot timpul peste entitati si mapari si va crea in vbaza de date respectivele tabele

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Consult.class);
                configuration.addAnnotatedClass(Pet.class);   //legatura cu baza de date
                configuration.addAnnotatedClass(Veterinarian.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
