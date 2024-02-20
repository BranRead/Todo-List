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

<body>
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
                <c:choose>
                    <c:when test="${item.isDone() == true}">
                        <td>Finished!</td>
                    </c:when>
                    <c:otherwise>
                        <td>Incomplete</td>
                    </c:otherwise>
                </c:choose>
                <td><form action="list"><button type="submit" name="edit" value="${item.getId()}">Edit</button></form></td>
                <td><form action="list"><button type="submit" name="delete" value="${item.getId()}">Delete</button></form></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
