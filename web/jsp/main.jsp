<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-10
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <title>水中灯个人办公网</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/open-iconic-bootstrap.css">
</head>
<style>
    *{
        margin: 0;
        padding: 0;
    }
    header{
        height: 40px;
        background-color: #0056b3;
    }
    .userMsg,#change_pwd{
        line-height: 35px;
        text-align: center;
    }
    #change_pwd{
        font-size: 15px;
    }
    .userMsg{
        font-size: 20px;
    }
    h3{
        padding-left: 10px;
    }
    h3,.userMsg{
        color:white;
    }
    .framePart{
        padding: 0;
        margin: 0;
    }
    #leftPart{
        background-color:#003366;
    }
    .list-group{
        width: 100%;
    }
    .listDiv{
        display: none;
    }
    a:hover{
        background-color: #002752;
        color: white;
    }
</style>
<body>
    <header class="row">
        <div class="col-8">
            <h3><span class="oi oi-globe userMsg"></span>水中灯个人办公网</h3>
        </div>
        <div class="col-1 userMsg">${ currentUser.userName }</div>
        <div class="col-2">
            <span id="change_pwd" class="oi oi-cog userMsg">修改密码</span>
        </div>
        <div class="col-1">
            <span id="quiet" class="oi oi-power-standby userMsg" onclick="logOut()"></span>
        </div>

    </header>
    <div class="container-fluid">
        <div class="row">
            <div id="leftPart"  class="col-2 framePart list-group">

                <c:if test="${currentUser.authority.authorityManage}">
                    <li class="list-group-item topTitle" id="1001" onclick="toggleDiv(10011)">
                        <span class="oi oi-people">&nbsp;账号管理</span>
                    </li>
                    <div class="listDiv list-group" id="10011">
                        <a href="${pageContext.request.contextPath}/user_find.action?select=false&authorityId=0" target="mainframe" class="list-group-item list-group-item-info">所有人员</a>
                        <a href="${pageContext.request.contextPath}/user_find.action?select=false&authorityId=3" target="mainframe" class="list-group-item list-group-item-info">项目经理</a>
                        <a href="${pageContext.request.contextPath}/user_find.action?select=false&authorityId=2" target="mainframe" class="list-group-item list-group-item-info">综合人员</a>
                    </div>
                </c:if>
                <li class="list-group-item topTitle" id="1002" onclick="toggleDiv(10021)">
                    <span class="oi oi-document">&nbsp;工单管理</span>
                </li>
                <div class="listDiv list-group" id="10021">
                    <a href="${pageContext.request.contextPath}/jsp/order/addOrder.jsp" target="mainframe" class="list-group-item list-group-item-info">工单起草</a>
                    <a href="#" target="mainframe" class="list-group-item list-group-item-info">我起草的</a>
                    <a href="#" target="mainframe" class="list-group-item list-group-item-info">待办工单</a>
                    <a href="#" target="mainframe" class="list-group-item list-group-item-info">已办工单</a>
                    <a href="#" target="mainframe" class="list-group-item list-group-item-info">已完结工单</a>
                </div>
                <li class="list-group-item topTitle" id="1003" onclick="toggleDiv(10031)">
                    <span class="oi oi-file">&nbsp;档案管理</span>
                </li>
                <div class="listDiv list-group" id="10031">
                    <a href="${pageContext.request.contextPath}/project_find.action?projectTurn=false" target="mainframe" class="list-group-item list-group-item-info">档案管理</a>
                </div>

            </div>
            <div id="mainpart" class="col-10 framePart">
                <iframe name="mainframe" id="mainframe" src="${pageContext.request.contextPath}/jsp/Hello.jsp" width="100%" height="100%"></iframe>
            </div>
        </div>
    </div>

</body>
<script>
    var path="${pageContext.request.contextPath}";
    function toggleDiv(id) {
        $("#"+id).toggle();
    }
    function logOut() {
        location.href=path+"/user_logOut.action";
    }

</script>
</html>
