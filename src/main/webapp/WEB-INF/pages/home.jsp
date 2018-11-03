<%@ page import="com.unsw.Entity.Users" %>
<%@ page import="com.unsw.Entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unsw.Service.Interface.UsersService" %>
<%@ page import="com.unsw.Service.Implement.UsersServiceImpl" %>
<%@ page import="com.unsw.Service.Interface.PostService" %>
<%@ page import="com.unsw.Service.Implement.PostServiceImpl" %>
<%@ page import="com.unsw.Service.Interface.FriendService" %>
<%@ page import="com.unsw.Service.Implement.FriendServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--1.如果session离没有这个用户，就redirect 到 login页面--%>
<%
  Users login_user = (Users)request.getSession().getAttribute("login-user");
//  System.out.println("home.jsp => login_user ="+login_user.getUsername());
  if(login_user == null){
    // 说明用户没有登录，让他跳转到登录页面
    request.setAttribute("error", "Please login.");
//    request.getRequestDispatcher("/LoginServlet").forward(request,response);
    request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
    return;
  }
  FriendService friendService = new FriendServiceImpl();
  List<Users> friendList = friendService.showAllFriends(login_user.getUid());
  System.out.println("home.jsp => friendList ="+friendList);
  try{
      friendList.add(login_user);
  }catch (Exception e){
      System.out.println("home.jsp => friendList null="+friendList);
      List<Users> loginList = new ArrayList<Users>();
      loginList.add(login_user);
      friendList = loginList;
  }
//  if (friendList==null || friendList){
//      List<Users> loginList = new ArrayList<Users>();
//      loginList.add(login_user);
//      friendList = loginList;
//  }else{
//      friendList.add(login_user);
//  }
  UsersService usersService = new UsersServiceImpl();
  PostService postService = new PostServiceImpl();
  List<Post> friendPostsList = postService.getFriendPostsByFriendList(friendList);
  System.out.println("home.jsp => friendPostsList ="+friendPostsList);
//   10.3 post like button



%>


<html>

<script type="text/javascript">
    function myFunction(the_path) {
        var Result = document.getElementsByName('fileupload');
        alert(Result[0].value)
        document.getElementsByName("filename")[0].value = the_path
        document.getElementById("one").submit();
    }
</script>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Home</title>

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

<%--0.大框架--%>
<div class="mainbody container-fluid">
  <div class="row">
    <%--<p>Welcome to UNSWBOOK: <%=login_user.getUsername()%>   <%=login_user.getName()%></p>--%>

    <%--10.admin入口--%>
    <% if(login_user.getAdminUid()!=null && login_user.getAdminUid()>=10000 ){ %>
    <p align = "right"><a href="control?action=admin">Admin page</a></p>
    <%}%>
    <%--1.左侧头像--%>
    <div style="padding-top:50px;"> </div>
    <div class="col-lg-3 col-md-3 hidden-sm hidden-xs">
      <div class="panel panel-default">
        <div class="panel-body">
          <div class="media">
            <div align="center">
              <img class="thumbnail img-responsive" src="<%=login_user.getHeadphotoPath()%>" width="300px" height="300px">
            </div>
            <div class="media-body">
              <hr>
              <h3><strong><%=login_user.getName()%></strong></h3>
              <hr>
              <h3><strong>Username</strong></h3>
              <p><%=login_user.getUsername()%></p>
              <h3><strong>Email</strong></h3>
              <p><%=login_user.getEmail()%></p>
              <hr>
              <h3><strong>Gender</strong></h3>
              <p><%=login_user.getGender()%></p>
              <hr>
              <h3><strong>Birthday</strong></h3>
              <p><%=login_user.getBirthday()%></p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <%--2.发布框--%>
    <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
      <div class="panel panel-default">
        <div class="panel-body">
          <form action="control/FileUpLoad" name="one" id="one" enctype="multipart/form-data" method="post">
            <strong>choose photo</strong>
            <input type="file" name="fileupload" id="fileupload" value="upload" onchange="myFunction()"  accept="image/*"/><br>
          </form>
          <%
            if (request.getAttribute("filePath")!=null){
          %>
          <img src="<%=request.getAttribute("filePath")%>">
          <%
            }
          %>

          <%--2.post text--%>
          <form action= "control" method="get" id="postForm"
                enctype= "multipart/form-data" class="form-inline" role="form">
            <input type="hidden" name="action" value="postAction">

            <div class = "form-group">
              <label for="postText">text</label>
              <br>
              <textarea class="form-control" name="text"  id="postText" rows="3" cols="120" placeholder="Post what you want to say"></textarea>
            </div>

            <br>
            <input type="text" name = "filename" id="filename" value="<%=request.getAttribute("filePath")%>" hidden="true"></input>
            <br>
            <button form="postForm" type="submit" class = "btn btn-success" name="postButton">Post</button>
            <%--10.14--%>
            <br>
            <%
              String bullyingNotice = (String) request.getAttribute("bullying");
              if (bullyingNotice!=null){
            %>
            <p><%=bullyingNotice%></p>
            <%}%>
            <%--10.14--%>
          </form>
        </div>
      </div>
    </div>
    <%--3.Sample post content with picture.--%>
    <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
      <%
        if (friendPostsList!=null){
          for (Post p: friendPostsList){
              // 10.3
            List<Integer> userLikeList = postService.getPostLikeOrUnlikeListByPostId(p.getPostId(),1);
            List<Integer> userUnLikeList = postService.getPostLikeOrUnlikeListByPostId(p.getPostId(),0);
            System.out.println("home => userLikeList="+userLikeList);
            System.out.println("home => userUnLikeList="+userUnLikeList);
      %>
      <div class="panel panel-default">
        <div class="panel-body">
          <%--user name--%>
          <div class="pull-left">
            <a href="#">
              <img class="media-object img-circle" src="<%=usersService.findUserByUid(p.getUid()).getHeadphotoPath()%>" width="50px" height="50px" style="margin-right:8px; margin-top:-5px;">
            </a>
          </div>
          <h4><a href="control?action=profile?uid=<%=p.getUid()%>" style="text-decoration:none;">
            <strong><%=usersService.findUserByUid(p.getUid()).getName()%></strong></a>
            – <small><small><a href="#" style="text-decoration:none; color:grey;"><i>
              <i class="fa fa-clock-o"></i><%=p.getPostime()%></i></a></small></small></h4>
          <hr>
          <div class="post-content">
            <p><%=p.getContent()%></p>
            <% if(p.getImageNum().contains("upload")){ %>
            <img class="img-responsive" src="<%=p.getImageNum()%>">
            <% } %>
          </div>
          <hr>

            <%--like unlike--%>
            <%--10.3 cc bu--%>
            <%--<% if (! userLikeList.contains(login_user.getUid())){ %>--%>
            <div>
              <div class="pull-right btn-group-xs">
                <% if (! userLikeList.contains(login_user.getUid())){ %>
                <form action="control" method="get" id="likeButton" onclick="">
                  <input type="hidden" name="action" value="likeAction">
                </form>
                <button form="likeButton" class="btn btn-default btn-sm btn-block" type="submit"
                        name = "postid" value=<%=p.getPostId()%>
                            <span class="glyphicon glyphicon-thumbs-up"></span>Like</button>

            <% }
            if (!userUnLikeList.contains(login_user.getUid())){ %>

                <form action="control" method="get" id="unlikeButton" onclick="">
                  <input type="hidden" name="action" value="unlikeAction">
                </form>
                <button form="unlikeButton" class="btn btn-default btn-sm btn-block" type="submit"
                        name = "postid" value=<%=p.getPostId()%>
                            <span class="glyphicon glyphicon-thumbs-down"></span>Unlike</button>
             <% }%>
              </div>
              <br>
            </div>
            <%--like unlike--%>
        </div>
      </div>
      <%
          }}// end of if
      %>

    </div>
  </div>
</div>


</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>

