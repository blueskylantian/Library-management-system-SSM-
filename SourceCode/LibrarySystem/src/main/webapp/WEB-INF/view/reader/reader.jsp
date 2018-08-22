<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- meta部分 -->
<jsp:include page="meta.jsp"></jsp:include>
<title>个人信息&续借</title>
<!-- header部分 -->
</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	更多操作 <span class="c-gray en">&gt;</span> 个人信息&续借 <a
		class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a> </nav>
	<div class="page-container">
		<div class="text-c" id="div1">
			<input type="text" name="username" id="username" placeholder="用户名"
				style="width: 250px" class="input-text"> <input
				type="password" name="password" id="password" placeholder="密码"
				style="width: 250px" class="input-text">
			<button name="tid" id="tid" class="btn btn-success" type="button">
				<i class="Hui-iconfont">&#xe665;</i> 查询
			</button>
		</div>
		<div class="mt-20">
			<table id="table"
				class="table table-border table-bordered table-hover table-bg table-sort">
			</table>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript" src="${ares}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript" src="${ares}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript" src="${ares}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript" src="${ares}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript" src="${ares}/lib/laypage/1.2/laypage.js"></script>
	<script src="${ares}/lib/datatables/bootstrap.min.js"></script>
	<script src="${ares}/lib/datatables/bootstrap-table.min.js"></script>
	<script src="${ares}/lib/datatables/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript">
$("#tid").click(function () {
	var username = $("#username").val();
	var password = $("#password").val();
	//var value = $("#tid").val();
	//alert(value);
	$("#table").bootstrapTable('destroy');
	$('#table').bootstrapTable({
		ajax : function (request) {
	        $.ajax({
	            type : "POST",
	            url : "${ lpath}/reader/queryReaderbooks",
				contentType: "application/json;charset=utf-8",
				dataType:"json",
				data:JSON.stringify({"username": username,"password":password}),	
	            success : function (d) {
	            	var temp = d.msg;
						if(temp == "账号或密码错误"){
							layer.alert(d.msg, {icon: 6});
						}else{  
							$("#div1 input").val("");
	                   	 	alert("给table发送数据");
	                   	 	row:d
	                		$('#table').bootstrapTable('load', d);
							$("#table").bootstrapTable('hideLoading'); //隐藏正在加载
						}   
	            },
				error:function(){
					alert("错误");
				}
	        });
		},
		striped: true,      //是否显示行间隔色
		cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: true,     //是否显示分页（*）
		sortable: false,      //是否启用排序
		sortOrder: "asc",     //排序方式
		pageNumber: 1,      //初始化加载第一页，默认第一页
		queryParamsType: '', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
		// 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
		sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
		strictSearch: true,
		clickToSelect: true,    //是否启用点击选中行
		searchOnEnterKey: true,
		uniqueId: "id", // 每一行的唯一标识，一般为主键列
		"pageSize": 10,      //每页的记录行数（*）
		"pageList": [10, 25, 50, 100],  //可供选择的每页的行数（*）
		"order": [[0, "asc"]],
		"columns": [{
			"fidld": 'borrow_id',
			"title": 'id',
			visible: false
		}, {
			"fidld": 'book.book_id',
			"title": 'bookid',
			visible: false
		}, {
			"field": 'book.book_name',
			"title": '图书名',
		}, {
			"field": 'book.book_author',
			"title": '作者',
		}, {
			"field": 'book.book_publish',
			"title": '出版社'
		},{
			"field": 'borrow_date',
			"title": '借阅日期'
		},{
			"field": 'borrow_return',
			"title": '归还日期'
		},{
			"title": '状态',
			"class": "td-manage",
			formatter: function (value, row, index) {
				var amount = row.borrow_type;

				if (amount == -1) {
					var a = '<span class="label radius">逾期</span>';
				} else if(amount == 0){
					var a = '<span class="label label-success radius">未逾期</span>';
				}else{
					var a = '<span class="label label-success radius">已归还</span>';
				}
				return a;
			}
		}, {
			"field": '超链接',
			"title": '操作',
			"events": 'operateEvents',
			"formatter": 'AddFunctionAlty',
		},],
		pagination: true
	});
});
function AddFunctionAlty(value, row, index) {
	if(row.borrow_type == 0){
	return [
		'<a style="text-decoration: none"title="续借" id="borrow"><i class="Hui-iconfont"></i>续借</a>'
	].join("")}
}
window.operateEvents = {
	"click #borrow": function (e, value, row, index){
	
		//layer.alert("请到管理员处消除违规状态在进行借阅",{icon: 6});
		
		var id = row.book.book_id;
		var url = "${ lpath}/reader/lendbook?id="+id;
		layer_show("借阅图书",url,'','510');	
				
	},
}
</script>
</body>
</html>