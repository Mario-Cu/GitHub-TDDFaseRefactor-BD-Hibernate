package uva.tds.practica2_grupo8;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 * Clase de utilidad de Hibernate
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	 

	private HibernateUtil() {
		throw new IllegalStateException("Utility class");
	}

    private static SessionFactory buildSessionFactory() {
    	// A SessionFactory is set up once for an application!
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    			.configure() // configures settings from hibernate.cfg.xml
    			.build();
    	try {
    		return new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    	}
    	catch (Exception e) {
    		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
    		// so destroy it manually.
    		StandardServiceRegistryBuilder.destroy( registry );
    		throw e;
    	}
    }
    
    /**
     * Metodo que devuelve el sessionFactory
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    /**
     * Metodo que cierra los caches y las connection pools
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}