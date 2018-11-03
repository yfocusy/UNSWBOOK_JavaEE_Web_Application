<%@ page import="com.unsw.Entity.Users" %>
<%@ page import="com.unsw.Dao.Interface.UsersDao" %>
<%@ page import="com.unsw.Service.Implement.UsersServiceImpl" %>
<%@ page import="com.unsw.Dao.Implement.UsersDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: yuli510
  Date: 17-8-29
  Time: 下午1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%
  Users login_user = (Users)request.getSession().getAttribute("login-user");
  System.out.println("profile.jsp => login_user ="+login_user);
  if(login_user == null){
    // 说明用户没有登录，让他跳转到登录页面
    request.setAttribute("error", "Please login.");
//    request.getRequestDispatcher("/LoginServlet").forward(request,response);
    request.getRequestDispatcher("login.jsp").forward(request,response);
    // 这个return很重要！
    return;
  }

  Integer uid = login_user.getUid();// default ui
  UsersServiceImpl usersService = new UsersServiceImpl();
  Users profileUser = usersService.findUserByUid(uid);
  try {
    uid = Integer.parseInt(request.getParameter("uid").toString());
    profileUser = usersService.findUserByUid(uid);
  }catch (Exception e) {
    System.out.println("init uid Error");
//      e.printStackTrace();
  }
%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Profile page1</title>

  <!-- Bootstrap -->
  <link href="source/css/bootstrap.min.css" rel="stylesheet">
  <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.7.3/css/bootstrap.min.css" integrity="">--%>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<jsp:include page="/WEB-INF/public_pages/head_userAlreadyLogin.jsp" />
<%--1.如果session里没有这个用户，就re direct 到 login页面--%>
<h1 align="center">Profile</h1>
  <div class="container" style="padding: 20px 0px 80px;">

    <table class="table table-striped table-bordered table-hover table-condensed" width="85%" border ="1" align="center">
      <tr>
        <td>Username</td>
        <td><%=profileUser.getUsername()%></td>
      </tr>

      <tr>
        <td>Name</td>
        <td><%=profileUser.getName()%></td>
      </tr>

      <tr>
        <td>Gender</td>
        <td><%=profileUser.getGender()%></td>
      </tr>

      <tr>
        <td>Email</td>
        <td><%=profileUser.getEmail()%></td>
      </tr>

      <tr>
        <td>Birthday</td>
        <td><%=profileUser.getBirthday()%></td>
      </tr>

  </table>
</div>




















</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>
