<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-01-11
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>批量导入</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xlsx.full.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/checkVaule.js"></script>
</head>
<style>
    #error p{
        color:red;
    }
</style>
<body>
<input type="hidden" id="projectId" value="${projectId}">
选择文件:<input type="file" id="excel-file"><br>
<button id="submit">上传文件</button><br>
<button onclick="goBack()">返回</button>
<a href=${pageContext.request.contextPath}${downLoadURL}>下载模板</a>
<div id="error">
</div>
<script>
    <!--
    var objects=[];
    var map={};
    var flag=false;
    var $error=$("#error");
    $(function () {
        $.get("${pageContext.request.contextPath}/fileType_totalType.action",function (result) {
            var list=JSON.parse(result);
            $.each(list,function (i,item) {
                map[item.filetypeName]=item.filetypeId;
            })
        })
    });

    $('#excel-file').change(function(e) {
        var files = e.target.files;
        var fileReader = new FileReader();
        fileReader.onload = function(ev) {
            try {
                var data = ev.target.result,
                    workbook = XLSX.read(data, {
                        type: 'binary'
                    }), // 以二进制流方式读取得到整份excel表格对象
                    objs = []; // 存储获取到的数据
            } catch (e) {
                console.log('文件类型不正确');
                return;
            }

            // 表格的表格范围，可用于判断表头是否数量是否正确
            var fromTo = '';
            // 遍历每张表读取
            for (var sheet in workbook.Sheets) {
                if (workbook.Sheets.hasOwnProperty(sheet)) {
                    fromTo = workbook.Sheets[sheet]['!ref'];
                    console.log(fromTo);
                    objs = objs.concat(XLSX.utils.sheet_to_json(workbook.Sheets[sheet]));
                     break; // 如果只取第一张表，就取消注释这行
                }
            }
            objects=[];
            $error.html("");
            flag=true;
            for(var i=0;i<objs.length;i++){
                var item=objs[i];
                var file=new fileBean();
                if(!checkBoxNum(item.盒号)){
                    $error.append("<p>第"+(i+2)+"条数据盒号格式有误</p>");
                    flag=false;
                }
                file.fileBoxNum=item.盒号;
                file.fileNum=item.文件编号;
                file.fileCompany=item.责任者;
                file.fileName=item.文件题名;
                try{
                    file.fileDate=format(item.文件日期);
                    if(file.fileDate==="false"){
                        $error.append("<p>第"+(i+2)+"条数据文件日期格式有误</p>");
                        flag=false;
                    }
                }catch(e) {
                    $error.append("<p>第"+(i+2)+"条数据文件日期格式有误</p>");
                    flag=false;
                }
                file.fileCount=item.备注;
                if(item.档案类型 in map){
                    file.fileTypeId=map[item.档案类型];
                }else {
                    $error.append("<p>第"+(i+2)+"条数据档案类型不存在</p>");
                    flag=false;
                }

                objects.push(file);
            }
            console.log(objects);
        };
        // 以二进制方式打开文件
        fileReader.readAsBinaryString(files[0]);
    });

    function format(filedate) {
        var times=filedate.split("/");
        var year=parseInt(times[2])>50?"19"+times[2]:"20"+times[2];
        if(parseInt(times[0])>12||parseInt(times[1])>31){
            return "false";
        }
        var month=times[0].length===1?"0"+times[0]:times[0];
        var day=times[1].length===1?"0"+times[1]:times[1];
        return year+"-"+month+"-"+day;
    }

    function fileBean(boxNum,fileNum,company,fileName,fileDate,remark,typeId) {
        this.fileBoxNum=boxNum;
        this.fileNum=fileNum;
        this.fileCompany=company;
        this.fileName=fileName;
        this.fileDate=fileDate;
        this.fileCount=remark;
        this.fileTypeId=typeId;
    }

    $("#submit").click(function () {
        if(!flag)return;
        if(objects.length===0){
            $error.html("");
            $error.append("<p>请选择文件</p>")
        }
        var json=JSON.stringify(objects);
        console.log(json);
        var data={
            projectId:$("#projectId").val(),
            result:json
        };

        $.post("${pageContext.request.contextPath}/file_batchSave.action",data,function (result) {
            if(result==="200"){
                goBack();
            }else {
                $error.html("");
                $error.append("<p>"+result+"</p>")
            }
        })
    });
    String.prototype.regJson=function(f,e){//吧f替换成e
        var reg=new RegExp(f,"g"); //创建正则RegExp对象
        return this.replace(reg,e);
    };
    function goBack() {
        var $id=$("#projectId").val();
        location.href="${pageContext.request.contextPath}/file_find.action?projectId="+$id;
    }

    -->
</script>
</body>
</html>
