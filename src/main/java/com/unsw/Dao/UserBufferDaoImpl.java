package com.unsw.Dao;

import com.unsw.Entity.FriendApply;
import com.unsw.Entity.Users;
import com.unsw.Entity.UsersBuffer;
import com.unsw.Dao.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserBufferDaoImpl {

    public void insertUsersBuffer(UsersBuffer usersBuffer) {
        SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(usersBuffer);
        transaction.commit();
        session.close();
    }

    public void delUsersBuffer(UsersBuffer usersBuffer) {
        SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(usersBuffer);
        transaction.commit();
        session.close();
    }

    public List<UsersBuffer> getUserBufferByUserNameAndCode(String userName,String vcode) {
        SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from UsersBuffer where username = ? and verification = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,userName);
        query = query.setParameter(1,vcode);
        List<UsersBuffer> list = query.list();
        session.close();
        return list;
    }
}
