package com.unsw.Controller;

import com.unsw.Entity.Users;
import com.unsw.Service.Implement.FriendServiceImpl;
import com.unsw.Service.Interface.FriendService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddFriendServlet")
public class AddFriendServlet {
    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        System.out.println("--------6 in add friend Servelt------------");
        //  parameter addfriend

        System.out.println("request.getParameter(uid)="+request.getParameter("uid"));
        System.out.println("request.getParameter(fuid)="+request.getParameter("fuid"));
        int uid = Integer.parseInt(request.getParameter("uid").toString());
        int fuid = Integer.parseInt(request.getParameter("fuid").toString());

        System.out.println("uid ="+uid);
        System.out.println("fuid="+fuid);

        FriendService friendService = new FriendServiceImpl();
        Boolean applySendFlag = friendService.addFriendApply(uid, fuid);
        if (applySendFlag==true){
//            request.getSession().setAttribute("friend-add-list", friendList);
            System.out.println("applySendFlag="+applySendFlag);
            /// 肯定要 js交互
        }else{
            System.out.println("applySendFlag="+applySendFlag);
        }

        ServletContext context = request.getServletContext();
//        System.out.println("context.getRealpath()   ="+context.getRealPath(""));
        System.out.println("context.getContextPath()="+context.getContextPath());
//        List<Users> result = (List) request.getSession().getAttribute("result");
//        request.setAttribute("result",result);

        request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);



    }

}
