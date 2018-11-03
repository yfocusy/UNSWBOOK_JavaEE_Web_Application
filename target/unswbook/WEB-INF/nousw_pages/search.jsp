<%--
  Created by IntelliJ IDEA.
  User: yuli510
  Date: 17-8-22
  Time: 下午8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Search</title>
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
<div class="container">
  <h1 align="center">Search</h1>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="../../source/js/bootstrap.min.js"></script>

  <div class="container" style="padding: 20px 200px 10px;">
    <form action="search" method="get" id="basicSearchform"
          class="bs-example bs-example-form" role="form">
      <%--<p align = "right"><a href="advancedsearch.jsp">Advanced Search</a></p>--%>
      <div class="input-group">
        <span class="input-group-addon">Username</span>
        <input type="text" class="form-control" name="username" placeholder=" e.g.">
      </div>
      <div class="input-group">
        <span class="input-group-addon">Full name</span>
        <input type="text" class="form-control" name="fullname" placeholder="e.g. keywords">
      </div>
      <div class="input-group">
        <span class="input-group-addon">Birthday</span>
        <input type="date" class="form-control" name="birthday" placeholder="birthday ">
      </div>
      <div class="input-group">
        <span class="input-group-addon">Gender</span>
        <input type="text" class="form-control" name="gender" placeholder=" e.g.">
      </div>

    </form>
  </div>
  <div style="padding: 20px 200px 80px;">
    <button class="btn btn-success btn-lg btn-block" type="submit" form="basicSearchform"  name = "basic_Search" value="ButtonSearch">
      <span class="glyphicon glyphicon-search"></span> Search</button>
  </div>
</div>
</body>
<jsp:include page="/WEB-INF/public_pages/foot.jsp" />
</html>
