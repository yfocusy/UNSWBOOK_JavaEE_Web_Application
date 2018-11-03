<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.unsw.Entity.Users" %>
<%@ page import="com.unsw.Entity.Friend" %>
<%@ page import="com.unsw.Entity.FriendApply" %>
<%@ page import="com.unsw.Service.Interface.FriendService" %>
<%@ page import="com.unsw.Service.Implement.FriendServiceImpl" %>
<%@ page import="com.unsw.Dao.Implement.UsersDaoImpl" %>


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
  <%
    System.out.println("--------4 in result.jsp------------");
    Users login_user = (Users)request.getSession().getAttribute("login-user");

//    login_user = new UsersDaoImpl().findUserByUid(5); //增加的这行




    List<Users> result = (List) request.getSession().getAttribute("result");
    FriendService friendService = new FriendServiceImpl();
    List<FriendApply> applyList = friendService.getFriendApplyBySender(login_user.getUid());
    System.out.println("apply.jsp => login_user ="+login_user.getUsername());
    System.out.println("apply.jsp => applyList  ="+applyList);
    if(result==null || result.size() ==0) {
  %><h3>Sorry, no matching datasets found!</h3>
  <%
  }else{
    Integer pages = (int) Math.ceil((double)result.size()/10);
    List<Users> friendList = friendService.showAllFriends(login_user.getUid());
    System.out.println("friendList="+friendList);
  %>

    <div class="container">
      <div class="row">
        <div class="col-xs-12 col-sm-offset-0 col-sm-10">
          <div class="panel panel-default">
            <ul class="list-group" id="contact-list">

              <h2>    <%=result.size()%> users found</h2>
              <%
                for (Users user: result) {
                  if(user!=login_user){
              %>

              <li class="list-group-item">
                <div class="col-xs-3 col-sm-2">
                  <img src=<%=user.getHeadphotoPath()%> alt=<%=user.getName()%> class="img-responsive img-circle" />
                </div>
                <div class="col-xs-12 col-sm-9">
                  <%--<span class="name"><strong>--%>
                    <%--<a href="control?action=profile.jsp?uid=<%=user.getUid()%>">--%>
                      <%--<% out.append(user.getName());%>--%>
                    <%--</a></strong>--%>
                    <div class="col-sm-3 text-align-center">
                      <strong>
                        <a href="control?action=profile.jsp?uid=<%=user.getUid()%>">
                             <% out.append(user.getName());%></a>
                      </strong>
                      <br>
                      <p class="fs-mini text-muted">Gender:   <%=user.getGender()%></p>
                      <p class="fs-mini text-muted">Brithday: <%=user.getBirthday()%></p>
                     <%
                       System.out.println("friendList="+friendList);
                       if (friendList ==null || !friendList.contains(user)){
                         Boolean requestAreadySend = friendService.checkApplys(login_user.getUid(),user.getUid());
                         if (!requestAreadySend  && user!=login_user){

                     %>

                      <a class="btn btn-primary btn-info btn-sm"
                         href="control?action=addFriendAction&uid=<%=login_user.getUid()%>&fuid=<%=user.getUid()%>">
                        Add Friend
                        </a>
                    <% }else if(requestAreadySend){%>
                      <p>Already applied</p>
                      <% }} %>
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
