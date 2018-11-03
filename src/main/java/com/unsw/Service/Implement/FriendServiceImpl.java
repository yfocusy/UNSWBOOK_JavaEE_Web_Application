package com.unsw.Service.Implement;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.unsw.Dao.Implement.FriendDaoImpl;
import com.unsw.Dao.Implement.UsersDaoImpl;
import com.unsw.Dao.Interface.FriendDao;
import com.unsw.Dao.Interface.UsersDao;
import com.unsw.Entity.Friend;
import com.unsw.Entity.FriendApply;
import com.unsw.Entity.Users;
import com.unsw.Service.Interface.FriendService;
import com.unsw.Service.Interface.SearchService;
import com.unsw.Service.Interface.UsersService;

import java.text.ParseException;
import java.util.*;

public class FriendServiceImpl implements FriendService{

    private UsersDao usersDao = new UsersDaoImpl();
    private FriendDao friendDaoImpl = new FriendDaoImpl();

    // yuli 改的 getFriendApplyByReceiver2，返回friendApply类型
    public boolean addFriendApply(int sendUid, int receiveUid) {
        Boolean friendshipFlag = friendDaoImpl.checkFriendShipByUid(sendUid,receiveUid);
        if (friendshipFlag==false){
            // 没有朋友关系，去验证apply表里是不是apply过的记录
            List<FriendApply> applyList= friendDaoImpl.getFriendApplyByReceiver(receiveUid);
            if (applyList!=null && applyList.size()>0){
                for (FriendApply apply: applyList){
                    // 发送过apply
                    if (apply.getReceiverUid()==receiveUid && apply.getSenderUid() == sendUid){
                        return false;
                    }
                    else{ // 没发送过apply
                        friendDaoImpl.addFriendApply(sendUid,receiveUid);
                        return true;
                    }
                }
            }else { // applylist 是null，没发送过apply
                friendDaoImpl.addFriendApply(sendUid,receiveUid);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean addFriend(int uid1, int uid2) {
        if (uid1==uid2){
            return false;
        }
        if (friendDaoImpl.checkFriendShipByUid(uid1,uid2))
            return false;
        if (friendDaoImpl.checkApplysByUid(uid2,uid1))
        {
            friendDaoImpl.deleteFriendApply(uid2,uid1);
            friendDaoImpl.addFriend(uid2,uid1);
        }

//        friendDaoImpl.deleteFriendApply(uid1,uid2);
//        friendDaoImpl.addFriend(uid1,uid2);
        return true;
    }
    // yuli 1
    public List<Users> showAllFriends(int uid) {
        System.out.println();
        System.out.println("--------5  in FriendService ------------");
        List<Users> friends = friendDaoImpl.getAllFriendsByUid(uid);
        System.out.println("friends     ="+friends);


        return friends;
    }

    public List<FriendApply> getFriendApplyByReceiver(int uid) {
        List<FriendApply> friendApplies = friendDaoImpl.getFriendApplyByReceiver(uid);
        return friendApplies;
    }

    public List<FriendApply> getFriendApplyBySender(int uid){
        List<FriendApply> friendApplies = friendDaoImpl.getFriendApplyBySender(uid);
        return friendApplies;
    }

    public boolean checkFriends(int uid, List<Users> friends) {
        for(Iterator iterator = friends.iterator(); iterator.hasNext();){
            Users i = (Users) iterator.next();
            if (i.getUid()==uid){
                return false;
            }
        }
        return true;
    }

    public Friend findFriendByFriendshipId(int friendShipId) {

        return null;
    }


    public boolean checkApplys(int senderUid,int receiverUid){
        Boolean ApplyCheck = friendDaoImpl.checkApplysByUid(senderUid,receiverUid);
        return ApplyCheck;
    }

    public boolean  deleteFriend(int uid1, int uid2){

        if (uid1==uid2){
            return false;
        }
        if (friendDaoImpl.checkFriendShipByUid(uid1,uid2)){
            friendDaoImpl.deleteFriend(uid1, uid2);
            if (friendDaoImpl.checkFriendShipByUid(uid2,uid1)) {
                friendDaoImpl.deleteFriend(uid2, uid1);
                return true;
            }
            return true;
        }
        return false;
    }

}
