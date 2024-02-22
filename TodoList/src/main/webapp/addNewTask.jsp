<%--
  Created by IntelliJ IDEA.
  User: Brandon
  Date: 2/19/2024
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="list" method="post">
        <input type="text" name="name" placeholder="Name"/>
        <textarea name="description"></textarea>
        <input type="date" name="date"/>
        <button name="add" value="${sessionScope.user.getId()}">Add</button>
    </form>
</body>
</html>
