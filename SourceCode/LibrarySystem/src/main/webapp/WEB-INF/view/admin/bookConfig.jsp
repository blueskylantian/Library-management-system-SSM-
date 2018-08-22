<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="meta.jsp"></jsp:include>
		<title>图书管理</title>
	</head>

	<body>
		<nav class="breadcrumb">
			<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
			</span> 用户中心 <span class="c-gray en">&gt;
			</span> 用户管理
			<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);"
			 title="刷新">
				<i class="Hui-iconfont">&#xe68f;</i>
			</a>
		</nav>
		<div class="page-container">
			<div class="text-c">
				<input type="text" class="input-text" style="width:250px" placeholder="图书名称" id="" name="">
				<button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜图书</button>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
					<a href="javascript:;" id="addbook" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
						添加图书</a></span>
				<div class="mt-20">
					<table class="table table-border table-bordered table-hover table-bg table-sort" id="table">
					</table>
				</div>
			</div>
			<jsp:include page="footer.jsp"></jsp:include>

			<!--请在下方写此页面业务相关的脚本-->
			<script type="text/javascript" src="${ares}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
			<script type="text/javascript" src="${ares}/lib/laypage/1.2/laypage.js"></script>
			<script src="${ares}/lib/datatables/bootstrap.min.js"></script>
			<script src="${ares}/lib/datatables/bootstrap-table.min.js"></script>
			<script src="${ares}/lib/datatables/bootstrap-table-zh-CN.js"></script>
			<script type="text/javascript">
				$(function () {
					//$("#table").bootstrapTable('destroy');
					$('#table').bootstrapTable({
						ajax: function (request) {
							$.ajax({
								type: "POST",
								url: "${ lpath}/admin/showbooks",
								contentType: "application/json;charset=utf-8",
								dataType: "json",
								//data:JSON.stringify({"username": username,"password":password}),	
								success: function (d) {
									row: d
									$('#table').bootstrapTable('load', d);
									$("#table").bootstrapTable('hideLoading'); //隐藏正在加载
								},
								error: function () {
									alert("错误");
								}
							});
						},
						//search : true,     //打开bootstraptable的自带搜索框
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
							"fidld": 'book_id',
							"title": 'bookid',
							visible: false
						}, {
							"field": 'book_num',
							"title": '图书编号',
						}, {
							"field": 'book_name',
							"title": '图书名',
						}, {
							"field": 'type.type_name',
							"title": '类型',
						}, {
							"field": 'book_author',
							"title": '作者',
						}, {
							"field": 'book_publish',
							"title": '出版社'
						}, {
							"field": 'book_price',
							"title": '图书价格'
						}, {
							"field": "book_amount",
							"title": '库藏数量'
						},
						{
							"field": "remark",
							"title": '备注'

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
					var a = '<a style="text-decoration: none"title="编辑" id="edit" style="text-decoration:none"><i class="Hui-iconfont"></i></a>';
					var b = '<a style="text-decoration: none"title="删除" id="del" style="text-decoration:none"><i class="Hui-iconfont"></i></a>';
					return [
						a + b
					].join("")
				}
				window.operateEvents = {
					"click #edit": function (e, value, row, index) {
						//layer.alert("请到管理员处消除违规状态在进行借阅",{icon: 6});
						//alert("ok");
						var id = row.book_id;
						var url = "${ lpath}/admin/editbook?bookid=" + id;
						layer_show("修改图书信息", url, '', '580');
					},
					"click #del": function (e, value, row, index) {
						var id = row.book_id;
						layer.confirm('确认要删除吗？', function (index) {
							$.ajax({
								type: 'POST',
								url: '${ lpath}/admin/delBook',
								contentType: "application/json;charset=utf-8",
								dataType: "json",
								data:JSON.stringify({"id": id}),
								success: function (data) {
									var msg = data.msg;
									if(msg.indexOf("成功") != -1){
									$('#table').bootstrapTable('remove',{
										"field":"book_id",
										"values":[parseInt(id)],
										});
									layer.msg('已删除!', { icon: 1, time: 1000 });				
									}
								},
								error: function (data) {
									alert("错误");
								},
							});
						});
					},
				}
				$("#addbook").click(function(){
						var id = '';
						var url = "${ lpath}/admin/editbook?bookid=" + id;
						layer_show("新增图书信息", url, '', '580');
				});
				/*用户-删除*/
				function member_del(obj, id) {
					layer.confirm('确认要删除吗？', function (index) {
						$.ajax({
							type: 'POST',
							url: '',
							dataType: 'json',
							success: function (data) {
								$(obj).parents("tr").remove();
								layer.msg('已删除!', { icon: 1, time: 1000 });
							},
							error: function (data) {
								console.log(data.msg);
							},
						});
					});
				}
			</script>
	</body>

	</html>