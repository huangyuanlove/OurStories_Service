<%--
  Created by IntelliJ IDEA.
  User: huangyuan
  Date: 16-11-10
  Time: 下午12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="testPOJO" method="post">

    username:<input type="text" name="username"><br>
    age:<input type="text" name="age"><br>
    email:<input type="text" name="email"><br>
    city:<input type="text" name="address.city"><br>
    province:<input type="text" name="address.province"><br>
    <input type="submit" value="提交">
</form>
<hr/>

<form action="regist" method="post">
    email:<input type="text" name="email"><br>
    age:<input type="text" name="password"><br>
    age:<input type="text" name="gender"><br>
    <input type="submit" value="提交">
</form>

<a href="emps">list all</a>
<hr>
<a href="testPathVariable/11">testPathVariable</a>
<hr>
<a href="testParamsAndHeaders?username=s&age=11">testParamsAndHeaders</a>
<hr>
<a href="helloworld">hello world</a>
<hr>
<a href="testModelAndView">testModelAndView</a>
<hr>
<a href="testmap?name=xuan">testMap</a>
<hr><hr>
<form action="testFileUpload" method="POST" enctype="multipart/form-data">
    File:<input type="file" name="file"/>
    Desc:<input type="text" name="desc">
    <input type="submit" value="提交">
</form>
</body>
</html>
