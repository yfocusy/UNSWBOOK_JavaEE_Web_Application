package com.unsw.Controller;

import com.unsw.Service.Implement.PostServiceImpl;

public class LikePost {
    PostServiceImpl postServiceImpl = new PostServiceImpl();
    public boolean likePost(int uid, int postid){
        return postServiceImpl.likeOnePostByUid(uid,postid);
    }
    public boolean unLikePost(int uid,int postid){
        return postServiceImpl.unlikeOnePostByUid(uid,postid);
    }
}
