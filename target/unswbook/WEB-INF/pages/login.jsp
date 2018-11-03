<%@ page import="com.unsw.Entity.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Login</title>

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
<jsp:include page="/WEB-INF/public_pages/head_userNotLogin.jsp" />
<%--1.先判断session里有没有登录用户--%>
<%
  String error = (String) request.getAttribute("error");
  System.out.println("login.jsp = error message="+error);
  if (error !=null){
%>
<%--<p><%=error%></p>--%>
<%
  }else if (error == null){
    Users login_user = (Users) request.getSession().getAttribute("login-user");
    System.out.println("login.jsp => login_user =" + login_user);
    if (login_user == null) {
      // 说明用户没有登录，让他跳转到登录页面
      request.setAttribute("error", "Please login.");
      //    request.getRequestDispatcher("/LoginServlet").forward(request,response);
      request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
      // 这个return很重要！
      return;

    } else if (login_user != null){
      request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }

  }
%>

<%--1. Login--%>
<div class="container" style="margin-top:40px">
  <div class="row">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
      <div class="panel panel-default">
        <div class="panel-heading">
          <%--<strong> Sign in to continue</strong>--%>
          <strong><%=error%></strong>
        </div>
        <div class="panel-body">
          <form action="control" method="post" id="loginform" role="form">
            <input type="hidden" name="action" value="loginAction">


            <fieldset>
              <div class="row">
                <div class="center-block" align="center" style="padding: 20px 20px 20px;">
                  <img class="profile-img"
                       src="/default/defaultuser.png" alt="" width="140", height="140">
                </div>
                <%--<img class="profile-img"--%>
                <%--src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120" alt="">--%>
                <%--</div>--%>
              </div>
              <div class="row">
                <div class="col-sm-12 col-md-10  col-md-offset-1 ">
                  <div class="form-group">
                    <div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-user"></i>
												</span>
                      <input class="form-control" placeholder="Username" name="loginname" type="text" autofocus>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-lock"></i>
												</span>
                      <input class="form-control" placeholder="Password" name="password" type="password" value="">
                    </div>
                  </div>
                  <div class="form-group">
                    <input type="submit" class="btn btn-lg btn-success btn-block" value="Login in">
                  </div>
                </div>
              </div>
            </fieldset>
          </form>
        </div>
        <div class="panel-footer ">
          Don't have an account! <a href="control?action=register.jsp" onClick=""> Sign Up Here </a>
        </div>
      </div>
    </div>
  </div>
</div>



</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>







