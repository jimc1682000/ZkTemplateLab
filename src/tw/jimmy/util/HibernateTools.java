package tw.jimmy.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateTools {
	private static StandardServiceRegistry registry;
	private static SessionFactory factory;

	static {
		try {
			Configuration config = new Configuration().configure("/hibernate.cfg.xml");
			registry = new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties()).build();
			factory = config.buildSessionFactory(registry);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}

	public static void shutdown() {
		getSessionFactory().close();
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
