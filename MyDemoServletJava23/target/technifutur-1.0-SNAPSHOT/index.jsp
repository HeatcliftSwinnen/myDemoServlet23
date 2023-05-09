<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1> "Hello World!"  </h1>
<a href="login">login</a>
<%if (false){ %>
<p>connecté</p>
<% } else { %>
<p>pas connecté</p>
<% } %>
</body>
</html>