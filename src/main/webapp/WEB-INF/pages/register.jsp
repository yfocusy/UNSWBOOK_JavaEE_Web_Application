<%@ page import="com.unsw.Entity.Users" %><%--
  Created by IntelliJ IDEA.
  User: yuli510
  Date: 17-9-22
  Time: 上午8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Sign up</title>

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
<%--1.如果session有这个用户，就redirect 到 re页面--%>
<%
//1.先判断session里有没有r用户
  String error = (String) request.getAttribute("error");
  System.out.println("login.jsp = error message="+error);
%>
<jsp:include page="/WEB-INF/public_pages/head_userNotLogin.jsp" />
<%
  if (error!=null){
%>
<p><%=error%></p>
<%
  }
%>

<%--2. register表格--%>
<div class="container" style="margin-top:40px">
  <div class="row">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
      <div class="panel panel-default">
        <div class="panel-heading">
          </div>
          <div class="panel-body">
            <form action="control" method="post" id="registerform" role="form">

            <%--3.整体提交表单，  controller 通过name="action", value="registration" 得知需要action="registration" --%>
            <%--ControlServlet的action， action的 value ="registerAction" --%>
            <input type="hidden" name="action" value="registerAction">
            <div class="input-group">
              <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
              <input class="form-control" placeholder="Username" name="loginname" type="text" autofocus>
            </div>

            <div class="input-group">
              <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
              <input class="form-control" placeholder="Password" name="password" type="password" value="">
            </div>

             <div class="input-group">
               <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
               <input class="form-control" placeholder="Email" name="email" type="text" autofocus>
             </div>

             <div class="input-group">
               <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
               <input class="form-control" placeholder="Name" name="name" type="text" autofocus>
             </div>
            <div class="input-group">
              <input type="radio" class="form-control" name="gender" value="male">Male<br>
              <input type="radio" class="form-control" name="gender" value="female">Female
            </div>

            <div class="input-group">
              <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
              <input type="date" class="form-control" name="birthday" placeholder="birthday, yyyy-mm-dd">
            </div>
            <br>

            <div class="form-group">
              <input type="submit" class="btn btn-lg btn-success btn-block" value="Sign up">
            </div>
          </form>

              </div>
            <div class="panel-footer ">
              Have an account? <a href="control?action=login.jsp" onClick=""> Sign in Here </a>

          </div>
      </div>
    </div>
  </div>
</div>

</div>


</body>
</html>
