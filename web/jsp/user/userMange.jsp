<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-10
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<style>
    th,td{
        text-align: center;
    }
</style>
<body>
<div class="container-fluid">
    <br>
    <div class="row">
        <div class="col-9">
            <form class="form-inline" action="user_find.action?select=false" method="post">
                <input type="hidden" name="userType" value="${authorityId}">
                <div class="form-group">
                    <label for="name">姓名</label>
                    <input type="text" class="form-control"
                           name="userName" id="name" placeholder="请输入姓名">
                </div>
                <div class="form-group">
                    <label for="tel">手机号</label>
                    <input type="tel" class="form-control"
                           name="userTel" id="tel" placeholder="请输入手机号">
                </div>
                <button class="btn btn-default" type="submit">查询</button>

            </form>

        </div>
        <div class="col-3">
            <button class="btn btn-outline-secondary" onclick="save('')">增加</button>
        </div>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">姓名</th>
            <th scope="col">手机号</th>
            <th scope="col">所在中心</th>
            <th scope="col">操作</th>
        </tr>
        </thead>
        <c:forEach  var="user" items="${page.records}">
            <tr>
                <td>${user.userName}</td>
                <td>${user.userTel}</td>
                <td>${user.userCenter}</td>
                <td>
                    <button class="btn-success" onclick="save('${user.userId}')">更新</button>
                </td>
            </tr>
        </c:forEach>
    </table>

   <%@ include file="../pageFile.jsp"%>

</div>
<script>
    <!--
    var path="${pageContext.request.contextPath}";
    function save(id) {
        location.href=path+"/user_gotoSavePage?userId="+id;
    }

    -->
</script>
</body>
</html>
