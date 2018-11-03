package com.unsw.Controller;

import com.unsw.Dao.Implement.UsersDaoImpl;
import com.unsw.Entity.Users;
import com.unsw.Service.Implement.PostServiceImpl;
import com.unsw.Service.Implement.UsersServiceImpl;
import com.unsw.Service.Interface.PostService;
import com.unsw.Service.Interface.UsersService;
import com.unsw.Service.SendEmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PostServlet")
public class PostServlet {
    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        System.out.println("--------12 in post Servelt------------");
        //  parameter delfriend

        System.out.println("request.getParameter(text)="+request.getParameter("text"));
        String text = request.getParameter("text");
        Users user = (Users)request.getSession().getAttribute("login-user");

//        for 性能测试
//
//        user = new UsersDaoImpl().findUserByUid(5);


        // 1. extract keywords
        PostService postService = new PostServiceImpl();
        String stopWordsFile = (String) request.getSession().getAttribute("stopWordsFile");
        Boolean bullying = postService.checkBullying(text,stopWordsFile);
        if(bullying) {
            // mail admin
            // error message
            String EmailString = "Bullying post notice: uid=" + user.getUid() + " send bullying word.";
            request.setAttribute("bullying", "Your last message may has bullying word. A notice has been sent to Admin.");
            SendEmailService.send("li.yu.unsw@gmail.com", EmailString);
        }
        new PostServiceImpl().insertPost(user.getUid(),request.getParameter("text"),request.getParameter("filename"));
        request.getRequestDispatcher("/control?action=home.jsp").forward(request, response);


//        // 2.insert post , 原来的
//        new PostServiceImpl().insertPost(user.getUid(),request.getParameter("text"),request.getParameter("filename"));
//        request.getRequestDispatcher("/control?action=home.jsp").forward(request, response);
    }

}
