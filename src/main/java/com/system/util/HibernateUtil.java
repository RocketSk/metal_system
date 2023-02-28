package com.system.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public final class HibernateUtil {

    private HibernateUtil() {

    }

    private static StandardServiceRegistry registry;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                final MetadataSources sources = new MetadataSources(registry);
                final Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (RuntimeException exception) {
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                exception.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
