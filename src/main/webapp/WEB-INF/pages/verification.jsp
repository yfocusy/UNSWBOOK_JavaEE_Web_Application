<%@ page import="com.unsw.Entity.UsersBuffer" %>
<%@ page import="com.unsw.Dao.UserBufferDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unsw.Entity.Users" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.unsw.Dao.Implement.UsersDaoImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    System.out.println("request.getParameter(username)="+request.getParameter("username"));
    System.out.println("request.getParameter(code) ="+request.getParameter("code"));
    String username = request.getParameter("username");
    String code = request.getParameter("code");
    String forward= null;
    List<UsersBuffer> usersBufferList = new UserBufferDaoImpl().getUserBufferByUserNameAndCode(username,code);
    if(usersBufferList.size() == 1) {
        Users login_user = new Users();
        UsersBuffer usersBuffer = usersBufferList.get(0);
        login_user.setUsername(usersBuffer.getUsername());
        login_user.setPassword(usersBuffer.getPassword());
        login_user.setGender(usersBuffer.getGender());
        login_user.setName(usersBuffer.getName());
        login_user.setBirthday(usersBuffer.getBirthday());
        login_user.setEmail(usersBuffer.getEmail());
        login_user.setRegisterDate(usersBuffer.getRegisterDate());
        login_user.setLoginDate(new Timestamp(new Date().getTime()));
        login_user.setHeadphotoPath("/default/defaultuser.png");
        new UsersDaoImpl().insertUsers(login_user);
        new UserBufferDaoImpl().delUsersBuffer(usersBuffer);
        forward = "control?action=login.jsp";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Verification</title>
    <!-- Bootstrap -->
    <link href="../../source/css/bootstrap.min.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="source//maxcdn.bootstrapcdn.com/bootstrap/3.7.3/css/bootstrap.min.css"--%>
    <%--integrity="">--%>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="/WEB-INF/public_pages/head_userNotLogin.jsp" />
<div>
    <div><p align="center"><strong>Varification successfull. Please login.</strong></p></div>

<%
    } else {
%>
    <div><p align="center"><strong>Varification error. Please user the link in your email.</strong></p></div>
<%
    }
%>
</div>
</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>


