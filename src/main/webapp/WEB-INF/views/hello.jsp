<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hello</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<h1>${requestScope.message}</h1>
<form action="hellop" method="post">
    <input type="text" name="name">
    <input type="submit" value="Say Hello">
</form>
</body>
</html>
