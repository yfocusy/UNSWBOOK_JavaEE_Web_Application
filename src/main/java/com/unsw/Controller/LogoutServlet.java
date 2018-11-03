package com.unsw.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LogoutServlet {

    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        System.out.println("--------2 in LogoutServelt------------");
        HttpSession session = request.getSession(false);
        System.out.println("request.getContextPath()="+request.getContextPath());
        System.out.println("session="+ session);
        if (session==null) {
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }else{
            // logout user, remove session
            System.out.println("session 1="+ session);
            session.removeAttribute("login-user");
            System.out.println("session 2="+ session);
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }

}
