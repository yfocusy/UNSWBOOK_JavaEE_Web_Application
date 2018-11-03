<%@ page import="com.unsw.Entity.Users" %>
<%@ page import="com.unsw.Entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unsw.Service.Interface.UsersService" %>
<%@ page import="com.unsw.Service.Implement.UsersServiceImpl" %>
<%@ page import="com.unsw.Service.Interface.PostService" %>
<%@ page import="com.unsw.Service.Implement.PostServiceImpl" %>
<%@ page import="com.unsw.Service.Interface.FriendService" %>
<%@ page import="com.unsw.Service.Implement.FriendServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--1.如果session离没有这个用户，就redirect 到 login页面--%>
<%
  Users login_user = (Users)request.getSession().getAttribute("login-user");
  System.out.println("admin.jsp => admin_user ="+login_user.getUsername());
  if(login_user == null){
    // 说明用户没有登录，让他跳转到登录页面
    request.setAttribute("error", "Please login.");
//    request.getRequestDispatcher("/LoginServlet").forward(request,response);
    request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
    return;
  }
%>


<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Admin</title>

  <!-- Bootstrap -->
  <link href="../../source/css/bootstrap.min.css" rel="stylesheet">
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
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../source/js/bootstrap.min.js"></script>

<div class="container">
  <p>admin user: <%=login_user.getUsername()%>   <%=login_user.getName()%> adminID:<%=login_user.getAdminUid()%></p>

  <%--admin--%>
  <div class="container" style="padding: 20px 200px 10px;">
    <form action="control/admin" method="get" id="adminSearchform"
          class="bs-example bs-example-form" role="form">
      <%--ControlServlet的action， action的 value ="searchAction" --%>
      <input type="hidden" name="action" value="adminAction">
      <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
        <input type="text" class="form-control" name="nameoruid" placeholder="Name or uid">
      </div>
    </form>
  </div>
  <div style="padding: 20px 200px 80px;">
    <button class="btn btn-success btn-lg btn-block" type="submit" form="adminSearchform"
            name = "admin_Search" value="adminSearch">
      <span class="glyphicon glyphicon-search"></span> Search</button>
  </div>

</div>
</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>

