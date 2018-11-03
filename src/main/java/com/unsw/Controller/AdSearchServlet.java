package com.unsw.Controller;

import com.unsw.Entity.Users;
import com.unsw.Service.Implement.FriendServiceImpl;
import com.unsw.Service.Implement.SearchServiceImpl;
import com.unsw.Service.Interface.SearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdSearchServlet")
public class AdSearchServlet {
    protected static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        doPost(request, response);
    }
    protected static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        System.out.println();
        System.out.println("--------4 in AdSearch Servelt------------");
        /* Note:
           1. search.jsp 通过get方法， 把name= "tom（例如）" 穿过SearchServlet
         */
        System.out.println("request.getParameter(name)="+request.getParameter("name"));
        System.out.println("request.getParameter(birthday)="+request.getParameter("birthday"));
        System.out.println("request.getParameter(name)="+request.getParameter("gender"));
        List<Users> searchResultList= new ArrayList();

        String targetName = request.getParameter("name");
        String targetBday = request.getParameter("birthday");
        String targetGender = request.getParameter("gender");
        SearchService searchService = new SearchServiceImpl();
        searchResultList = searchService.adSearch(targetName,targetBday,targetGender);

        System.out.println("searchResultList="+searchResultList);
        request.getSession().setAttribute("result", searchResultList);
        request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);



    }

}
