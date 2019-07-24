<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-01-03
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>工程档案移交登记表</title>
</head>
<style>
    table{
        width:800px;
        margin: 0 auto;
        border-collapse: collapse;
    }
    caption{
        font:800 18px/18px "宋体";
    }
    .title{
        font:800 16px/16px "宋体";
    }
    .title2{
        background-color: yellow;
    }

    tbody td,tbody th{
        border: solid 1px black;
        font-family: "微软雅黑";
    }
    .foot{
        height: 80px;
    }
</style>
<body>
    <table>
        <caption>工程档案移交登记表</caption>
        <thead>
            <tr>
                <td colspan="5" class="title">工程名称：${projectName}</td>
            </tr>
            <tr>
                <td colspan="5" class="title">工程编号：${projectMis}</td>
            </tr>
            <tr>
                <td colspan="5" class="title">项目负责人:${user.userName}</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>序号</th>
                <th>文号</th>
                <th>责任部门</th>
                <th>文件名称</th>
                <th>备注</th>
            </tr>
            <%--<% int i=1;--%>
                <%--Map<String,List<Object[]>> map= (Map<String, List<Object[]>>)request.getAttribute("recordCount");--%>
                <%--for(String key:map.keySet()){%>--%>
            <%--<tr>--%>
                <%--<th colspan="5" class="title2"><%=key%></th>--%>
            <%--</tr>--%>
                <%--<%List<Object[]> list=map.get(key);--%>
                <%--for (Object[] objects:list){--%>
                <%--%>--%>
                <%--<tr>--%>
                    <%--<td style="text-align: center"><%=i%></td>--%>
                    <%--<td></td>--%>
                    <%--<td></td>--%>
                    <%--<td><%=objects[0]%></td>--%>
                    <%--<td style="text-align: center"><%=objects[1]%></td>--%>
                <%--</tr>--%>
            <%--<%i++;}}%>--%>
            <%int i=1;%>
            <s:iterator var="item" value="map">
                <tr>
                    <th colspan="5" class="title2"><s:property value="#item.key"/> </th>
                </tr>
                <s:iterator var="objs" value="#item.value">
                    <tr>
                    <td style="text-align: center"><%=i%></td>
                    <td></td>
                    <td></td>
                    <td><s:property value="#objs[0]"/></td>
                    <td style="text-align: center"><s:property value="#objs[1]"/></td>
                    </tr>
                    <%i++;%>
                </s:iterator>
            </s:iterator>
            <tr>
                <td>&nbsp;</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td colspan="4" >合计：</td>
                <td style="text-align: center">${projectFilesCount} </td>
            </tr>
            <tr class="foot">
                <td colspan="3">移交部门：</td>
                <td colspan="2">接收部门：</td>
            </tr>
            <tr class="foot">
                <td colspan="3">移交人签字：</td>
                <td colspan="2">接收人签字：</td>
            </tr>
            <tr class="foot">
                <td colspan="3">移交日期：</td>
                <td colspan="2">接收日期：</td>
            </tr>
        </tbody>
    </table>
</body>
</html>
