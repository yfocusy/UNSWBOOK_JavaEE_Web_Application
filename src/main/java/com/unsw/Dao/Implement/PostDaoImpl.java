package com.unsw.Dao.Implement;

import com.unsw.Dao.Interface.PostDao;
import com.unsw.Dao.MySessionFactory;
import com.unsw.Entity.FriendApply;
import com.unsw.Entity.Post;
import com.unsw.Entity.PostLikeOrUnlike;
import com.unsw.Entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao {
    SessionFactory sessionFactory = MySessionFactory.getSessionFactory();

//    public static void main(String[] args) {
//        Post post = new Post();
//        post.setUnlikeNum(0);
//        post.setLikeNum(0);
//        post.setImageNum(0);
//        post.setContent("sss");
//        post.setUid(1);
//        post.setCommentNum(0);
//        Date date = new Date(new  java.util.Date().getTime());
//        post.setPostime(new Timestamp(date.getTime()));
//        //new PostDaoImpl().insertPost(post);
//        //System.out.println(new PostDaoImpl().searchPostsByUid(post.getUid()));
//        //new PostDaoImpl().addLikeOrUnlikeToOnePostByUid(1,1,1);
//        //new PostDaoImpl().deleteLikeOrUnlikeToOnePostByUid(1,1,1);
//        //new PostDaoImpl().addLikeOrUnlikeToOnePostByUid(1,1,1);
//        List<PostLikeOrUnlike> postLikeOrUnlikes = new PostDaoImpl().getLikeorUnlikeListByPostId(1,1,-1);
//        System.out.println(new PostDaoImpl().checkLikeOrUnlikeToOnePostByUid(1,1));

    //}

    public boolean insertPost(Post post) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(post);
        transaction.commit();
        session.close();

        return true;
    }

    public List<Post> searchPostsByUid(int uid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Post where uid = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,uid);
        List<Post> list = query.list();
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list;
    }

    public Post searchPostsByPostId(int postid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Post where postId = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,postid);
        List<Post> list = query.list();
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list.get(0);
    }

    public boolean modifyPostByPostId(Post post) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Post where postId = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,post.getPostId());
        List<Post> list = query.list();
        //transaction.commit();
        Post post1 = list.get(0);
        post1.setLikeNum(post.getLikeNum());
        post1.setUnlikeNum(post.getUnlikeNum());
        session.update(post1);
        transaction.commit();
        session.close();
        return true;

    }

    public boolean addLikeOrUnlikeToOnePostByUid(int postId, int senderUid, int type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PostLikeOrUnlike postLikeOrUnlike = new PostLikeOrUnlike();

        postLikeOrUnlike.setPostId(postId);
        Date date = new Date(new  java.util.Date().getTime());
        postLikeOrUnlike.setSendTime(new Timestamp(date.getTime()));
        postLikeOrUnlike.setType(type);
        postLikeOrUnlike.setUid(senderUid);

        session.save(postLikeOrUnlike);
        transaction.commit();
        session.close();

        return true;
    }

    public boolean deleteLikeOrUnlikeToOnePostByUid(int postId, int senderUid, int type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from PostLikeOrUnlike where postId = ? and uid= ? and type=?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,postId);
        query = query.setParameter(1,senderUid);
        query = query.setParameter(2,type);
        List<FriendApply> friendApplyList = query.list();
        session.delete(friendApplyList.get(0));
        transaction.commit();
        session.close();
        return true;
    }

    public int checkLikeOrUnlikeToOnePostByUid(int postId, int senderUid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from PostLikeOrUnlike where postId = ? and uid= ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,postId);
        query = query.setParameter(1,senderUid);
        List<PostLikeOrUnlike> postLikeOrUnlikes = query.list();
        int type = -1;
        if (postLikeOrUnlikes.size()>0){
            type = postLikeOrUnlikes.get(0).getType();}
        transaction.commit();
        session.close();
        return type;
    }

    public List<PostLikeOrUnlike> getLikeorUnlikeListByPostId(int postId, int type, int uid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (uid==-1){
            String hql = "from PostLikeOrUnlike where postId = ?";
            Query query = session.createQuery(hql);
            query = query.setParameter(0,postId);
            List<PostLikeOrUnlike> postLikeOrUnlikes = query.list();
            transaction.commit();
            session.close();
            return postLikeOrUnlikes;
        }
        else{
            String hql = "from PostLikeOrUnlike where postId = ? and type=?";
            Query query = session.createQuery(hql);
            query = query.setParameter(0,postId);
            query = query.setParameter(1,type);
            List<PostLikeOrUnlike> postLikeOrUnlikes = query.list();
            transaction.commit();
            session.close();
            return postLikeOrUnlikes;
        }
    }

    // yuli 1
    public List<Post> getAllPosts(){
            //SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Post";
            Query query = session.createQuery(hql);
            List<Post> list = query.list();
            System.out.println("--- findAllPost list=" + list);
            transaction.commit();
            session.close();
            if (list.size()==0){
                return null;
        }
        return list;
    }

    // yuli2
    public Post getPostByPostId(int postid){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Post where postId = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,postid);
        List<Post> list = query.list();
        transaction.commit();
        session.close();
        if (list.size()==0){
            return null;
        }
        return list.get(0);
    }


//    // zhu to be
//    public List<PostLikeOrUnlike> getAllLikeOrUnlikeRecordsByUid(int uid,int type){
//        List<PostLikeOrUnlike> likedList ;
//    }

// 10.3 cc
    public List<PostLikeOrUnlike> getPostlikeorunlikeByPostId(Integer postId) {
        SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from PostLikeOrUnlike where postId = ?";
        Query query = session.createQuery(hql);
        query = query.setParameter(0,postId);
        List<PostLikeOrUnlike> list = query.list();
        session.close();
        return list;
    }



}
