<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-27
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>请选择项目经理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<style>
    th,td{
        text-align: center;
    }
    th{
        background-color: #23272b;
        color: white;
    }
</style>
<body style="background-color: #d1ecf1;width: 600px;padding: 20px">
<div style="width: 100%">
    <form action="user_find.action" method="post" class="row">
        <s:textfield label="姓名" name="userName"/>
        <s:textfield label="电话号码" name="userTel"/>
        <button type="submit">查询</button>
        <button type="button" onclick="choose()">确定</button>
    </form>
    <div class="row" >
        <table>
            <colgroup>
                <col style="width: 50px">
                <col style="width: 200px">
                <col style="width: 200px">
                <col style="width: 150px">
            </colgroup>
            <thead>
                <tr>
                    <th>选择</th>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>部门</th>
                </tr>
            </thead>

        </table>
    </div>
    <div class="row">
        <div style="height: 200px;overflow: auto">
            <table border="1" property="0">
                <colgroup>
                    <col style="width: 50px">
                    <col style="width: 200px">
                    <col style="width: 200px">
                    <col style="width: 150px">
                </colgroup>
                <tbody>
                <s:iterator value="%{page.records}">
                    <tr>
                        <td><input att="<s:property value="userId"/>" username="<s:property value="userName"/>" type="radio" name="userId"> </td>
                        <td><s:property value="userName"/></td>
                        <td><s:property value="userTel"/></td>
                        <td><s:property value="userCenter"/></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
    <%@ include file="../pageFile.jsp"%>
</div>
<script>
    function choose() {
        var $radio=$('input:radio[name="userId"]:checked');
        if(!$radio.length){
            alert("请选择人员");
            return;
        }
        var id=$radio.attr("att");
        var name=$radio.attr("username");
        /* 获得原窗口对象 */
        var win=window.opener;
        //获得document对象
        var doc=win.document;
        //获得元素并赋值
        doc.getElementById("choose_userName").value=name;
        doc.getElementById("choose_userId").value=id;
        window.close();
    }
</script>

</body>
</html>
