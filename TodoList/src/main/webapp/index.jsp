<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<jsp:useBean id="user" scope="request"
 class="com.brandon.todolist.model.User"/>
<body>
<h1>Login Form</h1>
<form action="login" method="POST">
    <input type="text" name="username">
    <input type="password" name="password">
    <button type="submit">Login</button>

    <p>Name of person is: <jsp:getProperty name="user" property="fName"/></p>
</form>

</body>
</html>