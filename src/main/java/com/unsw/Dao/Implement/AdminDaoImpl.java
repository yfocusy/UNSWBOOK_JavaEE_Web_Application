package com.unsw.Dao.Implement;

import com.unsw.Dao.Interface.AdminDao;
import com.unsw.Dao.MySessionFactory;
import com.unsw.Entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AdminDaoImpl implements AdminDao {
    SessionFactory sessionFactory = MySessionFactory.getSessionFactory();

    public static void main(String[] args) {
        new AdminDaoImpl().banUser(1);
    }
    public boolean banUser(int uid) {
        Users user = new UsersDaoImpl().findUserByUid(uid);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (user.getBan()==1){
            user.setBan(0);}
        else{
            user.setBan(1);
        }
        session.update(user);
        //session.save(user);
        transaction.commit();
        session.close();
        return true;
    }
}
