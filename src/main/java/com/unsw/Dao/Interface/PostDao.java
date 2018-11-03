package com.unsw.Dao.Interface;

import com.unsw.Entity.Post;
import com.unsw.Entity.PostLikeOrUnlike;

import java.util.List;

public interface PostDao {
    public boolean insertPost(Post post);
    public List<Post> searchPostsByUid(int uid);
    public Post searchPostsByPostId(int postid);

    public boolean modifyPostByPostId(Post post);
    public boolean addLikeOrUnlikeToOnePostByUid(int postId, int senderUid, int type);
    public boolean deleteLikeOrUnlikeToOnePostByUid(int postId, int senderUid, int type);

    public int checkLikeOrUnlikeToOnePostByUid(int postId, int senderUid);

    List<PostLikeOrUnlike>  getLikeorUnlikeListByPostId(int postId, int type, int uid);


    // yuli1
    public List<Post> getAllPosts();
    public Post getPostByPostId(int postid);
//    public List<PostLikeOrUnlike> getAllLikeOrUnlikeRecordsByUid(int uid,int type);
    public List<PostLikeOrUnlike> getPostlikeorunlikeByPostId(Integer postId);
}
