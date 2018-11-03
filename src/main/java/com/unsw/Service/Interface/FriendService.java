package com.unsw.Service.Interface;

import com.unsw.Entity.Friend;
import com.unsw.Entity.FriendApply;
import com.unsw.Entity.Users;

import java.util.List;

public interface FriendService


{
    public boolean addFriendApply(int uid1,int uid2);
    public boolean addFriend(int uid1,int uid2);
    public List<Users> showAllFriends(int uid);
    public List<FriendApply> getFriendApplyByReceiver(int uid);
    public List<FriendApply> getFriendApplyBySender(int uid);
    public boolean checkFriends(int uid,List<Users> friends);

    public Friend findFriendByFriendshipId(int friendShipId);

    public boolean checkApplys(int senderUid,int receiverUid);
    public boolean  deleteFriend(int uid1, int uid2);
}
