package com.unsw.Service.Implement;

import com.unsw.Dao.Implement.UsersDaoImpl;
import com.unsw.Dao.Interface.UsersDao;
import com.unsw.Dao.UserBufferDaoImpl;
import com.unsw.Entity.Users;
import com.unsw.Entity.UsersBuffer;
import com.unsw.Service.Interface.UsersService;
import com.unsw.Service.RandomNumberService;
import com.unsw.Service.SendEmailService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UsersServiceImpl implements UsersService{

    private UsersDao usersDao = new UsersDaoImpl();
    private UserBufferDaoImpl userBufferDao = new UserBufferDaoImpl();


    // 1. Login user
    public Users Login(String username, String password) {
        System.out.println("---- 1 in UserService => method = Login--------");
        Users loginUser = new Users();
        // username or password == null
        if (username == null || password == null) {
            return null;
        }
        // username and password != null
        else {
            loginUser = usersDao.findUserByUsername(username);
            System.out.println("UserDao return => user=" + loginUser);
            if (loginUser == null) {
                return null;
            } else if (loginUser.getUsername().equals(username)
                    && loginUser.getPassword().equals(password)) {
                return loginUser;
            }

        }
        return null;
    }// 1. end of Login method


    // 2. register
    public UsersBuffer registeUser(String username, String password, String email,
                             String name,String gender,String birthdayStr){
        if(username ==null || password == null || email==null || name == null) {
            return null;
        }
        // 1. 去db 验证是不是已经存在这个username
        // 2. 去db 验证是不是已经存在这个email
        Users alreadyuser = usersDao.findUserByUsername(username);
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date bday = sdf.parse(birthdayStr);
            java.sql.Date birthday = new java.sql.Date(bday.getTime());

            if (alreadyuser==null){
//                Users ruser = new Users();
                UsersBuffer ruser = new UsersBuffer();
                ruser.setUsername(username);
                ruser.setPassword(password);
                ruser.setEmail(email);
                ruser.setName(name);
                ruser.setGender(gender);
                ruser.setBirthday(birthday);
                ruser.setHeadphotoPath("/default/defaultuser.png");
                ruser.setRegisterDate(new Timestamp(System.currentTimeMillis()));


                RandomNumberService randomNumberService = new RandomNumberService();
                String code = randomNumberService.randomNumberGenerator();

//                SendEmailService.send(email, code);
                ruser.setVerification(code);
                new UserBufferDaoImpl().insertUsersBuffer(ruser);
                String link = "http://localhost:8080/control?action=verification.jsp&username="+username+"&code="+code;
                SendEmailService.send(email, link);
                return ruser;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("UserService return Exception=> ruser");
            e.printStackTrace();
        }
        return null;
    }

    // 3. findUserByUid
    public Users findUserByUid(Integer uid){
        int uid2 = uid.intValue();
        Users user = usersDao.findUserByUid(uid2);
        return user;
    }

    // 4. find all user in site
     public List<Users> findAllUsers(){
        List<Users> allUsers = usersDao.findAllUser();
        System.out.println("UserDao return => allUsers=" + allUsers);
        return allUsers;
     }

//     5. update user
    public Users updateUserInfo1(int uid, String name, String gender,String email,String birthdayStr){
        Users user = usersDao.findUserByUid(uid);
        user.setName(name);
        user.setGender(gender);
        user.setEmail(email);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date bday = sdf.parse(birthdayStr);
            java.sql.Date birthday = new java.sql.Date(bday.getTime());
            user.setBirthday(birthday);
        }catch(Exception e){
            System.out.println("UserService return Exception=> bday user");
            e.printStackTrace();
        }
        usersDao.updateUser(user);
        return user;
    }
     public Users updateUserInfo(int uid, String name, String gender,String email,String birthdayStr,String headPhotoSrc){
         Users user = usersDao.findUserByUid(uid);
         user.setName(name);
         user.setGender(gender);
         user.setEmail(email);
         if (headPhotoSrc!=null &&headPhotoSrc!=""){
             user.setHeadphotoPath(headPhotoSrc);
         }
         try {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
             java.util.Date bday = sdf.parse(birthdayStr);
             java.sql.Date birthday = new java.sql.Date(bday.getTime());
             user.setBirthday(birthday);
         }catch(Exception e){
             System.out.println("UserService return Exception=> bday user");
             e.printStackTrace();
         }
         usersDao.updateUser(user);
         return user;
     }


}
