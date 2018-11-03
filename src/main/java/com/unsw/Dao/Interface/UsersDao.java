package com.unsw.Dao.Interface;


import com.unsw.Entity.Users;

import java.util.Date;
import java.util.List;

public interface UsersDao {

    public void insertUsers(Users users);
    public Users findUserByUsername(String userName);
    public Users findUserByUid(int uid);
    public void updateUser(Users users);
    public List<Users> findUserByName(String name);
    public List<Users> findUserByGender(String target_gender);
    public List<Users> findUserByBirthday(Date birthday);
    public List<Users> findAllUser();
}
