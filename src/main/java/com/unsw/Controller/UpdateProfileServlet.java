package com.unsw.Controller;

import com.unsw.Entity.Users;
import com.unsw.Service.Implement.UsersServiceImpl;
import com.unsw.Service.Interface.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateProfileServlet")
public class UpdateProfileServlet {
    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        System.out.println("--------5 in update profile Servelt------------");
        //  parameter delfriend

        System.out.println("request.getParameter(uid)="+request.getParameter("uid"));
        System.out.println("request.getParameter(name)="+request.getParameter("name"));
        System.out.println("request.getParameter(gender)="+request.getParameter("gender"));
        System.out.println("request.getParameter(email)="+request.getParameter("email"));
        System.out.println("request.getParameter(birthday)="+request.getParameter("birthday"));

        int uid = Integer.parseInt(request.getParameter("uid").toString());
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String birthdayStr = request.getParameter("birthday");
        String headPhotoSrc = request.getParameter("uploadHeadPhoto");
        System.out.println("request.getParameter(uploadHeadPhoto)="+request.getParameter("uploadHeadPhoto"));


        UsersService usersService = new UsersServiceImpl();
        if (headPhotoSrc == null || headPhotoSrc.equals("null") ){
            headPhotoSrc = usersService.findUserByUid(uid).getHeadphotoPath();
            System.out.println("headPhotoSrc="+headPhotoSrc);
        }
        Users user = usersService.updateUserInfo(uid, name, gender, email, birthdayStr, headPhotoSrc);
        request.getSession().setAttribute("login-user", user);

        request.getRequestDispatcher("/control?action=profile.jsp").forward(request, response);
    }

}
