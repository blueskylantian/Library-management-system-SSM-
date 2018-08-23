<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="meta.jsp"></jsp:include>
<title>主页</title>
</head>
<body>
	<nav class="breadcrumb"><i class="Hui-iconfont"></i> 首页
	<span class="c-gray en">&gt;</span>
	读者还书
	<span class="c-gray en"></span>
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont"></i></a>
	</nav>
	<div class="text-c"> 读书编号:
		<input type="text" class="input-text" id="book_num" name="book_num" style="width:150px">
		读者账号:
		<input type="text" class="input-text" id="username" name="username" style="width:150px">
		<input type="hidden" id="" name="">
		<button type="button" class="btn btn-primary radius" id="tid" name="tid">查找图书</button>
	</div>
	<div class="mt-20">
			<table id="table"
				class="table table-border table-bordered table-hover table-bg table-sort">
			</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript" src="${ares}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript" src="${ares}/lib/laypage/1.2/laypage.js"></script>
	<script src="${ares}/lib/datatables/bootstrap.min.js"></script>
	<script src="${ares}/lib/datatables/bootstrap-table.min.js"></script>
	<script src="${ares}/lib/datatables/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript">
	$("#tid").click(function(){
		$("#table").bootstrapTable('destroy');
		var username = $("#username").val();
		var book_num = $("#book_num").val();
		$('#table').bootstrapTable({
			ajax : function (request){
		        $.ajax({
		            type : "POST",
		            url : "${ lpath}/admin/showReaderBooks",
					contentType: "application/json;charset=utf-8",
					dataType:"json",
					data:JSON.stringify({"username": username,"book_num":book_num}),	
		            success : function (d) {
		            	var temp = d.msg;
							if(temp != null && temp != ""){
								layer.alert(d.msg, {icon: 6});
							}else{  
								//$("#div1 input").val("");
		                   	 	//alert("给table发送数据");
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
			sortOrder: "dec",     //排序方式
			pageNumber: 1,      //初始化加载第一页，默认第一页
			queryParamsType: '', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
			// 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
			sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
			strictSearch: true,
			clickToSelect: true,    //是否启用点击选中行
			searchOnEnterKey: true,
			pagination: true,
			uniqueId: "id", // 每一行的唯一标识，一般为主键列
			"pageSize": 10,      //每页的记录行数（*）
			"pageList": [10, 25, 50, 100],  //可供选择的每页的行数（*）
			"order": [[0, "dec"]],
			"columns": [{
				"fidld": 'borrow_id',
				"title": 'id',
				visible: false
			}, {
				"fidld": 'book.book_id',
				"title": 'bookid',
				visible: false
			}, {
				"fidld": 'reader.reader_id',
				"title": 'readerid',
				visible: false
			}, {
				"field": 'reader.reader_name',
				"title": '读者姓名',
			},{
				"field": 'book.book_num',
				"title": '图书编号',
			},{
				"field": 'book.book_name',
				"title": '图书名',
			}, {
				"field": 'book.book_author',
				"title": '作者',
			}, {
				"field": 'book.book_price',
				"title": '图书价格'
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
						var a = '<span class="label label-success radius">正常</span>';
					}else{
						var a = '<span class="label  radius">已归还</span>';
					}
					return a;
				}
			}, {
				"field": '超链接',
				"title": '操作',
				"events": 'operateEvents',
				"formatter": 'AddFunctionAlty',
			},],
			
		});
	});
	function AddFunctionAlty(value, row, index) {
		//class="btn btn-danger radius"   红色按钮
		//class="btn btn-primary radius"  蓝色按钮
		//btn btn-success radius          绿色按钮
		var b = '<a class="btn btn-danger radius size-S" style="text-decoration: none"title="丢失" id="lost">丢失</a>'
		if(row.borrow_type == -1){
		var a = '<a class="btn btn-primary radius size-S" style="text-decoration: none"title="罚款" id="topay">罚款</a>&nbsp&nbsp&nbsp' + b;
		}else if(row.borrow_type == 0){
		var a = '<a class="btn btn-success radius size-S"  style="text-decoration: none"title="归还" id="return">归还</a>&nbsp&nbsp&nbsp' + b;
		}
		return [
			a
		].join("")
	}
	window.operateEvents = {
		"click #return": function (e, value, row, index){
			var borrowid = row.borrow_id;
			//var url = "${ lpath}/admin/returnbook?borrowid="+borrowid;
			layer.confirm('确认归还？', function (index) {
				$.ajax({
					type: 'POST',
					url: '${ lpath}/admin/returnbook',
					contentType: "application/json;charset=utf-8",
					dataType: "json",
					data:JSON.stringify({"id": borrowid}),
					success: function (data) {
						var msg = data.msg;
						if(msg.indexOf("成功") != -1){
						$('#table').bootstrapTable('remove',{
							"field":"borrow_id",
							"values":[parseInt(borrowid)],
							});
						layer.msg('还书成功!', { icon: 1, time: 1000 });				
						}
					},
					error: function (data) {
						alert("错误");
					},
				});
			});
			//layer_show("罚款",url,'','510');			
		},
		"click #topay": function (e, value, row, index){
			var borrowid = row.borrow_id;
			$.ajax({
				type: 'POST',
				url: '${ lpath}/admin/topay',
				contentType: "application/json;charset=utf-8",
				dataType: "json",
				data:JSON.stringify({"id": borrowid}),
				success: function (data) {
					var fine = data.fine;
					layer.confirm('需缴纳罚款：'+fine, function (index){
						$.ajax({
							type: 'POST',
							url: '${ lpath}/admin/returnbook',
							contentType: "application/json;charset=utf-8",
							dataType: "json",
							data:JSON.stringify({"id": borrowid}),
							success: function (data) {
								var msg = data.msg;
								if(msg.indexOf("成功") != -1){
								$('#table').bootstrapTable('remove',{
									"field":"borrow_id",
									"values":[parseInt(borrowid)],
									});
								layer.msg('还书成功!', { icon: 1, time: 1000 });				
								}
							},
							error: function (data) {
								alert("错误");
							},
						});
					});
				},
				error: function (data) {
					alert("错误");
				},
			});
		},
		"click #lost": function (e, value, row, index){
			var borrowid = row.borrow_id;
			$.ajax({
				type: 'POST',
				url: '${ lpath}/admin/topay',
				contentType: "application/json;charset=utf-8",
				dataType: "json",
				data:JSON.stringify({"id": borrowid}),
				success: function (data) {
					var fine = data.fine;
					var book_price = row.book.book_price;
					alert(data.fine);
					var fines = parseInt(fine)+book_price;
					layer.confirm('需缴纳罚款：'+fines, function (index){
						$.ajax({
							type: 'POST',
							url: '${ lpath}/admin/lost',
							contentType: "application/json;charset=utf-8",
							dataType: "json",
							data:JSON.stringify({"id": borrowid}),
							success: function (data) {
								var msg = data.msg;
								if(msg.indexOf("成功") != -1){
								$('#table').bootstrapTable('remove',{
									"field":"borrow_id",
									"values":[parseInt(borrowid)],
									});
								layer.msg('还书成功!', { icon: 1, time: 1000 });				
								}
							},
							error: function (data) {
								alert("错误");
							},
						});
					});
				},
				error: function (data) {
					alert("错误");
				},
			});
		},
	}
</script>
</body>
</html>