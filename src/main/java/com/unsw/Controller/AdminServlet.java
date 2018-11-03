package com.unsw.Controller;

import com.unsw.Entity.Post;
import com.unsw.Entity.Users;
import com.unsw.Service.Implement.AdminServiceImpl;
import com.unsw.Service.Implement.UsersServiceImpl;
import com.unsw.Service.Interface.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "AdminServlet")
public class AdminServlet {
    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        System.out.println("--------20 in admin Servelt------------");
        //  parameter delfriend

        System.out.println("request.getParameter(nameoruid)="+request.getParameter("nameoruid"));
        String nameoruid = request.getParameter("nameoruid");
        int targetUid = -1;
        try{
            targetUid = Integer.parseInt(request.getParameter("nameoruid").toString());
        }catch (Exception e){
            System.out.println("admin servlet => uid error");
        }
        // 1. 根据搜索内容找到targetUser
        UsersService usersService = new UsersServiceImpl();
        Users targetUser = null;
        if(targetUid == -1){//
            System.out.println("admin servlet => admin 需要查 name="+nameoruid);
            List<Users> allUsers = usersService.findAllUsers();
            for (Users u : allUsers){
                if (u.getName() == nameoruid){
                    targetUser = u;
                }
            }

        }else if (targetUid!=-1){
            System.out.println("admin servlet => admin 需要查 uid="+targetUid);
            targetUser = usersService.findUserByUid(targetUid);
        }

        // 2. 把targetUser放入admin的service，返回按照时间排序的timeline
        request.setAttribute("timeline",new AdminServiceImpl().timeline(targetUser.getUid()));
//        HashMap s = new AdminServiceImpl().timeline(targetUser.getUid());
//        Post post = (Post)s.get(1);
//        Boolean x = (s.get(1).getClass()==Post.class);
        // 3. 把timeline的list,存attribute，返回给页面
//       request.getSession().setAttribute("timeline", timeline);
        request.getRequestDispatcher("/WEB-INF/pages/admintimeline.jsp").forward(request, response);





    }

}
