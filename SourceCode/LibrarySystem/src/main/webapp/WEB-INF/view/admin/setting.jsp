<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	系统管理
	<span class="c-gray en">&gt;</span>
	基本设置
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<form class="form form-horizontal" id="form">
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>基本设置</span>
				<span>公告</span>
				<span>数据备份</span>
				<span>其他设置</span>
			</div>
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						可借阅的天数：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" type="hidden;" id="setting_id" name="setting_id"  value="${ data.setting_id}" class="input-text">
						<input type="text" id="lend_days" name="lend_days" placeholder="" value="${ data.lend_days}" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						可续借的次数：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="lend_num" name="lend_num" placeholder="" value="${ data.lend_num}" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						老师可借阅的图书数量：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="teacher_num" name="teacher_num" placeholder="" value="${ data.teacher_num}" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						学生可借阅的图书数量：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="student_num" name="student_num" placeholder="" value="${ data.student_num}" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						每逾期一天所付的罚款：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="fine" name="fine" placeholder="" value="${ data.fine}" class="input-text">
					</div>
				</div>
			</div>
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">公告</label>
					<div class="formControls col-xs-8 col-sm-9">
						<textarea class="textarea" name="remark" id="remark">${ data.remark}</textarea>
					</div>
				</div>
			</div>
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">邮件发送模式：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text"  class="input-text" value="" id="" name="">
					</div>
				</div>							
			</div>
			<div class="tabCon" >
				<div style="text-align:left">
					<button id="backSetting"  class="btn btn-secondary radius" type="button"> 退回到上一次设置</button>
				</div>
				<div>
					<button id="defaultSetting"  class="btn btn-warning radius" type="button"> 还原默认设置</button>
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
	      url: "${lpath}/admin/saveSetting",
	      data:JSON.stringify($("#form").serializeJSON()),
	      dataType: "json",
	      contentType : "application/json;charset=utf-8",
	      success: function (data) {
	    	  layer.msg(data.msg, { icon: 1, time: 1000 });
	      }
	    });
	  });
	$("#defaultSetting").click(function(){
		alert(JSON.stringify($("#form").serializeJSON()));
	    $.ajax({
	      type: "post",
	      url: "${lpath}/admin/defaultSetting",
	      //data:JSON.stringify($("#form").serializeJSON()),
	      dataType: "json",
	      contentType : "application/json;charset=utf-8",
	      success: function (data) {
	    	  layer.msg(data.msg, { icon: 1, time: 1000 });
	      }
	    });
	  });
	$("#backSetting").click(function(){
		alert(JSON.stringify($("#form").serializeJSON()));
	    $.ajax({
	      type: "post",
	      url: "${lpath}/admin/backSetting",
	      //data:JSON.stringify($("#form").serializeJSON()),
	      dataType: "json",
	      contentType : "application/json;charset=utf-8",
	      success: function (data) {
	    	  layer.msg(data.msg, { icon: 1, time: 1000 });
	      }
	    });
	  });
});
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
