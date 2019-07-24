<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-22
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    #msg{
        margin:auto 50px;
        width: 500px;
        height: 300px;
        border: 3px solid #007bff;
        text-align: center;
    }
</style>
<body>
<div id="msg">
    <s:actionerror/>
</div>

</body>
</html>
