<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-02-27
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body style="background-color:#d1ecf1">
<div style="padding-top: 10%;padding-left: 10%;width: 600px">
    <form action="${pageContext.request.contextPath}/order_newOrder.action">
        <div class="form-group row">
            <label for="orderType" class="col-sm-4 col-form-label">请选择工单类型：</label>
            <div class="col-sm-8">
                <select id="orderType" class="custom-select" name="orderTypeId"></select>
            </div>
        </div>
        <div class="form-group row">
            <label for="orderName" class="col-sm-4 col-form-label">工单名称：</label>
            <div class="col-sm-8">
                <input type="text" name="orderName" id="orderName" class="form-control">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">起草</button>

    </form>
</div>
<script>
    var path="${pageContext.request.contextPath}";
    $(function () {
        $.get(path+"/order_getOrderTypes.action",function (result) {
            var $select=$("#orderType");
            var obj=JSON.parse(result);
            $.each(obj,function (i,item) {
                $select.append("<option value='"+item.ordertypeId+"'>"+item.ordertypeName+"</option>");
            })
        })
    })
</script>
</body>
</html>
