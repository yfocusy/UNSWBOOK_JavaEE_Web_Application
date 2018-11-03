package com.unsw.Dao.Implement;

import com.unsw.Dao.MySessionFactory;
import com.unsw.Entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class UsersDaoImpl implements com.unsw.Dao.Interface.UsersDao {
    SessionFactory sessionFactory = MySessionFactory.getSessionFactory();

    public void insertUsers(Users users) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(users);
        transaction.commit();
        session.close();
    }

    public Users findUserByUid(int uid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Users where uid = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,uid);
        List<Users> list = query.list();
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list.get(0);
    }

    public void updateUser(Users users) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Users where uid = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,users.getUid());
        List<Users> list = query.list();
        Users users1 = list.get(0);
        users1.setName(users.getName());
        users1.setPassword(users.getPassword());
        users1.setEmail(users.getEmail());
        users1.setBirthday(users.getBirthday());
        users1.setGender(users.getGender());
        users1.setHeadphotoPath(users.getHeadphotoPath());
        users1.setLoginDate(users.getLoginDate());
        users1.setRegisterDate(users.getRegisterDate());
        session.update(users1);
        transaction.commit();
        session.close();
    }

    public Users findUserByUsername(String userName) {
        //SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Users where username = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,userName);
        List<Users> list = query.list();
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list.get(0);
    }

    public List<Users> findUserByName(String name){
        //SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Users where name = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,name);
        List<Users> list = query.list();
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list;
    }

    public List<Users> findUserByGender(String gender){
        //SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Users where gender = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,gender);
        List<Users> list = query.list();
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list;
    }

    // yuli3
    public List<Users> findUserByBirthday(Date birthday){
        //SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Users where birthday = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,birthday);
        List<Users> list = query.list();
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list;
    }
    // yuli3
    public List<Users> findAllUser(){
        //SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Users";
        Query query = session.createQuery(hql);
//        query = query.setParameter(0,birthday);
        List<Users> list = query.list();
        System.out.println("--- findAllUser list=" + list);
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list;
    }

}
