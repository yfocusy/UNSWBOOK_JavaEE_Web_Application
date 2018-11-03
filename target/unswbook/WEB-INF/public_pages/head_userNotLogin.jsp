
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Header</title>
</head>
<body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../source/js/bootstrap.min.js"></script>

<nav class="navbar navbar-light" style="background-color: #4CAF50;">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="control?action=home.jsp" style="color:#fff">UNSWBOOK</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <%--<li><a href="home.jsp" style="color:#fff">Home <span class="sr-only">(current)</span></a></li>--%>
        <%--<li><a href="profile.jsp" style="color:#fff"> Profile</a></li>--%>
        <%--<li><a href="search.jsp"  style="color:#fff"> Search</a></li>--%>
        <%--<li><a href="sitemap.jsp" style="color:#fff">Site Map</a></li>--%>
        <li><a href="control?action=contactus.jsp" style="color:#fff">Contact Us</a></li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="control?action=register.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="control?action=login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>


    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

</body>
</html>
