//package com.unsw.Service;
//
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//
//import com.unswbook.dao.imple.UserBufferDaoImpl;
//import com.unswbook.dao.imple.UserDaoImpl;
//import com.unswbook.domain.Users;
//import com.unswbook.domain.UsersBuffer;
//
//
//@WebServlet("/RegisterService")
//public class RegisterService extends HttpServlet{
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
//    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userName = request.getParameter("userName");
//        List<Users> userList = new UsersDaoImpl().getUserByUserName(userName);
//        String page = null;
//        UsersBuffer usersBuffer = null;
//        if (userList.isEmpty()) {
//            if(new UsersDaoImpl().getUserByEmail(request.getParameter("email")).size() == 0) {
//                usersBuffer = new UsersBuffer();
//                usersBuffer.setUsername(userName);
//                usersBuffer.setPassword(request.getParameter("password"));
//                usersBuffer.setName(request.getParameter("name"));
//                usersBuffer.setGender(request.getParameter("gender"));
//                usersBuffer.setEmail(request.getParameter("email"));
//                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//                Date birthdayDate = null;
//                try {
//                    System.out.println("time: " + request.getParameter("birthday"));
//                    birthdayDate = ft.parse(request.getParameter("birthday"));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                java.sql.Date birthday = new java.sql.Date(birthdayDate.getTime());
//                usersBuffer.setBirthday(birthday);
//                usersBuffer.setRegisterDate(new java.sql.Timestamp(new Date().getTime()));
//                usersBuffer.setLoginDate(new java.sql.Timestamp(new Date().getTime()));
//                String vcode = new RandomNumberService().randomNumberGenerator();
//                usersBuffer.setVerification(vcode);
//                new UserBufferDaoImpl().insertUsersBuffer(usersBuffer);
//                SendEmailService.send(request.getParameter("email"), "verification code is: " + vcode);
//                page = "/WEB-INF/pages/verification.jsp";
//            } else {
//                page = "/WEB-INF/pages/registeremailfail.jsp";
//            }
//        } else {
//            page = "/WEB-INF/pages/registerfail.jsp";
//        }
//
//            request.getSession().setAttribute("usersBuffer",usersBuffer);
//        RequestDispatcher rd = request.getRequestDispatcher(page);
//        rd.forward(request,response);
//    }
//
//}
