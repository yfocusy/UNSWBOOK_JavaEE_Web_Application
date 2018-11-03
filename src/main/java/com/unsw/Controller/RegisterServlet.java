package com.unsw.Controller;

import com.unsw.Entity.Users;
import com.unsw.Entity.UsersBuffer;
import com.unsw.Service.Implement.FriendServiceImpl;
import com.unsw.Service.Implement.UsersServiceImpl;
import com.unsw.Service.Interface.FriendService;
import com.unsw.Service.Interface.UsersService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@WebServlet(name = "RegisterServlet")
public class RegisterServlet {

    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println();
        System.out.println("--------2 in RegisterServelt------------");
        System.out.println("request.getParameter(loginname)="+request.getParameter("loginname"));
        System.out.println("request.getParameter(password) ="+request.getParameter("password"));
        System.out.println("request.getParameter(email)    ="+request.getParameter("email"));
        System.out.println("request.getParameter(email)    ="+request.getParameter("name"));
        System.out.println("request.getParameter(email)    ="+request.getParameter("gender"));
        System.out.println("request.getParameter(email)    ="+request.getParameter("birthday"));

        String username = request.getParameter("loginname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");

        UsersService usersService = new UsersServiceImpl();
        UsersBuffer ruser = new UsersBuffer();
        ruser = usersService.registeUser(username, password, email, name,gender,birthday);
        String registerStatus = new String("");
        if (ruser ==null){
            registerStatus = "registerFail";
            System.out.println("registerStatus ="+registerStatus);
            request.setAttribute("error", "Sign up Fail.This username already signed up.");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
        }else{
            //1. email
            //2. login

            // 把user对象保存在session, register success
            request.getSession().setAttribute("register-user", ruser);
//            request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
            request.setAttribute("error", "Sign up successfully. Check your email to verify.");
            request.getRequestDispatcher("/control?action=login.jsp").forward(request, response);


        }




//            String dbCheck_username = "nothave";
//            String dbCheck_email = "nothave";
//            if (dbCheck_username.equals("nothave") && dbCheck_email.equals("nothave")){
//                // 3. 发email
//                System.out.println("用户输入符合，准备调用 email Servlet");
//                SendEmailServlet.doGet(request,response);
                // 3. 发email
                //
                // 4.引导用户login
//            }

//        }



    }

}
