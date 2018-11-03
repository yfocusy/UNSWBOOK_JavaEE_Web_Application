<%@ page import="com.unsw.Entity.Users" %>
<%@ page import="com.unsw.Dao.Interface.UsersDao" %>
<%@ page import="com.unsw.Service.Implement.UsersServiceImpl" %>
<%@ page import="com.unsw.Dao.Implement.UsersDaoImpl" %>
<%@ page import="com.unsw.Service.Interface.UsersService" %>
<%@ page import="com.unsw.Service.Interface.PostService" %>
<%@ page import="com.unsw.Service.Implement.PostServiceImpl" %>
<%@ page import="com.unsw.Entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  Users login_user = (Users)request.getSession().getAttribute("login-user");
  System.out.println("1 profile.jsp => login_user ="+login_user);
  System.out.println("2 profile.jsp =>");
  UsersService usersService = new UsersServiceImpl();
  Users profileUser = login_user;
  Integer uid = login_user.getUid();
  System.out.println("3 profile.jsp =>");
  try {
    uid = Integer.parseInt(request.getParameter("uid").toString());
    profileUser = usersService.findUserByUid(uid);
    System.out.println("profileUser.name="+profileUser.getUsername());
  }catch (Exception e) {
    System.out.println("init uid Error");
//      e.printStackTrace();
  }
  PostService postService = new PostServiceImpl();
  List<Post> postList = postService.searchPostsByUid(profileUser.getUid());
  if (postList!=null){
     Collections.reverse(postList);
  }
  System.out.println("profileUser.postList="+postList);


%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Profile</title>

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
<h1 align="center">Profile</h1>
  <%--<div class="container" style="padding: 20px 0px 80px;">--%>
  <%--0.大框架--%>
  <div class="mainbody container-fluid">
    <div class="row">
      <%--1.左侧头像--%>
      <div style="padding-top:50px;"> </div>
      <div class="col-lg-3 col-md-3 hidden-sm hidden-xs">
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="media">
              <div align="center">
                <img class="thumbnail img-responsive" src="<%=profileUser.getHeadphotoPath()%>" width="300px" height="300px">
              </div>
              <div class="media-body">
                <%
                  if (login_user == profileUser){
                %>
                <p align = "right"><a href="control?action=profileupdate.jsp">Edit</a></p>
                <%
                  }
                %>
                <hr>
                <h3><strong><%=profileUser.getName()%></strong></h3>
                <hr>
                <h3><strong>Username</strong></h3>
                <p><%=profileUser.getUsername()%></p>
                <h3><strong>Email</strong></h3>
                <p><%=profileUser.getEmail()%></p>
                <hr>
                <h3><strong>Gender</strong></h3>
                <p><%=profileUser.getGender()%></p>
                <hr>
                <h3><strong>Birthday</strong></h3>
                <p><%=profileUser.getBirthday()%></p>
                <h3><strong>Email</strong></h3>
                <p><%=profileUser.getEmail()%></p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <%--end  头像区--%>

      <%--3.Sample post content with picture.--%>
      <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
        <%
          if (postList!=null){
            for (Post p: postList){
        %>
        <div class="panel panel-default">
          <div class="panel-body">
            <%--user name--%>
            <div class="pull-left">
              <a href="#">
                <img class="media-object img-circle" src="<%=usersService.findUserByUid(p.getUid()).getHeadphotoPath()%>" width="50px" height="50px" style="margin-right:8px; margin-top:-5px;">
              </a>
            </div>
            <h4><a href="control?action=profile.jsp?uid=<%=p.getUid()%>" style="text-decoration:none;">
              <strong><%=usersService.findUserByUid(p.getUid()).getName()%></strong></a>
              – <small><small><a href="#" style="text-decoration:none; color:grey;"><i>
                <i class="fa fa-clock-o"></i><%=p.getPostime()%></i></a></small></small></h4>
            <hr>
            <div class="post-content">
              <p><%=p.getContent()%></p>
              <%
                if(p.getImageNum().contains("upload")){
              %>
              <img class="img-responsive" src="<%=p.getImageNum()%>">
              <%
                }
              %>
            </div>
            <hr>

            <%--like unlike--%>
          </div>
        </div>
        <%
            }}// end of if
        %>

      </div>



</div>
</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>
