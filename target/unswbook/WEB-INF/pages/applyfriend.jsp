<%--
  Created by IntelliJ IDEA.
  User: yuli510
  Date: 17-9-25
  Time: 上午9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.unsw.Entity.Users" %>
<%@ page import="com.unsw.Entity.Friend" %>
<%@ page import="com.unsw.Service.Interface.FriendService" %>
<%@ page import="com.unsw.Service.Implement.FriendServiceImpl" %>
<%@ page import="com.unsw.Entity.FriendApply" %>
<%@ page import="com.unsw.Service.Interface.UsersService" %>
<%@ page import="com.unsw.Service.Implement.UsersServiceImpl" %>


<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Applys</title>

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
  <%

    Users login_user = (Users)request.getSession().getAttribute("login-user");
    System.out.println("apply.jsp => login_user ="+login_user.getUsername());
    System.out.println("--------6 in applyFriend.jsp------------");

    FriendService friendService = new FriendServiceImpl();
    List<FriendApply> applyList = friendService.getFriendApplyByReceiver(login_user.getUid());

    System.out.println("applyList ="+applyList);
    if(applyList==null || applyList.size() ==0) {
  %><h3>No apply record found!</h3>
  <%
  }else{
    Integer pages = (int) Math.ceil((double)applyList.size()/10);
    System.out.println("applyList="+applyList);

  %>
    <div class="container">
      <div class="row">
        <div class="col-xs-12 col-sm-offset-0 col-sm-10">
          <div class="panel panel-default">
            <ul class="list-group" id="contact-list">

              <h2>You have <%=applyList.size()%> friend apply found</h2>
              <%
                for (FriendApply apply: applyList)
                {
                  UsersService usersService = new UsersServiceImpl();
                  Users sender = usersService.findUserByUid(apply.getSenderUid());
//                  out.append(sender.getName());
              %>

              <li class="list-group-item">
                <div class="col-xs-3 col-sm-2">
                  <img src=<%=sender.getHeadphotoPath()%> alt=<%=sender.getName()%> class="img-responsive img-circle" />
                </div>
                <div class="col-xs-12 col-sm-9">
                  <%--<span class="name"><strong>--%>
                  <%--<a href="/control?action=profile.jsp?uid=<%=user.getUid()%>">--%>
                  <%--<% out.append(user.getName());%>--%>
                  <%--</a></strong>--%>
                  <div class="col-sm-3 text-align-center">
                    <strong>
                      <a href="control?action=profile.jsp?uid=<%=apply.getSenderUid()%>">
                        <% out.append(sender.getName());%></a>
                    </strong>
                    <br>
                    <p class="fs-mini text-muted">Gender:   <%=sender.getGender()%></p>
                    <p class="fs-mini text-muted">Brithday: <%=sender.getBirthday()%></p>

                    <a class="btn btn-primary btn-info btn-sm" type="submit"
                       href="control?action=agreeFriendAction&uid=<%=login_user.getUid()%>&fuid=<%=sender.getUid()%>">
                      Agree
                    </a>
                  </div>


                  <%--</span>--%>
                  <br/>
                </div>
                <div class="clearfix"></div>
              </li>
              <%
                  }
              %>


            </ul>
          </div>
        </div>
      </div>
    </div>
    <%
      }
    %>


  </div>

  <%--<jsp:include page="/WEB-INF/public_pages/foot.jsp" />--%>

</body>
</html>
