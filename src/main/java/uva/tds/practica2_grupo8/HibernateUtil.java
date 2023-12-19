package uva.tds.practica2_grupo8;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 * Clase de utilidad de Hibernate
 */
public class HibernateUtil {

	
	private HibernateUtil() {
		    throw new IllegalStateException("Utility class");
    }
	  
	private static final SessionFactory sessionFactory = buildSessionFactory();
	 


    private static SessionFactory buildSessionFactory() {
    	// A SessionFactory is set up once for an application!
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    			.configure() // configures settings from hibernate.cfg.xml
    			.build();
    	return new MetadataSources( registry ).buildMetadata().buildSessionFactory();

    }
    
    /**
     * Metodo que devuelve el sessionFactory
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
}