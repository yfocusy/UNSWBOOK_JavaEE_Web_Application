<%--
  Created by IntelliJ IDEA.
  User: yuli510
  Date: 17-8-22
  Time: 下午11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DataCuration.DataProcessor" %>

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Results</title>

  <!-- Bootstrap -->
  <link href="source/css/bootstrap.min.css" rel="stylesheet">
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
<jsp:include page="/header.jsp" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="source/js/bootstrap.min.js"></script>

<style>
  .records {
    display: none;
  }
  #page-select {
    display: inline-block;
    width: 20%;
  }
</style>


<div class="container">
  <%
    List<Integer> SearchResultsIDs = new ArrayList<Integer>();
    try{
        SearchResultsIDs = (List<Integer>) request.getAttribute("SearchResultsIDs");
    }catch (Exception e){
        e.printStackTrace();
    }

    if(SearchResultsIDs==null) {
  %><h3>Sorry, no matching datasets found!</h3>
  <%
    }else if (SearchResultsIDs.size()==0){

  %>
  <h3>Sorry, no matching datasets found!</h3>
  <%
  }else{
      Integer pages = (int) Math.ceil((double)SearchResultsIDs.size()/10);
//      System.out.println("pages = "+ pages);
  %>

  <div>
    <h2><%=SearchResultsIDs.size()%> results found</h2>
    <table class="table table-striped table-bordered table-hover table-condensed" width="85%" border ="1" align="center">
      <tr>
        <th>ID</th>
        <th>Headline</th>
      </tr>
      <%
        for (Integer id: SearchResultsIDs) {
      %>
      <tr class = "records">
        <td><%=id%></td>
        <td><a href="info.jsp?id=<%=id%>">
          <%
              out.append(DataProcessor.getElementbyID(id).getHeadline());
        }
          %>
          </a>
        </td>
      </tr>
    </table>


<%--pager--%>
    <div class="pager" style="padding: 20px 300px 80px;">
      <div class="">
        <a class="pull-left btn btn-success page-nav" id="prevbtn" display ="block" data-action1="prev">Prev</a>
        <a class="pull-right btn btn-success page-nav" id="nextbtn" display ="block" data-action1="next">Next</a>
      </div>
      <div class="">
        <select id="page-select" class ="form-control">
          <%
            for (int i=1; i<pages + 1; i++){
          %>
          <option value = "<%=i%>" data-index="<%=i%>">page<%=i%></option>
          <%
            }
          %>
        </select>
      </div>
    </div>
  </div>

  <%--pager script--%>
  <script>
      var totalPage = <%=pages%>;
      function showCurrentPage() {
          var results = $('.records');
          var select = $('#page-select');
          var currentIndex = select.val();
          results.hide();

//          var start = currentIndex * 10;
          var start = (currentIndex-1) * 10;
          var i = 0;
          for (i = start; i < start + 10; i++) {
              var el = $(results[i]);
              el.show();
          }
          // set page hide
          if (currentIndex == 1){
              if (totalPage == 1){
                  HideorDisplayButton("prevbtn","none");
                  HideorDisplayButton("nextbtn","none");
              }else{
                  HideorDisplayButton("prevbtn","none");
                  HideorDisplayButton("nextbtn","block");
              }

          }else if (currentIndex == totalPage){
              HideorDisplayButton("nextbtn","none");
              HideorDisplayButton("prevbtn","block");
          }else {
              HideorDisplayButton("nextbtn","block");
              HideorDisplayButton("prevbtn","block");
          }
      }

      function HideorDisplayButton(btnId,type){
          var currentBtn = document.getElementById(btnId);
          currentBtn.style.display=type;
      }

      $('#page-select').change(function(e) {
          showCurrentPage();
      });

      $('.page-nav').click(function(e) {
          var btn = $(e.target);
          var action = btn.data('action1');
          var currentIndex = parseInt($('#page-select').val());
          if (action == "next") {
              if (currentIndex < totalPage) {
                  $('#page-select').val(currentIndex + 1);
                  $('#page-select').change();
              }
          } else if (currentIndex > 1) {
              $('#page-select').val(currentIndex - 1);
              $('#page-select').change();
          }
      });
      showCurrentPage();
  </script>

  <%
    }
  %>
</div>


</body>
<footer>
  <p align="center">© 2017   COMP9321 Assignment 1 Data Curation</p>
</footer>
</html>

