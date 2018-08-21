<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link href="${ares}/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ares}/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="${ares}/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ares}/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

<title>后台登录 </title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" action="" method="post">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="username" name="username" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <button id="btu1" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">登陆</button>
          <button name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">取消</button>
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">大学生图书借阅管理系统</div>
<jsp:include page="footer.jsp"></jsp:include>
<script type="text/javascript" src="${ares}/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${ares}/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript">
	$("#btu1").click(function(){
    var username = $("#username").val();
	var password = $("#password").val();
    $.ajax({
      type: "post",
      url: "${lpath}/admin/login",
      data:JSON.stringify({"username": username,"password":password}),
      dataType: "json",
      contentType : "application/json",
      success: function (data) {
    	var msg = data.msg;  
    	if(msg.indexOf("成功") != -1){
    		window.location.href="${lpath}/admin"
    	}else{
    		layer.alert(msg, {icon: 6});
    	} 
       
      }
    });
  });
</script>
</body>
</html>