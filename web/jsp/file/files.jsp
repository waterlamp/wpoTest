<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-20
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>档案详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/open-iconic-bootstrap.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/colResizable-1.6.min.js"></script>
</head>
<style>
    #tab1{
        width:100%;
    }
    tbody{
        font-size: 14px;
    }
    th,td{
        text-align: center;
    }

    #alert{
        display: none;
    }
</style>
<body>
<div class="container-fluid">
    <div id="alert" class="alert alert-warning alert-dismissible fade show" role="alert">
        <strong>错误信息：</strong>删除失败，请重试！
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="row" style="height: 40px;padding-top: 5px">
        <div class="col-3">
            项目编号：${projectMis}
        </div>
        <div class="col-5">
            项目名称：${projectName}
        </div>

        <div class="col-2">盒数：${projectBoxsCount}盒</div>
        <div class="col-2">件数：${projectFilesCount}件</div>

    </div>
    <div class="row">
        <form class="col-6" action="${pageContext.request.contextPath}/file_find.action">
            <input type="hidden" id="projectId" name="projectId" value="${projectId}">
            <div class="form-inline" >
                <div class="form-group">
                    <label for="fileName">文件名称:</label>
                    <input type="text" class="form-control" id="fileName" name="fileName">
                </div>
                <button class="btn btn-primary" type="submit">查询</button>
            </div>
        </form>
        <div class="col-6">
            <div class="btn-group float-right" role="group" aria-label="Basic example">
                <button type="button" class="btn btn-success" onclick="gotoSavePage(0)">新增文件</button>
                <button type="button" class="btn btn-primary" onclick="gotoBatch()">批量导入</button>
                <button type="button" class="btn btn-primary" onclick="gotoCountPage()">工程档案移交表</button>
                <button type="button" class="btn btn-primary" onclick="gotoCatalogue()">归档文件目录</button>
                <button type="button" class="btn btn-warning" onclick="downloadExcel()">下载</button>
            </div>
        </div>

    </div>


    <table id="tab1" class="table table-hover">
        <colgroup>
            <col style="width: 15%">
            <col style="width: 8%">
            <col style="width: 10%">
            <col style="width: 15%">
            <col style="width: 24%">
            <col style="width: 10%">
            <col style="width: 8%">
            <col style="width: 10%">
        </colgroup>
        <thead>
            <tr>
                <th>档案号</th>
                <th>盒号</th>
                <th>文件编号</th>
                <th>责任者</th>
                <th>文件题名</th>
                <th>文件日期</th>
                <th>数量</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach  var="file" items="${page.records}">
            <tr>
                <td>${file.fileRecordNum}</td>
                <td>${file.fileBoxNum}</td>
                <td>${file.fileNum}</td>
                <td>${file.fileCompany}</td>
                <td>${file.fileName}</td>
                <td><fmt:formatDate value="${file.fileDate}" pattern="yyyy-MM-dd"/></td>
                <td>${file.fileCount}</td>
                <td>
                    <div class="btn-group">
                        <button class="btn btn-outline-dark" onclick="gotoSavePage(${file.fileId})" title="更改信息">
                            <span class="oi oi-pencil"></span>
                        </button>
                        <button class="btn btn-outline-dark" onclick="deleteFile(${file.fileId})" title="删除">
                            <span class="oi oi-x" style="color:red"></span>
                        </button>
                    </div>
                </td>
        </c:forEach>
        </tbody>
    </table>

    <%@include file="../pageFile.jsp"%>
</div>
</body>
<script>
    var path="${pageContext.request.contextPath}";
    $(function () {
        $("#tab1").colResizable({
            liveDrag:true,//实现实时拖动，可看见拖动轨迹
            draggingClass:"dragging" //防止拖动出险虚标线
        });

    });
    var projectId=$("#projectId").val();
    function gotoBatch() {
        location.href=path+"/file_gotoBatchPage.action?projectId="+projectId;
    }
    function gotoCountPage() {
        window.open(path+"/file_gotoCountPage.action?projectId="+projectId)
    }
    function gotoCatalogue() {
        window.open(path+"/file_catalogue.action?projectId="+projectId)
    }
    function gotoSavePage(fileId) {
        location.href=path+"/file_gotoSavePage.action?fileId="+fileId+"&projectId="+projectId;
    }
    function deleteFile(fileId) {
        $.ajax({
            url:path+"/file_delete.action",
            type:"POST",
            data:{"fileId":fileId},
            success:function (result) {
                if(result==="error"){
                    $("#alert").show();
                }else {
                    location.href=path+"/${page.url}&num=${page.currentPageNum}"
                }
            }
        })
    }
    function downloadExcel() {
        location.href=path+"/file_downLoadExcel.action?projectId="+projectId;
    }
</script>
</html>
