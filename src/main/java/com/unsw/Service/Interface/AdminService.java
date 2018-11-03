package com.unsw.Service.Interface;

import com.unsw.Dao.Interface.FriendDao;
import com.unsw.Entity.Friend;
import com.unsw.Entity.Post;
import com.unsw.Entity.Users;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public interface AdminService {
    public Timestamp returnDateJoin(int uid);
    public List<Friend> returnAddFriend(int uid);
    public List<Post> returnPosts(int uid);
    public boolean banUser(int uid);
    public List<Users> returnAllUsers();
    public List<Post> returnAllPosts();
    public HashMap timeline(int uid);
}
