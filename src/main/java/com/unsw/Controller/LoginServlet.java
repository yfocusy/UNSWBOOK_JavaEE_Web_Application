package com.unsw.Controller;

import com.unsw.Entity.Post;
import com.unsw.Entity.Users;
import com.unsw.Service.Implement.FriendServiceImpl;
import com.unsw.Service.Implement.PostServiceImpl;
import com.unsw.Service.Implement.UsersServiceImpl;
import com.unsw.Service.Interface.FriendService;
import com.unsw.Service.Interface.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet")
public class LoginServlet {

    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println();
        System.out.println("--------1 in LoginServelt------------");
        System.out.println("request.getParameter(loginname)="+request.getParameter("loginname"));
        System.out.println("request.getParameter(password) ="+request.getParameter("password"));
        System.out.println("request.getParameter(code) ="+request.getParameter("code"));

        String username = request.getParameter("loginname");
        String password = request.getParameter("password");
        String loginStatus = new String("");
        UsersServiceImpl usersService = new UsersServiceImpl();
        Users user = new Users();
        user = usersService.Login(username, password);
        if (user==null){
            loginStatus = "loginFail";
            System.out.println("loginStatus ="+loginStatus);
            request.setAttribute("error", "login Fail.Please check your username and password.");
            request.getRequestDispatcher("/control?action=login").forward(request, response);

        }else{
            if(user.getBan()==1){
                loginStatus = "loginFail";
                System.out.println("loginStatus ="+loginStatus);
                request.setAttribute("error", "login Fail. You are banned by the admin.");
                request.getRequestDispatcher("/control?action=login").forward(request, response);
            }





            // 把user对象保存在session, login success
            request.getSession().setAttribute("login-user", user);


            // 把user friendList 对象保存在session
            FriendService friendService = new FriendServiceImpl();
            List<Users> friendList = friendService.showAllFriends(user.getUid());
            System.out.println("friendList ="+friendList);
            request.getSession().setAttribute("friend-list", friendList);

//            PostService postService = new PostServiceImpl();
//            List<Post> friendPostsList = postService.getFriendPostsByFriendList(friendList);
//            System.out.println("按照postId 排序的 friendPostsList ="+friendPostsList);
//            request.getSession().setAttribute("friendPosts-list",friendPostsList);

//            request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
//            request.getRequestDispatcher("/control?action=home.jsp").forward(request, response);
             request.getRequestDispatcher("/control?action=home").forward(request, response);

        }
    }

}
