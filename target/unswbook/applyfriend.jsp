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
  <%

    Users login_user = (Users)request.getSession().getAttribute("login-user");
    System.out.println("apply.jsp => login_user ="+login_user.getUsername());
    System.out.println("--------6 in applyFriend.jsp------------");

    FriendService friendService = new FriendServiceImpl();
    List<Users> applyList = friendService.showFriendsRequest(login_user.getUid());

    System.out.println("applyList ="+applyList.get(0).getName());
    if(applyList==null || applyList.size() ==0) {
  %><h3>No apply record found!</h3>
  <%
  }else{
    Integer pages = (int) Math.ceil((double)applyList.size()/10);
    System.out.println("applyList="+applyList);

  %>
  <div>
    <h2>You have <%=applyList.size()%> friend apply found</h2>
    <table class="table table-striped table-bordered table-hover table-condensed"
           width="85%" border ="1" align="center">
      <tr>
        <th>Name</th>
        <th>Username</th>
        <th>Gender</th>
        <th>Birthday</th>
      </tr>
      <%
        for (Users user: applyList) {
      %>
      <tr class = "records">
        <td><a href="profile.jsp?uid=<%=user.getUid()%>">
          <%
            out.append(user.getName());
          %></a>
        </td>
        <td><%=user.getUsername()%></td>
        <td><%=user.getGender()%></td>
        <td><%=user.getBirthday()%></td>
        <td>

          <%--<form action="control" method="get" id="agreeFriendButton" onclick="">--%>
            <%--<input type="hidden" name="action" value="agreeFriendAction">--%>
            <%--<input type="hidden" name="uid" value=<%=login_user.getUid()%>>--%>
            <%--<input type="hidden" name="fuid" value=<%=user.getUid()%>>--%>
          <%--</form>--%>
          <%--<button form="agreeFriendButton" class="btn btn-success btn-lg btn-block"--%>
                  <%--type="submit" name = "agreefriendUid" value=<%=user.getUid()%>--%>
              <%--<span class="glyphicon glyphicon-plus"></span>Agree</button>--%>

          <form action="control" method="get" id="agreeFriendButton" onclick="">
            <input type="hidden" name="action" value="agreeFriendAction">
            <input type="hidden" name="uid" value=<%=login_user.getUid()%>>
            <input type="hidden" name="fuid" value=<%=user.getUid()%>>
          </form>
          <button form="agreeFriendButton" class="btn btn-success btn-lg btn-block"
                  type="submit" name = "agreefriendUid" value=<%=user.getUid()%>
                      <span class="glyphicon glyphicon-plus"></span>Agree</button>

        </td>
        <%
            }
          }
        %>
      </tr>
    </table>
  </div>
</div>
</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>
