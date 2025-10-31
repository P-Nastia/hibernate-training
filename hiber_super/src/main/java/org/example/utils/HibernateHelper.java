package org.example.utils;

import org.example.entities.CategoryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;

public class HibernateHelper {
    // session factory - клас для роботи з hibernate
    private static SessionFactory sessionFactory;

    //буде викликатися автоматично при використанні даного класу
    // final = const
    static{
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .build();
        try{
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(CategoryEntity.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }catch (Exception e){
            System.out.println("Exception" + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session getSession(){
        return sessionFactory.openSession();
    }
    public static void shutdown(){
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }
}
