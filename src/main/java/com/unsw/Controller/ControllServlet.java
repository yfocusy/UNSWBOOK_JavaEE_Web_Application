package com.unsw.Controller;

import java.io.IOException;
import java.text.ParseException;

@javax.servlet.annotation.WebServlet(name = "ControllServlet")
public class ControllServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String file = getServletConfig().getServletContext()
                .getResource("/englishStopwords.txt").getFile();

        request.getSession().setAttribute("stopWordsFile", file);


        String action = new String("");
        System.out.println("--------0 in ControllServelt------------");
        System.out.println("request.getParameter(action)="+request.getParameter("action"));

        if (request.getParameter("action") !=null){
            action = request.getParameter("action");
        }

        if (action.equals("registerAction")){
            RegisterServlet.doGet(request,response);
        } else if (action.equals("loginAction")){
            LoginServlet.doGet(request,response);
        } else if (action.equals("logoutAction")){
            LogoutServlet.doGet(request,response);
        } else if (action.equals("updateProfileAction")){
            UpdateProfileServlet.doGet(request,response);
        } else if (action.equals("addFriendAction")){
            AddFriendServlet.doGet(request,response);
        } else if (action.equals("agreeFriendAction")){
            AgreeFriendServlet.doGet(request,response);
        }else if (action.equals("delFriendAction")){
            DelFriendServlet.doGet(request,response);
        } else if (action.equals("searchAction")){
            SearchServlet.doGet(request,response);
        }else if (action.equals("adsearchAction")){
            try {
                AdSearchServlet.doGet(request,response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (action.equals("likeAction")){
            LikeServlet.doGet(request,response);
        }else if (action.equals("unlikeAction")){
            UnlikeServlet.doGet(request,response);
        }else if (action.equals("postAction")){
            PostServlet.doGet(request,response);
        }
        else if (action.equals("adminAction")){
            AdminServlet.doGet(request,response);
        }else if (action.equals("banAction")){
            BanUserServlet.doGet(request,response);
        }
        else if (action.contains("register")){
            request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
        }else if (action.contains("login")){
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
        }
        else if (action.contains("home")){
//        else if (action.contains("home.jsp")){
            request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);
        }else if (action.contains("profile")){
            request.getRequestDispatcher("WEB-INF/pages/"+action).forward(request, response);
        }else if (action.contains("profileupdate")){
            request.getRequestDispatcher("WEB-INF/pages/"+action).forward(request, response);
        }

        else if (action.contains("search")){
            request.getRequestDispatcher("WEB-INF/pages/search.jsp").forward(request, response);
        }

        else if (action.contains("sitemap")){
            request.getRequestDispatcher("WEB-INF/pages/sitemap.jsp").forward(request, response);
        }else if (action.contains("contactus")){
            request.getRequestDispatcher("WEB-INF/pages/contactus.jsp").forward(request, response);
        }else if (action.contains("applyfriend")){
            request.getRequestDispatcher("WEB-INF/pages/applyfriend.jsp").forward(request, response);
        }else if (action.contains("friends")){
        request.getRequestDispatcher("WEB-INF/pages/friends.jsp").forward(request, response);
        }

        else if (action.contains("admin")){
            request.getRequestDispatcher("WEB-INF/pages/admin.jsp").forward(request, response);
        }else if (action.contains("verification")){
            request.getRequestDispatcher("WEB-INF/pages/verification.jsp").forward(request, response);
        }

    }
}
