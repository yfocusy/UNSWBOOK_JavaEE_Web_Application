package com.unsw.Service.Interface;

import com.unsw.Entity.Users;

import java.text.ParseException;
import java.util.List;

public interface SearchService {
    public List<Users> searchUserByName(String targetName);
    public List<Users> adSearch(String targetName, String targetBdayStr, String targetGender) throws ParseException;
    public List<Users> MergeSearchList(List<Users> list1,List<Users> list2,List<Users> list3);
}
