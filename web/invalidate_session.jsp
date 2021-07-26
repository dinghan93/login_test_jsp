<%--
  Created by IntelliJ IDEA.
  User: handi
  Date: 2021/7/23
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% session.invalidate(); %>
<% response.sendRedirect("login.jsp");%>
</body>
</html>
