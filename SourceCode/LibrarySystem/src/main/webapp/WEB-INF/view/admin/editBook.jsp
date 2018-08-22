<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="meta.jsp"></jsp:include>
<title>基本设置</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span>
	图书管理
	<span class="c-gray en"></span>
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<form class="form form-horizontal" id="form">
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span> ${data.book_id ==''?'新增图书信息':'修改图书信息'}</span>
			</div>
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						图书编号：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="lend_days" name="book_num" placeholder="" value="${ data.book_num}" class="input-text">
						<input type="text" style="display:none" id="book_id" name="book_id"  value="${ data.book_id}">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						图书名称：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="book_name" name="book_name" placeholder="" value="${ data.book_name}" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						图书类型：
					</label>
					<div class="formControls col-xs-8 col-sm-9">
						<span class="select-box" style="width:150px">
							<select class="select" name="type_id" size="1">
							<c:forEach var="list" items="${ types}" varStatus="x">
								<option  value="${ list.type_id}" <c:if test="${ list.type_name == data.type.type_name}">selected = "selected"</c:if>>${ list.type_name}</option>
							</c:forEach>
							</select>
						</span>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						图书作者：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="book_author" name="book_author" placeholder="" value="${ data.book_author}" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						出版社：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="book_publish" name="book_publish" placeholder="" value="${ data.book_publish}" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						图书价格：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="book_price" name="book_price" placeholder="" value="${ data.book_price}" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						馆藏数量：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="book_amount" name="book_amount" placeholder="" value="${ data.book_amount}" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						备注：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="remark" name="remark" placeholder="" value="${ data.remark}" class="input-text">
					</div>
				</div>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button id="btu1"  class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${ares}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${ares}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${ares}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${ares}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${ares}/lib/datatables/bootstrap.min.js"></script>
<script type="text/javascript" src="${ares}/lib/datatables/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${ares}/lib/datatables/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript">
$(function(){
	var indexs = parent.layer.getFrameIndex(window.name);	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#tab-system").Huitab({
		index:0
	});
	$("#btu1").click(function(){
		alert(JSON.stringify($("#form").serializeJSON()));
	    $.ajax({
	      type: "post",
	      url: "${lpath}/admin/savebook",
	      data:JSON.stringify($("#form").serializeJSON()),
	      dataType: "json",
	      contentType : "application/json;charset=utf-8",
	      success: function (data) {
	    		layer.confirm(data.msg, function (index) {
	    			window.parent.location.reload(); //刷新父页面
	    			parent.layer.close(indexs);
				});
	      }
	    });
	  });
});
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
