package com.unsw.Controller;

import com.unsw.Service.Implement.FriendServiceImpl;
import com.unsw.Service.Interface.FriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddFriendServlet")
public class DelFriendServlet {
    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        System.out.println("--------8 in del friend Servelt------------");
        //  parameter delfriend

        System.out.println("request.getParameter(uid)="+request.getParameter("uid"));
        System.out.println("request.getParameter(fuid)="+request.getParameter("fuid"));
        int uid = Integer.parseInt(request.getParameter("uid").toString());
        int fuid = Integer.parseInt(request.getParameter("fuid").toString());

        System.out.println("uid ="+uid);
        System.out.println("fuid="+fuid);
        FriendService friendService = new FriendServiceImpl();
        Boolean delFriendFlag = friendService.deleteFriend(uid, fuid);
        System.out.println("delFriendFlag ="+delFriendFlag);
        request.getRequestDispatcher("/WEB-INF/pages/friends.jsp").forward(request, response);

    }

}
