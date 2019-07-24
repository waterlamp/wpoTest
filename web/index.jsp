<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-08
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  </head>
  <style>
    body{
      background: url("${pageContext.request.contextPath}/img/backgroup.jpg") no-repeat 100%;
    }

    #top{
      color:white;
      font:50px "楷体";
    }
    #content>div{
      background-color:rgba(255,255,255,0.7);
      border-radius: 10px;
      padding: 30px 0 10px;
    }

  </style>
  <body>
  <div class="container-fluid">
    <div id="blank" class="row d-none d-md-block" style="height:120px"></div>
    <div id="top" class="row">
      <div class="col-12">
        <div class="h3 text-center">水中灯个人办公系统</div>
      </div>
    </div>
    <div class="row" id="content">
      <div class="col-12 col-sm-6 offset-sm-2 col-md-4 offset-md-4 shadow-lg">
        <form action="${pageContext.request.contextPath}/user_login.action" method="post" onsubmit="return check()">
          <div class="form-group row">
            <label class="col-3 col-form-label text-right" for="inputTel">手机号：</label>
            <div class="col-8">
              <input type="tel" class="form-control" name="userTel" id="inputTel" placeholder="请输入手机号">
            </div>
          </div>
          <div class="form-group row">
            <label class="col-3  col-form-label text-right" for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
            <div class="col-8 ">
              <input type="password" class="form-control" name="userPwd" id="password" placeholder="请输入密码">
            </div>
          </div>
          <div class="row">
            <div class="col-6">
              <div id="warning" style="color:red" class="text-center"><s:actionerror/></div>
            </div>
            <div class="col-5">
              <button type="submit" class="btn btn-primary" id="login" style="width:100%">登陆</button>
            </div>
          </div>
        </form>


      </div>
    </div>
  </div>
  <script>
    <!--
      function check() {
          if($("#inputTel").val()===""){
              $("#warning").html("请输入账号");
              $("#inputTel").focus();
              return false;
          }
          if($("#password").val()==""){
              $("#warning").html("请输入密码");
              $("#password").focus();
              return false;
          }
          return true;
      }
    -->
  </script>
  </body>
</html>
