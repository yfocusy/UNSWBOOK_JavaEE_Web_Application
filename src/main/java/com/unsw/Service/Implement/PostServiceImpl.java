package com.unsw.Service.Implement;

import com.unsw.Dao.Implement.FriendDaoImpl;
import com.unsw.Dao.Implement.PostDaoImpl;
import com.unsw.Dao.Implement.UsersDaoImpl;
import com.unsw.Dao.Interface.FriendDao;
import com.unsw.Dao.Interface.PostDao;
import com.unsw.Dao.Interface.UsersDao;
import com.unsw.Entity.BullyingWords;
import com.unsw.Entity.Post;
import com.unsw.Entity.PostLikeOrUnlike;
import com.unsw.Entity.Users;
import com.unsw.Service.Interface.PostService;
import unsw.curation.api.tokenization.ExtractionKeywordImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostServiceImpl implements PostService{
    private PostDao postDao = new PostDaoImpl();
    private UsersDao usersDao = new UsersDaoImpl();

    public boolean insertPost(int uid,String content,String picSrc) {
        Post post = new Post();
        post.setUid(uid);
        post.setContent(content);
        post.setImageNum(picSrc);
        post.setPostime(new Timestamp(new Date().getTime()));
//      me. 10.4
        post.setLikeNum(0);
        post.setUnlikeNum(0);
        post.setCommentNum(0);

        new PostDaoImpl().insertPost(post);
        return true;
    }

    public List<Post> searchPostsByUid(int uid){
        List<Post> postList =  postDao.searchPostsByUid(uid);
        return postList;
    }

    public Post searchPostsByPostid(int postid) {
        Post post =  postDao.searchPostsByPostId(postid);
        return post;
    }

    // yuli
    public List<Post> getFriendPostsByFriendList(List<Users> friendList){

        System.out.println("----- 8. PostServiceImpl --- getFriendPostsByFriendList");
        // 1. 取得所有的post，删除非好友的post，就是排好顺序的post
        List<Post> allPostsList = postDao.getAllPosts();
        List<Post> friendPostsList = new ArrayList<Post>();
        System.out.println("allPostsList ="+allPostsList);

        if (allPostsList!=null){
            for (Post post: allPostsList){
                Users fUser = usersDao.findUserByUid(post.getUid());
                if(friendList.contains(fUser)){
                    friendPostsList.add(0,post);
                }
            }
        }
        if (friendPostsList ==null || friendPostsList.size()==0){
            return null;
        }
        return friendPostsList;
    }

    public Boolean likeOnePostByUid(int uid, int postid){
        int type = postDao.checkLikeOrUnlikeToOnePostByUid(postid, uid);
        Post post = postDao.getPostByPostId(postid);
        if (type == 0){
            //1. 原来unlike，现在要改like。
            // 改like数字，update到post里
            post.setUnlikeNum(post.getUnlikeNum() - 1 );
            post.setLikeNum(post.getLikeNum() + 1 );
            postDao.modifyPostByPostId(post);

            //2. 改likeUnlike的表
            postDao.deleteLikeOrUnlikeToOnePostByUid(postid,uid,0);
            postDao.addLikeOrUnlikeToOnePostByUid(postid,uid,1);
            return true;
        }else if (type == 1){
            return false;

        }else{// 没like 也没unlike
            //1. update post + like
            post.setLikeNum(post.getLikeNum() + 1 );
            postDao.modifyPostByPostId(post);
            postDao.addLikeOrUnlikeToOnePostByUid(postid,uid,1);
            return true;
        }
    }

    public Boolean unlikeOnePostByUid(int uid, int postid){
        int type = postDao.checkLikeOrUnlikeToOnePostByUid(postid, uid);
        Post post = postDao.getPostByPostId(postid);
        if (type == 1){
            //1. 原来unlike，现在要改like。
            // 改like数字，update到post里
            post.setUnlikeNum(post.getUnlikeNum() + 1 );
            post.setLikeNum(post.getLikeNum() - 1 );
            postDao.modifyPostByPostId(post);

            //2. 改likeUnlike的表
            postDao.deleteLikeOrUnlikeToOnePostByUid(postid,uid,1);
            postDao.addLikeOrUnlikeToOnePostByUid(postid,uid,0);
            return true;
        }else if (type == 0){
            return false;

        }else{// 没like 也没unlike
            //1. update post + like
            post.setUnlikeNum(post.getUnlikeNum() + 1 );
            postDao.modifyPostByPostId(post);
            postDao.addLikeOrUnlikeToOnePostByUid(postid,uid,0);
            return true;
        }
    }

    public void UploadPhoto(String uploadPath, String saveName, File file) throws IOException {
        if(!(new File(uploadPath)).exists()){
            new File(uploadPath).mkdirs();
        }
        File saveFile = new File(uploadPath,saveName);
        FileOutputStream fos = null;
        FileInputStream fis = new FileInputStream(new File("/photos/saveName"));
        try{
            fos = new FileOutputStream(saveFile);
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = fis.read(buffer))>0 ){
                fos.write(buffer,0,length);
            }
        }catch (Exception e){
            e.printStackTrace();

        }finally{
            if (fis!=null){
                fis.close();
            }
            if (fos!=null){
                fos.close();
            }
        }
    }

    public List<Post> getAllPosts() {
        return new PostDaoImpl().getAllPosts();
    }

    // zhu to be 9.27
//    public List<PostLikeOrUnlike> getAllLikedOrUnlikeedPostsByUid(int uid,int type){
//
//        List<PostLikeOrUnlike> likedList = postDao.getAllLikeOrUnlikeRecords(type);
//
//        for (Post p : likedList){
//            if (p.getUid() == uid){
//
//            }
//        }
//
//
//    }
// 10.3 cc
    public ArrayList<Integer> getLikeList(List<PostLikeOrUnlike> list) {
        ArrayList<Integer> likeOrUnlikeList = new ArrayList<Integer>();
        for (PostLikeOrUnlike plol:list) {
            if(plol.getType() == 1) {
                likeOrUnlikeList.add(plol.getUid());
            }
        }
        if (likeOrUnlikeList.size()==0) return null;
        return likeOrUnlikeList;
    }

    public ArrayList<Integer> getUnlikeList(List<PostLikeOrUnlike> list) {
        ArrayList<Integer> likeOrUnlikeList = new ArrayList<Integer>();
        for (PostLikeOrUnlike plol:list) {
            if(plol.getType() == 0) {
                likeOrUnlikeList.add(plol.getUid());
            }
        }
        if (likeOrUnlikeList.size()==0) return null;
        return likeOrUnlikeList;
    }
// 10.3 gai cc
    public List<Integer> getPostLikeOrUnlikeListByPostId(Integer postId, Integer type) {
//    public List<PostLikeOrUnlike> getPostLikeListByPostId(Integer postId) {
//1. 把一条post下的like ulike 都取出来
        List<PostLikeOrUnlike> ListOfOnePost = postDao.getPostlikeorunlikeByPostId(postId);
        List<Integer> allUsersList = new ArrayList<Integer>();
        for (PostLikeOrUnlike p : ListOfOnePost) {
            if (type == p.getType()) {
                allUsersList.add(p.getUid());
            }
        }
        return allUsersList;
    }


    // 10.14 bullying
    public Boolean checkBullying(String text, String stopWordsFile){
        if(text !=null){
            ExtractionKeywordImpl ek = new ExtractionKeywordImpl();
            try {
                String extractKeywordsString = ek.ExtractSentenceKeyword(text, new File(stopWordsFile));
                System.out.println("--in checkBullying serviceImpl----");
                System.out.println("extractKeywordsString="+extractKeywordsString);

                BullyingWords bullyingWords =  new BullyingWords();
                List<String> bullyingList = bullyingWords.getBullyingList();
//                extractKeywordsString = extractKeywordsString.substring(1,extractKeywordsString.length()-1).toLowerCase();
                extractKeywordsString = extractKeywordsString.toLowerCase();
                String[] tmplist = extractKeywordsString.split(",");
                List<String> keyWordsList = java.util.Arrays.asList(tmplist);
                System.out.println("keyWordsList="+keyWordsList);
                for (String str: keyWordsList){
                    if(bullyingList.contains(str)){
                        System.out.println("Bullying str="+str);
                        return true;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

}// end of class
