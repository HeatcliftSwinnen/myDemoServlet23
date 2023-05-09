<%--
  Created by IntelliJ IDEA.
  User: studentdev04
  Date: 08-05-23
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" href="styles/style.css">
    </head>
    <body>
        <h1>login</h1>
        <form method="post" action="login">
            <label for="login">login: </label>
            <input type="text" id="login" name="login" value="${login}">
            <label for="password">password: </label>
            <input type="password" id="password" name="password">
            <button type="submit">Connect</button>
        </form>
    </body>
</html>
