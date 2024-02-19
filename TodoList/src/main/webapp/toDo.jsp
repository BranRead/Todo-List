<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: Brandon
  Date: 2/19/2024
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To Do List</title>
</head>
<%--<jsp:useBean id="user" scope="request"--%>
<%--             class="com.brandon.todolist.model.User"/>--%>
<body>
    <h1>You have logged in!</h1>

<%--    <p>Name of person is: <c:out value="${user.getfName()}"/></p>--%>

    <button><a href="addNewTask.jsp">Add task</a></button>

    <table>
        <tr>
            <th>Task</th>
            <th>When to Complete</th>
            <th>Finished?</th>
        </tr>
        <c:forEach var="item" items="${sessionScope.list}">
            <tr>
                <td><c:out value="${item.getName()}"/></td>
                <td><c:out value="${item.getDueDate()}"/></td>
                <td><c:out value="${item.isDone()}"/></td>
            </tr>

        </c:forEach>
    </table>
</body>
</html>
