package com.unsw.Dao.Implement;

import com.unsw.Dao.Interface.FriendDao;
import com.unsw.Dao.MySessionFactory;
import com.unsw.Entity.Friend;
import com.unsw.Entity.FriendApply;
import com.unsw.Entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FriendDaoImpl implements FriendDao{
    SessionFactory sessionFactory = MySessionFactory.getSessionFactory();

    public static void main(String[] args) {
 //       List<Users> a= new FriendDaoImpl().getFriendApplyByReceiver(2);
  //      new FriendDaoImpl().addFriendApply(1,3);
  //      new FriendDaoImpl().deleteFriendApply(1,3);
  //      new FriendDaoImpl().getAllFriendsByUid(1);
  //      System.out.println(new FriendDaoImpl().checkFriendShipByUid(1,2));
  //      System.out.println(new FriendDaoImpl().checkFriendShipByUid(1,3));
        new FriendDaoImpl().addFriend(1,3);
        System.out.println("xxx");
    }



    // yuli 改
//    public List<Users> getFriendApplyByReceiver(int receiverUid) {
    public List<FriendApply> getFriendApplyByReceiver(int receiverUid) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from FriendApply where receiverUid = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,receiverUid);
        List<FriendApply> list = query.list();
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list;
    }

    public List<FriendApply> getFriendApplyBySender(int senderUid) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from FriendApply where senderUid = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,senderUid);
        List<FriendApply> list = query.list();
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list;
    }

    public void addFriendApply(int senderUid, int receiverUid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FriendApply friendApply = new FriendApply();
        friendApply.setSenderUid(senderUid);
        friendApply.setReceiverUid(receiverUid);

        Date date = new Date(new  java.util.Date().getTime());
        friendApply.setTime(date);

        session.save(friendApply);
        transaction.commit();
        session.close();
    }

    public void deleteFriendApply(int senderUid, int receiverUid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from FriendApply where senderUid = ? and receiverUid= ? ";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,senderUid);
        query = query.setParameter(1,receiverUid);
        List<FriendApply> friendApplyList = query.list();

        session.delete(friendApplyList.get(0));
        transaction.commit();
        session.close();
    }

    public List<Users> getAllFriendsByUid(int uid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "select user2Uid from Friend where user1Uid = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,uid);
        List list = query.list();
        String hql2 = "select user1Uid from Friend where user2Uid = ?";
        Query query2 = session.createQuery(hql2);
        query2 = query2.setParameter(0,uid);
        List list2 = query2.list();
        list.addAll(list2);
        //list2.clear();

        List<Users> listUsers = new ArrayList<Users>();
        for(Iterator iterator = list.iterator(); iterator.hasNext();){
            int i =  Integer.parseInt(iterator.next().toString());
            String hql3 = "from Users where uid = ?";
            Query query3 = session.createQuery(hql3);
            query3 = query3.setParameter(0,i);
            List<Users> list3 = query3.list();
            listUsers.addAll(list3);
        }

        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        System.out.println("friendDaoImpl=> friendlist ="+listUsers);
        System.out.println("friendDaoImpl=> friendlist ="+listUsers.get(0).getUid());
        return listUsers;
    }

    public boolean checkFriendShipByUid(int uid1, int uid2) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Friend where user1Uid = ? and user2Uid = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,uid1);
        query = query.setParameter(1,uid2);
        List<Users> list = query.list();
        //String hql2 = "from Friend where user2Uid = ?";
        query = query.setParameter(1,uid1);
        query = query.setParameter(0,uid2);
        List<Users> list2 = query.list();
        list.addAll(list2);
        transaction.commit();
        session.close();
        if (list.size()==0){
            return false;
        }
        return true;
    }

    public void addFriend(int uid1, int uid2) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Friend friend = new Friend();
        friend.setUser1Uid(uid1);
        friend.setUser2Uid(uid2);
        Date date = new Date(new  java.util.Date().getTime());
        friend.setTime(date);
        session.save(friend);
        transaction.commit();
        session.close();
    }

    public Friend findFriendByfriendshipId(int friendshipId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Friend where friendshipId = ? ";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,friendshipId);
        List<Friend> friendShipList = query.list();
        transaction.commit();
        session.close();
        return friendShipList.get(0);
    }

    //YULI1
    public boolean checkApplysByUid(int senderUid,int receiverUid){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from FriendApply where senderUid = ? and receiverUid = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,senderUid);
        query = query.setParameter(1,receiverUid);
        List<Users> list = query.list();
        //String hql2 = "from Friend where user2Uid = ?";
        query = query.setParameter(1,senderUid);
        query = query.setParameter(0,receiverUid);
        List<Users> list2 = query.list();
        list.addAll(list2);
        transaction.commit();
        session.close();
        if (list.size()==0){
            return false;
        }
        return true;
    }

    // yuli
    public void deleteFriend(int senderUid, int receiverUid) {
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Friend where  user1Uid= ? and user2Uid= ? ";
            Query query = session.createQuery(hql);
            query = query.setParameter(0,senderUid);
            query = query.setParameter(1,receiverUid);
            List<Friend> friendList = query.list();

            session.delete(friendList.get(0));
            transaction.commit();
            session.close();
        }catch(Exception e){
            System.out.println("Fdao=> delFriend =>Exception ");
        }
    }


}
