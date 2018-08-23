<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<jsp:include page="meta.jsp"></jsp:include>


<title>添加用户 - H-ui.admin v3.1</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图书名称</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text"  class="input-text" value="${ data.book_name}" readonly="readonly" id="book_name" name="book_name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>作者</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${ data.book_author} " readonly="readonly" id="book_author" name="book_author">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>出版社</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${data.book_publish }" readonly="readonly" id="book_publish" name="book_publish">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="username" name="username">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" value="" placeholder="" id="password" name="password">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button class="btn btn-primary radius"  id ="btu1" type="button" value="&nbsp;&nbsp;确认借阅&nbsp;&nbsp;">确认借阅</button>
			</div>
		</div>
	</form>
</article>

<jsp:include page="footer.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="${ares}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${ares}/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${ares}/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${ares}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	

	$("#btu1").click(function(){
		
			var indexs = parent.layer.getFrameIndex(window.name);	
			var bookid = ${ data.book_id};
			var username = $("#username").val();
			var password = $("#password").val();

			$.ajax({
				type:"post",
				url:"${ lpath}/reader/finshlend",
				//${ lpath}/reader/finshlend
				dataType:"json",
				contentType : "application/json",
				
				data:JSON.stringify({"bookid":bookid,"username": username,"password":password}),
				success:function(d){
					//alert(d.msg);
					//alert(d.name);
					if(d.msg == "借阅成功"){
						layer.msg("读者"+d.name+"：您所需图书已经"+d.msg, {
							  time: 0 //不自动关闭
							  ,btn: ['确认',]
							  ,yes: function(index){
								parent.layer.close(indexs);
							  }
							});								
					}else{
						layer.alert(d.msg, {icon: 6});
					}
					
				},
				error:function(){},
			});

	});	
		
		
		
			
		
	
});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>