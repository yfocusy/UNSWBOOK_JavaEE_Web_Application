package com.unsw.Controller;

import com.unsw.Entity.FriendApply;
import com.unsw.Entity.Users;
import com.unsw.Service.Implement.FriendServiceImpl;
import com.unsw.Service.Implement.SearchServiceImpl;
import com.unsw.Service.Implement.UsersServiceImpl;
import com.unsw.Service.Interface.FriendService;
import com.unsw.Service.Interface.SearchService;
import com.unsw.Service.Interface.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchServlet")
public class SearchServlet {
    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        System.out.println("--------3 in Search Servelt------------");
        /* Note:
           1. search.jsp 通过get方法， 把name= "tom（例如）" 穿过SearchServlet
         */
        System.out.println("request.getParameter(name)="+request.getParameter("name"));
        String targetName = request.getParameter("name");
        SearchService searchService = new SearchServiceImpl();
        List<Users> searchResultList= new ArrayList();
        if (targetName==null || targetName.equals("")){
            UsersService usersService = new UsersServiceImpl();
            searchResultList = usersService.findAllUsers();
            System.out.println("Search Servelt => UserDao return => allUsers=" + searchResultList);
        }else{
            searchResultList = searchService.searchUserByName(targetName);
        }
        // friendList
        List<Users> friendList = new ArrayList();
//        friendList =

        // searchResultList, friendList 被session
        System.out.println("searchResultList="+searchResultList);


//        request.setAttribute("result", searchResultList);
        request.getSession().setAttribute("result", searchResultList);


        // put applyList in session
//        List<Users> applyList = new ArrayList();
        Users login_user = (Users)request.getSession().getAttribute("login-user");
        FriendService friendService = new FriendServiceImpl();
        List<FriendApply> applyList = friendService.getFriendApplyBySender(login_user.getUid());
        request.getSession().setAttribute("applyList", applyList);




        request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);


    }

}
