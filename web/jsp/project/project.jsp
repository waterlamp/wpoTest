<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-12
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>档案管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/colResizable-1.6.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/open-iconic-bootstrap.css">

</head>
<style>
    #card{
        z-index: 100;
        position: fixed;
        top:170px;
        left:200px;
        display: none;
        border: 2px solid #007bff;
    }
    #tab1{
        width:100%;
        border-collapse: collapse;
        table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
    }
    td{
        overflow:hidden;		/* 内容超出宽度时隐藏超出部分的内容 */
        white-space:nowrap;		/* 不换行 */
        text-overflow:ellipsis;	/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*/

    }
    td,th{
        text-align: center;
    }

</style>
<body>
<div class="container-fluid">

    <s:if test="%{errMsg.length>0}">
        <div class="alert alert-danger" role="alert">
            <s:property value="errMsg"/>
        </div>
    </s:if>
    <div class="card" id="card" style="width: 40rem;">
        <div class="card-header">项目详情<button class="float-right" id="close">X</button></div>
        <div class="card-body">
            <h5 class="card-title" id="projectName"></h5>

            <p class="card-text">文件地址：<span id="address"></span></p>
            <p class="card-text">文件盒数：<span id="boxCount"></span></p>
            <p class="card-text">文&nbsp;件&nbsp;数：<span id="fileCount"></span></p>
        </div>
    </div>
    <div class="row" id="manage">
        <div class="col-9">
            <form class="form-inline" action="project_find.action">
                <s:textfield label="Mis" name="projectMis" class="form-control"/>
                <s:if test="#session.currentUser.authority.authorityAffairs">
                    <s:textfield label="项目负责人：" name="user.userName" class="form-control"/>
                </s:if>
                <s:else>
                    <s:hidden name="user.userName"/>
                </s:else>

                <s:select label="项目归档情况" name="projectTurn" class="form-control" list="#{'false':'未归档','true':'已归档'}"/>


                <button class="btn btn-outline-secondary" type="submit">查询</button>
            </form>
        </div>
        <div class="col-3">
            <button class="btn btn-success" id="save" onclick="gotoSavePage(0)">新增档案</button>
        </div>
        <div class="col-12">
            <p>保管期限:土建项目的工程建设档案，保管期限定为永久；设备安装方面的工程建设档案，其设备使用寿命在16年以上的，保管期限定为长期；其它设备、软件等方面的保管期限均为短期。永久标识符“A”、长期标识符“B”、短期标识符“C”。</p>
        </div>
    </div>

    <table class="table table-hover" id="tab1">
        <colgroup>
            <col style="width: 6%">
            <col style="width: 6%">
            <col style="width: 10%">
            <col style="width: 25%">
            <col style="width: 10%">
            <col style="width: 10%">
            <col style="width: 10%">
            <col style="width: 10%">
            <col style="width: 13%">
        </colgroup>
        <thead>
            <tr>
                <th class="td7">类别</th>
                <th class="td7">期限</th>
                <th class="td9">项目编号</th>
                <th class="td20">项目名称</th>
                <th class="td11">项目负责人</th>
                <th class="td11">竣工日期</th>
                <th class="td11">预归档日期</th>
                <th class="td11">归档</th>
                <th class="td20">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach  var="project" items="${page.records}">
            <tr>
                <td>${project.projectType}</td>
                <td>${project.projectStorageLife}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/file_find.action?projectId=${project.projectId}">
                            ${project.projectMis}
                    </a>
                </td>
                <td>${project.projectName}</td>
                <td>${project.user.userName}</td>
                <td><fmt:formatDate value="${project.projectFindate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${project.projectPredate}" pattern="yyyy-MM-dd"/></td>
                <c:if test="${project.projectTurn}">
                    <td><fmt:formatDate value="${project.projectTurndate}" pattern="yyyy-MM-dd"/></td>
                </c:if>
                <c:if test="${!project.projectTurn}">
                    <td>
                        <button class="btn btn-success btn-sm" onclick="projectTurn(${project.projectId})">
                            归档
                        </button>
                    </td>
                </c:if>
                <td>
                    <div class="btn-group">
                        <button class="btn btn-outline-dark" onclick="getDetail(${project.projectId})" title="详细信息">
                            <span class="oi oi-magnifying-glass"></span>
                        </button>
                        <button class="btn btn-outline-dark" onclick="gotoSavePage(${project.projectId})" title="更改信息">
                            <span class="oi oi-pencil"></span>
                        </button>
                        <button class="btn btn-outline-dark" onclick="deleteProject(${project.projectId})" title="删除">
                            <span class="oi oi-x" style="color:red"></span>
                        </button>
                    </div>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>

    <%@include file="../pageFile.jsp"%>

</div>
<script>
    var path="${pageContext.request.contextPath}";
    $(function () {
        $("#tab1").colResizable({
            liveDrag:true,//实现实时拖动，可看见拖动轨迹
            draggingClass:"dragging" //防止拖动出险虚标线
        });

    });
    function gotoSavePage(id) {
        location.href=path+"/project_gotoSavePage.action?projectId="+id;
    }
    function projectTurn(id) {
        location.href=path+"/project_turn.action?projectId="+id;
    }
    function deleteProject(id) {
        location.href=path+"/project_delete.action?projectId="+id;
    }
    function getDetail(id) {
        $("#card").show();
        $.ajax({
            url:path+"/project_detail.action",
            type:"POST",
            data:{"projectId":id},
            dateType:"json",
            success:function (result) {
                console.log(result);

                var project=JSON.parse(result);
                $("#projectName").html(project.projectName);
                $("#address").html(project.projectAddress);
                $("#boxCount").html(project.projectBoxsCount);
                $("#fileCount").html(project.projectFilesCount);
            }
        })
    }
    $("#close").click(function () {
        $("#card").hide();
    })
</script>
</body>

</html>
