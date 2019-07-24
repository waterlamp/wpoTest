<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-26
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/checkVaule.js"></script>
</head>
<style>
    .errMsg{
        float: right;
        color:red;
        display: none;
    }
</style>
<body>
<div class="container">
    <br>
    <form action="${pageContext.request.contextPath}/user_save.action" class="row" onsubmit="return check()">
        <div class="col-12">
            <button type="submit" class="btn btn-primary">提交</button>
            <button type="button" class="btn btn-primary" onclick="back()">取消</button>
            <h5 style="color: red"><s:actionerror/></h5>
        </div>
        <br><br>
        <s:hidden value="%{userId}" id="userId" name="userId"/>

        <div class="form-group col-6">
            <label for="name">姓名：</label>
            <span id="errName" class="errMsg">姓名不得为空</span>
            <input type="text" name="userName" id="name" class="form-control">
        </div>
        <div class="form-group col-6">
            <label for="tel">手机号码：</label>
            <span id="errTel" class="errMsg">手机号格式错误</span>
            <input type="tel" name="userTel" class="form-control" id="tel" placeholder="请输入手机号">
        </div>
        <div class="form-group col-6">
            <label for="password">密码：</label>
            <span id="errPassword" class="errMsg">密码不少于6位</span>
            <input type="password" name="userPwd" id="password" class="form-control" placeholder="Password">
        </div>

        <div class="form-group col-6">
            <label for="authority">职责：</label>
            <span id="errAuthority" class="errMsg">请选择</span>
            <select id="authority" class="form-control" name="authorityId">
                <option value="0">--请选择--</option>
                <option value="1">管理员</option>
                <option value="2">综合人员</option>
                <option value="3">项目经理</option>
            </select>
        </div>
        <div class="form-group col-6">
            <label for="center">所在中心：</label>
            <span id="errCenter" class="errMsg">请选择</span>
            <select name="userCenter" id="center" class="form-control">
                <option value="0">--请选择--</option>
                <option value="全业务">全业务中心</option>
                <option value="基础网">基础网中心</option>
                <option value="综合室">综合室</option>
            </select>
        </div>
        <div class="form-group col-6">
            <label for="userActive">账号状态：</label>
            <select name="userActive" id="userActive" class="form-control">
                <option value="true">使用中</option>
                <option value="false">关闭</option>
            </select>
        </div>
    </form>
</div>
<script>
    <!--
    var path="${pageContext.request.contextPath}";
    var $userId=$("#userId").val();
    $(function () {
        if($userId.length>0){
            $.get(path+"/user_detail.action?userId="+$userId,function (result) {
                if(result==null){
                    alert("信息获取失败");
                    return;
                }
                var user=JSON.parse(result);
                $("#name").val(user.userName);
                $("#tel").val(user.userTel);
                $("#password").val(user.userPwd);
                $("#authority").val(user.authorityId);
                $("#center").val(user.userCenter);
                $("#userActive").val(user.userActive+"");
            })
        }
    });
    function back() {
        location.href=path+"/user_find.action?authority.authorityId=0";
    }

    function check() {
        var flag=true;
        $(".errMsg").hide();
        if($("#name").val()===""){
            $("#errName").show();
            flag=false;
        }
        if(!checkTel($("#tel").val())){
            $("#errTel").show();
            flag=false;
        }
        if($("#password").val().length<6){
            $("#errPassword").show();
            flag=false;
        }
        if($("#authority").val()==="0"){
            $("#errAuthority").show();
            flag=false;
        }
        if($("#center").val()==="0"){
            $("#errCenter").show();
            flag=false;
        }
        return flag;
    }
    -->
</script>
</body>
</html>
