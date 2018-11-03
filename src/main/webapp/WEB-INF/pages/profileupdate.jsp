<%@ page import="com.unsw.Entity.Users" %>
<%@ page import="com.unsw.Service.Implement.UsersServiceImpl" %>
<%@ page import="com.unsw.Service.Interface.UsersService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  Users profileUser = (Users)request.getSession().getAttribute("login-user");
  System.out.println("1 profile update.jsp => login_user= profileUser ="+profileUser);
  System.out.println("2 profile.jsp =>");

%>
<html>
<script type="text/javascript">
    function myFunction() {
        var Result = document.getElementsByName('fileupload');
        alert(Result[0].value)
        //document.getElementsByName("uploadHeadPhoto")[0].value = the_path
        alert(document.getElementById("one"))
//        alert(document.getElementsByName("uploadHeadPhoto")[0].value)
//        var postText = document.getElementById('postText');
//        setCookie(postText.nodeName,postText.value);
//        alert(postText.name)
//        alert(postText.value)
        document.getElementById("one").submit();
    }
</script>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Profile page1</title>

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
<%--1.如果session里没有这个用户，就re direct 到 login页面--%>
<h1 align="center">Profile update</h1>
<div class="container" style="padding: 20px 0px 80px;">
  <form action="control/HeadPhotoUpLoad" name="one" id="one" enctype="multipart/form-data" method="post">
    <strong>choose photo</strong>
    <input type="file" name="fileupload" id="fileupload" value="upload" onchange="myFunction()"  accept="image/*"/><br>
  </form>
  <form action="control" method="get" id="updateProfileform"
        class="bs-example bs-example-form" role="form">
    <input type="hidden" name="action" value="updateProfileAction">
    <input type="hidden" name="uid" value=<%=profileUser.getUid()%>>
    <%--Username: <p><%=profileUser.getUsername()%></p><br>--%>
    <%--HeadPhoto: <img src="<%=profileUser.getHeadphotoPath()%>"--%>
    <%--alt="Responsive image" class="img-thumbnail"--%>
    <%--width="80", height="80"><br>--%>
    <%--Name: <input type="text" name="name" value="<%=profileUser.getName()%>"/><br>--%>
    <%--Gender:<input type="text" name="gender" value="<%=profileUser.getGender()%>"/><br>--%>
    <%--Email: <input type="text" name="email" value="<%=profileUser.getEmail()%>"/><br>--%>
    <%--Birthday: <input type="text" name="birthday" value="<%=profileUser.getBirthday()%>"/><br>--%>
    <table class="table table-striped table-bordered table-hover table-condensed" width="85%" border ="1" align="center">

      <tr>
        <td>Username</td>
        <td><%=profileUser.getUsername()%></td>
      </tr>

      <%--show headphoto--%>
      <%--<tr>--%>
      <%--<td>HeadPhoto</td>--%>
      <%--<td><img src="<%=profileUser.getHeadphotoPath()%>"--%>
      <%--alt="Responsive image" class="img-thumbnail"--%>
      <%--width="80", height="80"></td>--%>
      <%--</tr>--%>
      <tr>
        <%
          if (request.getParameter("headPhotoPath")!=null){
        %>
        <img src="<%=request.getParameter("headPhotoPath")%>" alt="Responsive image" class="img-thumbnail"
             width="80", height="80">
        <%
        }
        else{
        %>
        <img src="<%=profileUser.getHeadphotoPath()%>" alt="Responsive image" class="img-thumbnail"
             width="80", height="80">
        <%
          }
        %>
        <input type="text" name="uploadHeadPhoto" id="uploadHeadPhoto" value="<%=request.getAttribute("filePath")%>" hidden="true">
      </tr>
      <tr>
        <td>Name</td>
        <td><input type="text" name="name" value="<%=profileUser.getName()%>"/></td>
      </tr>

      <tr>
        <td>Gender</td>
        <td><input type="text" name="gender" value="<%=profileUser.getGender()%>"/></td>
      </tr>

      <tr>
        <td>Email</td>
        <td><input type="text" name="email" value="<%=profileUser.getEmail()%>"/></td>
      </tr>

      <tr>
        <td>Birthday</td>
        <td><input type="date" name="birthday" value="<%=profileUser.getBirthday().toString()%>"/></td>
      </tr>
    </table>
  </form>
  <div style="padding: 20px 200px 80px;">
    <button class="btn btn-success btn-group-sm btn-block" type="submit" form="updateProfileform"  name = "updatePrifile" value="updateButton">
      <span class="glyphicon glyphicon-plue"></span> update</button>
  </div>
</div>




</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>
