package com.unsw.Controller;

import com.unsw.Entity.Users;
import com.unsw.Service.Implement.FriendServiceImpl;
import com.unsw.Service.Implement.UsersServiceImpl;
import com.unsw.Service.Interface.FriendService;
import com.unsw.Service.Interface.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AgreeFriendServlet")
public class AgreeFriendServlet{
    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        System.out.println("--------6 in agree friend Servelt------------");

        System.out.println("request.getParameter(uid)="+request.getParameter("uid"));
        System.out.println("request.getParameter(fuid)="+request.getParameter("fuid"));
        int uid = Integer.parseInt(request.getParameter("uid").toString());
        int fuid = Integer.parseInt(request.getParameter("fuid").toString());

        System.out.println("uid ="+uid);
        System.out.println("fuid="+fuid);

        FriendService friendService = new FriendServiceImpl();
        Boolean agreeFriendFlag = friendService.addFriend(uid, fuid);
        System.out.println("agreeFriendFlag ="+agreeFriendFlag);

        if (agreeFriendFlag){
            // 把user friendList 对象保存在session
            UsersService usersService = new UsersServiceImpl();
            Users user = usersService.findUserByUid(uid);
            List<Users> friendList = friendService.showAllFriends(user.getUid());
            System.out.println("friendList ="+friendList);
            request.getSession().setAttribute("friend-list", friendList);
            request.getRequestDispatcher("/WEB-INF/pages/friends.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/WEB-INF/pages/applyfriend.jsp").forward(request, response);
        }


    }
}
