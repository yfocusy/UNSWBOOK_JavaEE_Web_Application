package com.unsw.Service.Interface;

import com.unsw.Dao.Implement.UsersDaoImpl;
import com.unsw.Dao.Interface.UsersDao;
import com.unsw.Entity.Users;
import com.unsw.Entity.UsersBuffer;

import java.util.List;

public interface UsersService {

    // 1. Login user
    public Users Login(String username, String password);


    // 2. register
    public UsersBuffer registeUser(String username, String password, String email,
                                   String name, String gender, String birthdayStr);
    // 3. findUserByUid
    public Users findUserByUid(Integer uid);
    // 4.
    public List<Users> findAllUsers();

    //
    public Users updateUserInfo1(int uid, String name, String gender,String email,String birthdayStr);
    public Users updateUserInfo(int uid, String name, String gender,String email,String birthdayStr,String headPhotoSrc);
}
