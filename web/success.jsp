<%@ page import="com.kkb.xzk.validation.DatabaseValidation" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: handi
  Date: 2021/7/23
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>µÇÂ¼³É¹¦</title>
  <script src="js/jquery-1.11.1.js"></script>
  <script src="js/bootstrap.js"></script>
  <link rel="stylesheet" href="css/bootstrap.css" />
  <link rel="stylesheet" href="css/bootstrap-theme.css" />
</head>
<body>

  <h1>»¶Ó­Äú£¡${sessionScope.userName}</h1>
  <form action="invalidate_session.jsp" method="post">
    <button type="submit" class="btn btn-default">ÍË³ö</button>
  </form>

</body>
</html>
