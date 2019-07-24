<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-22
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件保存</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/open-iconic-bootstrap.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/checkVaule.js"></script>
</head>
<style>
    .important{
        color: red;
    }
    #errNum{
        display: none;
    }
    .errMsg{
        float:right;
        display: none;
    }
</style>
<body>
    <div class="container">
        <br>
        <form id="form1" action="${pageContext.request.contextPath}/file_save.action" class="row" onsubmit="return check()">
            <div class="col-12">
                <button type="submit" class="btn btn-primary">提交</button>
                <button type="button" class="btn btn-primary" onclick="back()">取消</button>
            </div>
            <s:hidden value="%{fileId}" id="fileId" name="fileId"/>
            <s:hidden value="%{projectId}" id="projectId" name="projectId"/>
            <s:actionerror/>
            <s:fielderror/>
            <br><br>

            <div class="col-12 form-inline">
                <label for="recordNum">档案编号：</label>
                <input type="text" class="form-control" id="recordNum" style="width: 500px"
                       name="fileRecordNum" >
                <s:if test="%{fileId==0}">
                    <span id="refresh" class="oi oi-loop-circular" style="color: #007bff" onclick="getNewRecordNum()"></span>
                </s:if>
                <span id="errNum" class="important">档案编号重复</span>
            </div>

            <div class="form-group col-6">
                <label for="boxNum">盒号：<span class="important">*</span></label>
                <span id="errBox" class="important errMsg">盒号不规范</span>
                <input type="text" id="boxNum" name="fileBoxNum" class="form-control"
                       placeholder="'1'或'1-2'或'1,3,5'">
            </div>
            <div class="form-group col-6">
                <label for="fileNum">文件编号：</label>
                <input type="text" id="fileNum" class="form-control" name="fileNum">
            </div>
            <div class="form-group col-6">
                <label for="company">责任者：<span class="important">*</span></label>
                <span id="errCompany" class="important errMsg">不得为空</span>
                <input type="text" id="company" class="form-control" name="fileCompany">

            </div>
            <div class="form-group col-6">
                <label for="fileName">文件题名：<span class="important">*</span></label>
                <span id="errFileName" class="important errMsg">不得为空</span>
                <input type="text" id="fileName" class="form-control" name="fileName">
            </div>
            <div class="form-group col-6">
                <label for="fileDate">文件日期：<span class="important">*</span></label>
                <input type="date" id="fileDate" class="form-control" name="fileDate">
            </div>
            <div class="form-group col-6">
                <label for="fileCount">备注(数量)<span class="important">*</span></label>
                <span id="errNumber" class="important errMsg">不得小于0</span>
                <input type="number" id="fileCount" name="fileCount" class="form-control" value="1">
            </div>
            <div class="form-group col-6">
                <label for="topName">档案阶段<span class="important">*</span></label>
                <span id="errTopName" class="important errMsg">请选择</span>
                <select class="form-control" id="topName">
                    <option value="0">——请选择——</option>
                </select>
            </div>
            <div class="form-group col-6">
                <label for="fileTypeId">档案类型<span class="important">*</span></label>
                <span id="errFileType" class="important errMsg">请选择</span>
                <select class="form-control" id="fileTypeId" name="fileTypeId">
                    <option value="0">——请选择——</option>
                </select>
            </div>
        </form>

    </div>
    <script>
        <!--
        var path="${pageContext.request.contextPath}";
        var $fileId=$("#fileId").val();
        var $projectId=$("#projectId").val();
        $(function () {
            getTopName();
            if($fileId==="0"){
                getNewRecordNum();
                setInterval(getNewRecordNum(),1000);
            }else {
                getFile();
            }
        });
        function back() {
            location.href=path+"/file_find.action?projectId="+$projectId;
        }
        function getFile() {
            $.get(path+"/file_getFileDetail?fileId="+$fileId,function (result) {
                if(result==null){
                    alert("信息获取失败");
                    return;
                }
                var file=JSON.parse(result);
                $("#recordNum").val(file.fileRecordNum);
                $("#boxNum").val(file.fileBoxNum);
                $("#fileNum").val(file.fileNum);
                $("#company").val(file.fileCompany);
                $("#fileName").val(file.fileName);
                $("#fileDate").val(file.fileDate);
                $("#fileCount").val(file.fileCount);
                var fid=file.fileType.filetypeFid;
                $("#topName").val(fid);
                changeSecName(fid,file.fileType.filetypeId);
            })
        }
        function getTopName() {
            $.get(path+"/fileType_getTopName.action",function (result) {
                var obj=JSON.parse(result);
                var $fileType=$("#topName");
                $.each(obj,function (i,item) {
                    $fileType.append("<option value='"+item.filetypeId+"'>"+item.filetypeName+"</option>");
                });
            })
        }
        $("#topName").change(function () {
            var id=$(this).val();
            changeSecName(id,0)
        });
        function changeSecName(id,selectId) {
            var $fileType=$("#fileTypeId");
            $fileType.html("");
            $fileType.append("<option value='0'>——请选择——</option>");
            if(id==="0"){
                return;
            }

            $.get(path+"/fileType_getSecName.action?filetypeFid="+id,function (result) {
                var obj=JSON.parse(result);
                $.each(obj,function (i,item) {
                    $fileType.append("<option value='"+item.filetypeId+"'>"+item.filetypeName+"</option>");
                });
                $fileType.val(selectId);
            });

        }
        //获得新recordNum
        function getNewRecordNum() {
            $.get(path+"/file_getNewRecordNum.action?projectId="+$projectId,function (result) {
                $("#recordNum").val(result);
            })
        }
        function check() {
            var flag=true;
            $(".errMsg").hide();
            if(!checkBoxNum($("#boxNum").val())){
                $("#errBox").show();
                flag=false;
            }
//            if($("#company").val()===""){
//                $("#errCompany").show();
//                flag=false;
//            }
            if($("#fileName").val()===""){
                $("#errFileName").show();
                flag=false;
            }

            if($("#fileCount").val()===""|| $("#fileCount").val()<1){
                $("#errNumber").show();
                flag=false;
            }
            if($("#topName").val()==="0"){
                $("#errTopName").show();
                flag=false;
            }
            if($("#fileTypeId").val()==="0"){
                $("#errFileType").show();
                flag=false;
            }
           return flag;
        }
        -->
    </script>
</body>

</html>
