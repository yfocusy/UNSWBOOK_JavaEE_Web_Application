package com.unsw.Service;

import com.unsw.Dao.UserBufferDaoImpl;
import com.unsw.Dao.Implement.UsersDaoImpl;
import com.unsw.Entity.Users;
import com.unsw.Entity.UsersBuffer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@WebServlet("/VerificationService")
public class VerificationService extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String vcode = request.getParameter("vcode");
        UserBufferDaoImpl ubdi = new UserBufferDaoImpl();
        List<UsersBuffer> list = ubdi.getUserBufferByUserNameAndCode(userName,vcode);
        String forward = null;
        if(list.size() == 1) {
            Users login_user = new Users();
            UsersBuffer usersBuffer = list.get(0);
            login_user.setUsername(usersBuffer.getUsername());
            login_user.setPassword(usersBuffer.getPassword());
            login_user.setGender(usersBuffer.getGender());
            login_user.setName(usersBuffer.getName());
            login_user.setBirthday(usersBuffer.getBirthday());
            login_user.setEmail(usersBuffer.getEmail());
            login_user.setRegisterDate(usersBuffer.getRegisterDate());
            login_user.setLoginDate(new Timestamp(new Date().getTime()));
            login_user.setHeadphotoPath("/upload/default.jpg");
            new UsersDaoImpl().insertUsers(login_user);
            new UserBufferDaoImpl().delUsersBuffer(usersBuffer);
            forward = "/LoginService";
        } else {
            forward = "/signinfail.jsp";

        }
        RequestDispatcher rd = request.getRequestDispatcher(forward);
        rd.forward(request,response);

    }
}