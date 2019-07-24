<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-01-03
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>归档文件目录</title>
</head>
<style>
    table{
        width:1366px;
        margin: 0 auto;
        border-collapse: collapse;
    }
    caption{
        font:800 30px/30px "宋体";
    }
    .title{
        font:800 14px/14px "宋体";
    }

    tbody td,tbody th{
        font-family: "宋体";
        text-align: center;
        border: solid 1px black;
    }
</style>
<body>
    <table>
        <caption>归档文件目录</caption>
        <thead>
            <tr>
                <td colspan="3" class="title">
                    工程项目名称:${projectName}
                </td>
                <td colspan="2" class="title">
                    MIS号:${projectMis}
                </td>
                <td colspan="2" class="title">
                    项目负责人:${user.userName}
                </td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>档案号</th>
                <th>盒号</th>
                <th>文件编号</th>
                <th>责任者</th>
                <th>文件题名</th>
                <th>文件日期</th>
                <th>备注</th>
            </tr>
        <c:forEach items="${list}" var="item">
            <tr>
                <td>${item.fileRecordNum}</td>
                <td>${item.fileBoxNum}&nbsp;</td>
                <td>${item.fileNum}</td>
                <td>${item.fileCompany}</td>
                <td>${item.fileName}</td>
                <td><fmt:formatDate value="${item.fileDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
                <td>${item.fileCount}</td>
            </tr>
        </c:forEach>
            <tr>
                <td colspan="6">合计</td>
                <td>${projectFilesCount}</td>
            </tr>
        </tbody>
    </table>

</body>
</html>
