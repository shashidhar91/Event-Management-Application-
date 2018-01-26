/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory createSessionFactory() {
    	
    	if(sessionFactory == null) {
    		
    		Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(com.model.EventModel.class);
            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	}
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return createSessionFactory();
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtil.sessionFactory = sessionFactory;
    }

    public static ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    public static void setServiceRegistry(ServiceRegistry serviceRegistry) {
        HibernateUtil.serviceRegistry = serviceRegistry;
    }
    
}