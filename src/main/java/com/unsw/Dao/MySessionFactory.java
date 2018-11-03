package com.unsw.Dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


final public class MySessionFactory {
    private static SessionFactory sessionFactory= null;
    public MySessionFactory() {}
    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    public static SessionFactory getSessionFactory() {return sessionFactory;}
}
