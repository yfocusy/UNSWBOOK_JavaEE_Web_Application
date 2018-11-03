package com.unsw.Service.Implement;

import com.unsw.Dao.Implement.AdminDaoImpl;
import com.unsw.Dao.Implement.FriendDaoImpl;
import com.unsw.Dao.Interface.FriendDao;
import com.unsw.Dao.MySessionFactory;
import com.unsw.Entity.Friend;
import com.unsw.Entity.FriendApply;
import com.unsw.Entity.Post;
import com.unsw.Entity.Users;
import com.unsw.Service.Interface.AdminService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    SessionFactory sessionFactory = MySessionFactory.getSessionFactory();

    public Timestamp returnDateJoin(int uid) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        String hql = "from Users where uid = ?";
//        Query query = session.createQuery(hql);
//        query = query.setParameter(0,uid);
//        List<Users> list = query.list();
        //transaction.commit();
        Users user = new UsersServiceImpl().findUserByUid(uid);
        return user.getRegisterDate();
    }

    public List<Friend> returnAddFriend(int uid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Friend where user1Uid = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,uid);
        List<Friend> list = query.list();

        hql = "from Friend where user2Uid = ?";
        query = session.createQuery(hql);
        query = query.setParameter(0,uid);
        list.addAll( query.list());

        //transaction.commit();
        //Users user = list.get(0);
        return list;
    }

    public List<Post> returnPosts(int uid) {
        return new PostServiceImpl().searchPostsByUid(uid);
    }

    public boolean banUser(int uid) {
        new AdminDaoImpl().banUser(uid);
        return false;
    }

    public List<Users> returnAllUsers() {
        return new UsersServiceImpl().findAllUsers();
    }

    public List<Post> returnAllPosts() {
        return new PostServiceImpl().getAllPosts();
    }

    public HashMap timeline(int uid) {
        Timestamp timestamp = returnDateJoin(uid);
        List<Post> userPosts = returnPosts(uid);
        List<Friend> friendApplies = returnAddFriend(uid);
        HashMap timLine = new HashMap();
        timLine.put(0,timestamp);
        //List<String> tLine = new ArrayList<String>();
        //tLine.add(timestamp.toString());
        int i =1;
        while(!userPosts.isEmpty()&&!friendApplies.isEmpty()){
            if(friendApplies.get(0).getTime().after(userPosts.get(0).getPostime())){
                timLine.put(i,userPosts.get(0));
                //tLine.add("post "+userPosts.get(0).getPostId());
                userPosts.remove(0);
            }
            else{
                timLine.put(i,friendApplies.get(0));
                //tLine.add("friend "+friendApplies.get(0).getFriendshipId());
                friendApplies.remove(0);
            }
            i++;
        }
        while (!userPosts.isEmpty()){
            timLine.put(i,userPosts.get(0));
            //tLine.add("post "+userPosts.get(0).getPostId());
            userPosts.remove(0);
            i++;
        }
        while(!friendApplies.isEmpty()){
            timLine.put(i,friendApplies.get(0));
            //tLine.add("friend "+friendApplies.get(0).getFriendshipId());
            friendApplies.remove(0);
            i++;
        }
        //return tLine;
        return timLine;
    }

    public static void main(String[] args) {
        new AdminServiceImpl().timeline(1);
    }

    public String returnTimeLineType(String s){
        return s.split(" ")[0];
    }

    public String returnTimeLineId(String s){
        return s.split(" ")[1];
    }

}
