<%--
  Created by IntelliJ IDEA.
  User: yuli510
  Date: 17-9-23
  Time: 上午9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.unsw.Entity.Users" %>
<%@ page import="com.unsw.Entity.Friend" %>
<%@ page import="com.unsw.Entity.FriendApply" %>
<%@ page import="com.unsw.Service.Interface.FriendService" %>
<%@ page import="com.unsw.Service.Implement.FriendServiceImpl" %>
<%@ page import="com.unsw.Entity.Post" %>
<%@ page import="com.unsw.Service.Implement.PostServiceImpl" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.unsw.Service.Implement.UsersServiceImpl" %>
<%@ page import="com.unsw.Service.Interface.UsersService" %>

<%
  UsersService usersService = new UsersServiceImpl();
%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Result</title>

  <!-- Bootstrap -->
  <link href="../../source/css/bootstrap.min.css" rel="stylesheet">
  <%--<link rel="stylesheet" href="source//maxcdn.bootstrapcdn.com/bootstrap/3.7.3/css/bootstrap.min.css"--%>
  <%--integrity="">--%>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="source/https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="source/https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body>
<jsp:include page="/WEB-INF/public_pages/head_userAlreadyLogin.jsp" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../source/js/bootstrap.min.js"></script>

<div class="container">
  <div class="col-md-9 col-md-pull-0">
    <div class="mainbody container-fluid">
      <div class="row">
      <%
        System.out.println("--------21 in admintimeline.jsp------------");
        Users login_user = (Users)request.getSession().getAttribute("login-user");
        HashMap timeline = (HashMap) request.getAttribute("timeline");

        if(timeline==null || timeline.size() ==0) {
      %><h3>Sorry, no matching user found!</h3>
      <%
      }else{
        Integer pages = (int) Math.ceil((double)timeline.size()/10);
      %>
        <h2><%=timeline.size()%> records found for this user</h2>



        <%--接着显示 timeline--%>
        <%
          }
        %>
        <div>
          <%
              int i = timeline.size()-1;
            while (i>0){
            if (timeline.get(i).getClass()==Post.class){
                Post post = (Post)timeline.get(i);
          %>
<%-- ban --%>
          <form action="control" method="get" id="banButton" onclick="">
            <input type="hidden" name="action" value="banAction">
            <%--<input type="hidden" name="postid" value=<%=p.getPostId()%>>--%>
          </form>
          <button form="banButton" class="btn btn-default btn-sm btn-block" type="submit"
                  name = "banUid" value=<%=post.getUid()%>
                      <span class="glyphicon glyphicon-thumbs-up"></span>Ban this user</button>
<%-- ban --%>
          <div class="panel panel-default">
            <div class="panel-body">
              <%--user name--%>
              <div class="pull-left">
                <a href="#">
                  <img class="media-object img-circle" src="<%=usersService.findUserByUid(post.getUid()).getHeadphotoPath()%>" width="50px" height="50px" style="margin-right:8px; margin-top:-5px;">
                </a>
              </div>
              <h4><a href="control?action=profile.jsp?uid=<%=post.getUid()%>" style="text-decoration:none;">
                <strong><%=usersService.findUserByUid(post.getUid()).getName()%></strong></a>
                – <small><small><a href="#" style="text-decoration:none; color:grey;"><i>
                  <i class="fa fa-clock-o"></i><%=post.getPostime()%></i></a></small></small></h4>
              <hr>
              <div class="post-content">
                <p>Post Context: <%=post.getContent()%></p>
                <%
                  if(post.getImageNum().contains("upload")){
                %>
                <img class="img-responsive" src="<%=post.getImageNum()%>">
                <%
                  } // if of upload
                %>
              </div>
              <hr>

              <%--like unlike--%>
            </div>
          </div>

          <%
              }// end of if == post
            else{
                Friend friend = (Friend) timeline.get(i);
          %>
          <br>
          <div>
            <div><strong>time:<%=friend.getTime()%></strong></div>
            <div><%=new UsersServiceImpl().findUserByUid(friend.getUser1Uid()).getUsername()%>become friends with:<%=new UsersServiceImpl().findUserByUid(friend.getUser2Uid()).getUsername()%>
            </div>
          </div>
          <br>

          <%}
            i--;}
            String s = timeline.get(0).toString();
//            out.println("Date the user join UNSWBOOK: "+s);
          %>
          <p><strong>Date the user join UNSWBOOK: <%=s%></strong></p>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>
