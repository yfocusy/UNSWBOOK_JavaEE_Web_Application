package com.unsw.Controller;

import com.unsw.Entity.Users;
import com.unsw.Service.Implement.PostServiceImpl;
import com.unsw.Service.Interface.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UnlikeServlet")
public class UnlikeServlet {

    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        System.out.println("--------11 in like Servelt------------");

        System.out.println("request.getParameter(postid)="+request.getParameter("postid"));
//        System.out.println("request.getParameter(uid)="+request.getParameter("uid"));
//        int uid = Integer.parseInt(request.getParameter("uid").toString());

        Users login_user = (Users)request.getSession().getAttribute("login-user");
        int uid = login_user.getUid();
        int postid = Integer.parseInt(request.getParameter("postid").toString());

        System.out.println("uid ="+uid);
        System.out.println("postid="+postid);

        PostService postService = new PostServiceImpl();
        Boolean likeFlag = postService.unlikeOnePostByUid(uid, postid);
        System.out.println("unlikeFlag="+likeFlag);

        request.getRequestDispatcher("/control?action=home.jsp").forward(request, response);
    }

}
