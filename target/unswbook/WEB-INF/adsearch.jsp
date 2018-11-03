<%@ page import="com.unsw.Entity.Users" %><%--
  Created by IntelliJ IDEA.
  User: yuli510
  Date: 17-8-22
  Time: 下午8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Search</title>
  <!-- Bootstrap -->
  <link href="../../source/css/bootstrap.min.css" rel="stylesheet">
  <%--<link rel="stylesheet" href="source//maxcdn.bootstrapcdn.com/bootstrap/3.7.3/css/bootstrap.min.css"--%>
  <%--integrity="">--%>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body>
<jsp:include page="/WEB-INF/public_pages/head_userAlreadyLogin.jsp" />
<%--1.如果session离没有这个用户，就redirect 到 login页面--%>
<p>test adsearch!!!!</p>
<%
  Users login_user = (Users)request.getSession().getAttribute("login-user");
//  System.out.println("search.jsp => login_user ="+login_user.getUsername());
  if(login_user == null){
    System.out.println("search.jsp => login_user ="+login_user);
    // 说明用户没有登录，让他跳转到登录页面
    request.setAttribute("error", "Please login.");
//    request.getRequestDispatcher("/LoginServlet").forward(request,response);
    request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
    // 这个return很重要！
    return;
  }
%>

<div class="container">
  <h1 align="center">Search</h1>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="../../source/js/bootstrap.min.js"></script>

  <div class="container" style="padding: 20px 200px 10px;">
    <form action="/control/adresult" method="get" id="adSearchform"
          class="bs-example bs-example-form" role="form">


      <%----%>
      <%--这里ControlServlet的action， action的 value ="adsearchAction" --%>
      <input type="hidden" name="action" value="adsearchAction">
      <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
        <input type="text" class="form-control" name="name" placeholder="name">
      </div>

      <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
        <input type="date" class="form-control" name="birthday" placeholder="birthday ">
      </div>
      <%--<div class="input-group">--%>
        <%--<span class="input-group-addon"><i class="fa fa-venus-mars"></i></span>--%>
        <%--<input type="text" class="form-control" name="gender" placeholder="gender">--%>
      <%--</div>--%>

      <%--<div class="input-group">--%>
        <%--<input type="radio" class="form-control" name="gender" value="male">Male<br>--%>
        <%--<input type="radio" class="form-control" name="gender" value="female">Female--%>
      <%--</div>--%>

      <div class="input-group">
        <select class="form-control" name="gender">
          <option value="male" selected>male</option>
          <option value="female">female</option>
        </select>
      </div>

    </form>
  </div>
  <div style="padding: 20px 200px 80px;">
    <button class="btn btn-success btn-lg btn-block" type="submit" form="adSearchform"  name = "adSearch" value="ButtonSearch">
      <span class="glyphicon glyphicon-search"></span> Search</button>
  </div>
</div>
</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>

