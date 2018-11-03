package com.unsw.Service.Interface;

import com.unsw.Entity.Post;
import com.unsw.Entity.PostLikeOrUnlike;
import com.unsw.Entity.Users;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface PostService {
//    public boolean insertPost(Post post);
//    public List<Post> searchPostsByUid(int uid);
//
//    public boolean modifyPostByPostId(Post post);
//    public boolean addLikeOrUnlikeToOnePostByUid(int postId, int senderUid, int type);
//    public boolean deleteLikeOrUnlikeToOnePostByUid(int postId, int senderUid, int type);
//
//    public int checkLikeOrUnlikeToOnePostByUid(int postId, int senderUid);
//
//    List<PostLikeOrUnlike>  getLikeorUnlikeListByPostId(int postId, int type, int uid);


    // yuli
    public boolean insertPost(int uid,String content,String picSrc);
    public List<Post> searchPostsByUid(int uid);
    public Post searchPostsByPostid(int postid);
    public List<Post> getFriendPostsByFriendList( List<Users> friendList);
    public Boolean likeOnePostByUid(int uid, int postid);
    public Boolean unlikeOnePostByUid(int uid, int postid);
    public void UploadPhoto(String path, String saveName, File file) throws IOException;
    public List<Post> getAllPosts();

    // zhu to be 9.27
//    public List<PostLikeOrUnlike> getAllLikedOrUnlikeedPostsByUid(int uid,int type);


//     10.3 cc
    public ArrayList<Integer> getLikeList(List<PostLikeOrUnlike> list);
    public ArrayList<Integer> getUnlikeList(List<PostLikeOrUnlike> list);
    public List<Integer> getPostLikeOrUnlikeListByPostId(Integer postId, Integer type);

    // 10.14 bullying
    public Boolean checkBullying(String text,String stopWordsFile);

}

