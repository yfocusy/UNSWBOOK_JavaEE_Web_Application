package com.unsw.Service.Implement;

import com.unsw.Dao.Implement.UsersDaoImpl;
import com.unsw.Dao.Interface.UsersDao;
import com.unsw.Entity.Users;
import com.unsw.Service.Interface.SearchService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchServiceImpl implements SearchService{
    private UsersDao usersDao = new UsersDaoImpl();

    // 1. basic search：search target_name
    public List<Users> searchUserByName(String targetName){
        if(targetName ==null){
            return null;
        }else{
            List<Users> searchResultList = usersDao.findUserByName(targetName);
            return searchResultList;
        }
    }

    public List<Users> adSearch(String targetName, String targetBdayStr, String targetGender) throws ParseException {
        List<Users> result = new ArrayList<Users>();
        List<Users> nameResult = new ArrayList<Users>();
        List<Users> bdayResult = new ArrayList<Users>();
        List<Users> genderResult = new ArrayList<Users>();
        List<Users> allUsers = usersDao.findAllUser();
        // ---------
        System.out.println("------------------- 4. in Search service: adSearch --------------");
        System.out.println("targetName="+targetName);
        System.out.println("targetBdayStr="+targetBdayStr);
        System.out.println("targetGender="+targetGender);
        // 1. name and bday = "", get all users by gender---------
        if (targetName.equals("") && targetBdayStr.equals("")){
            if (targetGender.equalsIgnoreCase(targetGender)){
                result = usersDao.findUserByGender(targetGender);
            }
            return result;
        }
        // 2. name = "",
        if (targetName.equals("")){
            nameResult = usersDao.findAllUser();
        }else{
            nameResult = usersDao.findUserByName(targetName);
        }
        // 3. bday = ""
        if(targetBdayStr.equals("")){
            bdayResult = usersDao.findAllUser();
        }else{
            Date targetBday = new Date();
            if(allUsers!=null){
                System.out.println("allUsers="+allUsers);
                for (Users u: allUsers){
                    System.out.println("u.getBirthday()="+u.getBirthday());

                    try{
                        String userBdayStr = u.getBirthday().toString();
                        if (targetBdayStr.equalsIgnoreCase(userBdayStr)){
                            bdayResult.add(u);
                        }
                    }catch (Exception e){
                        System.out.println("bday has null value");
                    }

                }
            }
            System.out.println("bdayResult="+bdayResult);
        }

        genderResult = usersDao.findUserByGender(targetGender);
        System.out.println("genderResult="+genderResult);

        result = MergeSearchList(nameResult, bdayResult, genderResult);
        return result;
    }

    // yuli4
    public List<Users> MergeSearchList(List<Users> list1,List<Users> list2,List<Users> list3) {
        List<Users> result = new ArrayList<Users>();
        if(list1==null || list2==null || list3==null ){
            return null;
        }

        for (int i=0 ; i<list1.size(); i++){
            for (int j=0 ; j<list2.size(); j++){
                if (list1.get(i).equals(list2.get(j))){
                    for (int k=0 ; k<list3.size(); k++){
                        if (list3.get(k).equals(list2.get(j))){
                            result.add(list1.get(i));
                        }
                    }
                }
            }
        }
        if (result.size() == 0) {
            return result;
        }
        return result;
    }


}// end of class
