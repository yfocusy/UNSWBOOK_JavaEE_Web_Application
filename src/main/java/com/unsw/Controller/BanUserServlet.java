package com.unsw.Controller;

import com.unsw.Service.Implement.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BanUserServlet")
public class BanUserServlet {
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("banUid"));
        new AdminServiceImpl().banUser(uid);
        String path = "/control/admin?action=adminAction&nameoruid="+uid+"&admin_Search=adminSearch";
        request.getRequestDispatcher(path).forward(request, response);

    }

    protected static  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
