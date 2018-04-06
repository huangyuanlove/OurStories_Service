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
<form method="post" action="regist">
    邮箱:<input type="email" name="email"/> <br/>
    昵称:<input type="text" name="name" maxlength="10" minlength="1"/> <br/>
    密码:<input type="password" name="password" maxlength="16" minlength="6"/> <br/>
    <input type="submit" value="注册"><input type="reset" value="重置">
</form>

</body>
</html>
