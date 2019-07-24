<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-15
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增项目档案</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vue2.6.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/open-iconic-bootstrap.css">
</head>
<style>
    .errMsg{
        float: right;
        color: red;
    }
</style>
<body>
<div class="container" id="project">
    <br>
    <form action="project_save.action" class="row">
        <div class="col-12">
            <button type="submit" class="btn btn-primary">提交</button>
            <button type="button" class="btn btn-primary" onclick="back()">取消</button>
        </div>

        <input type="hidden" id="projectId" name="projectId" value="${projectId}">
        <div class="col-6">
            <div class="form-group">
                <label for="mis">MIS： </label>
                <span v-show="isRept" class="errMsg">MIS编号重复</span>
                <input type="text" class="form-control" id="mis"
                       name="projectMis" placeholder="请输入MIS编号" @blur="checkMisRept">
            </div>
        </div>
        <div class="col-6">
            <div class="form-group">
                <label for="projectName">项目名称：</label>
                <input type="text"  id="projectName" name="projectName"
                       class="form-control" placeholder="请输入MIS名称">
            </div>
        </div>
        <div class="col-6">
            <div class="form-group">
                <label for="choose_userName">项目负责人：</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="choose_userName" name="user.userName">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" onclick="findUser()">
                            <span class="oi oi-magnifying-glass"></span>
                        </button>
                    </div>
                </div>
                <input type="hidden" name="userId" id="choose_userId">

            </div>
        </div>
        <div class="col-6">
            <div class="form-group">
                <label for="finDate">竣工日期</label>
                <input type="date" id="finDate" name="projectFindate" class="form-control">
            </div>
        </div>
        <div class="col-6">
            <div class="form-group">
                <label for="type">工程类别：</label>
                <select id="type" class="form-control" name="projectType">
                    <option value="HW">话务网</option>
                    <option value="XY">新业务新技术</option>
                    <option value="CS">传输网</option>
                    <option value="ZC">支撑网</option>
                    <option value="JZ">房屋建筑</option>
                    <option value="QT">其它</option>
                </select>
            </div>
        </div>
        <div class="col-6">
            <div class="form-group">
                <label for="life">保管期限：</label>
                <select id="life" class="form-control" name="projectStorageLife">
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                </select>
            </div>
        </div>
        <div class="col-12">
            <div class="form-group">
                <label for="address">存放位置:</label>
                <input type="text" class="form-control" id="address" name="projectAddress">
            </div>
        </div>

    </form>
</div>
<script>
    var path="${pageContext.request.contextPath}";
    var vm=new Vue({
        el:"#project",
        data:{
            isRept:false

        },
        methods:{
            checkMisRept:function (e) {
                var mis=e.target.value;

                if(mis===""){
                    this.isRept=false;
                    return;
                }

                this.isRept=checkMis(mis);

            }
        }
    });
    $(function () {
        var id=$("#projectId").val();
        if(id!=0){
            $.ajax({
                url:path+"/project_detail.action",
                type:"POST",
                data:{"projectId":id},
                dateType:"json",
                success:function (result) {
                    console.log(result);

                    var project=JSON.parse(result);
                    $("#mis").val(project.projectMis);
                    $("#projectName").val(project.projectName);
                    $("#name").val(project.user.userName);
                    $("#type").val(project.projectType);
                    $("#life").val(project.projectStorageLife);
                    $("#finDate").val(project.projectFindate);
                    $("#address").val(project.projectAddress);
                }
            })
        }
    });
    function back() {
        location.href=path+"/project_find.action?projectTurn=false";
    }
    function checkMis(mis) {
        if(mis==="") return false;
        var flag=false;
        $.ajax({
            type:"GET",
            url:path+"/project_checkMis.action?projectMis="+mis,
            async: false,
            success:function (result) {
                flag=result=== "success";
            }
        });

        return flag;


    }

    function findUser() {
        window.open (path+'/user_find?select=true&authorityId=0','','height=400,width=600')
    }

</script>
</body>
</html>
