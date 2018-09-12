<%--
  Created by IntelliJ IDEA.
  User: Lv
  Date: 2018/9/11
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/testUpload1.action" enctype="multipart/form-data"
      method="post">
    上传用户：<input type="text" name="username"/><br/>
    上传文件：<input type="file" name="file"/><br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
