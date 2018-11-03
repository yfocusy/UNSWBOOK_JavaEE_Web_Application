<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.unsw.Entity.Users" %>
<%@ page import="com.unsw.Entity.Friend" %>
<%@ page import="com.unsw.Entity.FriendApply" %>
<%@ page import="com.unsw.Service.Interface.FriendService" %>
<%@ page import="com.unsw.Service.Implement.FriendServiceImpl" %>
<%@ page import="com.unsw.Service.Interface.UsersService" %>
<%@ page import="com.unsw.Service.Implement.UsersServiceImpl" %>


<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Friends</title>

  <!-- Bootstrap -->
  <link href="../../source/css/bootstrap.min.css" rel="stylesheet">
  <%--<link rel="stylesheet" href="source//maxcdn.bootstrapcdn.com/bootstrap/3.7.3/css/bootstrap.min.css"--%>
  <%--integrity="">--%>
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
    System.out.println("--------7 in friends.jsp------------");
    Users login_user = (Users)request.getSession().getAttribute("login-user");
//    List<Users> friendList = (List) request.getSession().getAttribute("friend-list");

    // 把user friendList 对象保存在session
    FriendService friendService = new FriendServiceImpl();
    List<Users> friendList = friendService.showAllFriends(login_user.getUid());
    System.out.println("friendList ="+friendList);
    request.getSession().setAttribute("friend-list", friendList);


    System.out.println("friendList="+friendList);
    System.out.println("friends.jsp => login_user ="+login_user.getUsername());
    if(friendList==null || friendList.size() ==0) {
  %><h3>You have 0 friend. Go to add someone you like.</h3>
  <%
  }else{
    Integer pages = (int) Math.ceil((double)friendList.size()/10);
  %>

  <div class="container">
    <div class="row">
      <div class="col-xs-12 col-sm-offset-0 col-sm-10">
        <div class="panel panel-default">
          <ul class="list-group" id="contact-list">

            <h2>  You have  <%=friendList.size()%> friends </h2>
            <%
              for (Users user: friendList) {
                if(user!=login_user){
            %>
<%--show each friend--%>
            <li class="list-group-item">
              <div class="col-xs-3 col-sm-2">
                <img src=<%=user.getHeadphotoPath()%> alt=<%=user.getName()%> class="img-responsive img-circle" />
              </div>
              <div class="col-xs-12 col-sm-9">
                <div class="col-sm-3 text-align-center">
                  <strong>
                    <a href="control?action=profile.jsp?uid=<%=user.getUid()%>">
                      <% out.append(user.getName());%></a>
                  </strong>
                  <br>
                  <p class="fs-mini text-muted">Gender:   <%=user.getGender()%></p>
                  <p class="fs-mini text-muted">Brithday: <%=user.getBirthday()%></p>

                  <a class="btn btn-primary btn-info btn-sm" type="submit"
                     href="control?action=delFriendAction&uid=<%=login_user.getUid()%>&fuid=<%=user.getUid()%>">
                    Delete
                  </a>
                </div>


                <%--</span>--%>
                <br/>
              </div>
              <div class="clearfix"></div>
            </li>
            <%
                }}
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
