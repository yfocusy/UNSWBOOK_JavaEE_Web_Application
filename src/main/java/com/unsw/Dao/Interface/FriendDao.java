package com.unsw.Dao.Interface;

import com.unsw.Entity.Friend;
import com.unsw.Entity.FriendApply;
import com.unsw.Entity.Users;

import java.util.List;

public interface FriendDao {


    public List<FriendApply> getFriendApplyByReceiver(int receiverUid);
    public List<FriendApply> getFriendApplyBySender(int senderUid);

    public void addFriendApply(int senderUid, int receiverUid);
    public void deleteFriendApply(int senderUid, int receiverUid);
    public List<Users> getAllFriendsByUid(int uid);
    public boolean checkFriendShipByUid(int uid1, int uid2);
    public void addFriend(int uid1, int uid2);

    public Friend findFriendByfriendshipId(int friendshipId);
    //YULI
    public boolean checkApplysByUid(int senderUid,int receiverUid);
    public void deleteFriend(int senderUid, int receiverUid);
}
