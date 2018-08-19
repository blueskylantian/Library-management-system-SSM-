<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE HTML>
	<html>

	<head>
		<meta charset="utf-8">
		<jsp:include page="meta.jsp"></jsp:include>

		<link rel="stylesheet" type="text/css" href="${ares}/lib/datatables/bootstrap-table.css" />
		<link rel="stylesheet" type="text/css" href="${ares}/lib/datatables/bootstrap.min.css" />
		
		<title>查询</title>
	</head>

	<body>
		<div class="text-c">
			<div></div>
			<input type="text" name="text" id="tid" placeholder="书名   作者   出版社" style="width: 600px" class="input-text">
			<button name="" id="but01" class="btn btn-success" type="button">
				<i class="Hui-iconfont"></i> 搜索
			</button>
		</div>
		<div id="did" style="background-color:#fff;border: 1px solid #C0C0C0; width: 598px; position: relative; left: 330px;z-index:999;position: absolute;">
		</div>
		<div>
			<table class="table table-border table-bordered table-bg" id="table">
			</table>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
		<script src="${ares}/lib/datatables/bootstrap.min.js"></script>
		<script src="${ares}/lib/datatables/bootstrap-table.min.js"></script>
		<script src="${ares}/lib/datatables/bootstrap-table-zh-CN.js"></script>
		<script type="text/javascript">
			function use(id) {
				//alert(id);
				//var tempquery = document.getElementById("tid");
				$("#tid").val("" + id + "");
				$("#did").hide();
			}
			function AddFunctionAlty(value, row, index) {
				return [
					'<a style="text-decoration: none"title="启用" id="borrow"><i class="Hui-iconfont"></i>借阅</a>'
				].join("")
			}
			window.operateEvents = {
				"click #borrow": function (e, value, row, index) {
					if (row.book_amount > 2) {

						var id = row.book_id;
						var url = "${ lpath}/reader/lendbook?id="+id;
						//	url: "${ lpath}/reader/queryById?id=" + id,
						layer_show("借阅图书",url,'','510');
					} else {
						layer.alert('馆藏数量太少，无法借阅', {icon: 6});
					}
				},
			} 
		</script>
		<script type="text/javascript">
			$(function () {
				//文本框keyup的时候发送ajax
				$("#tid").keyup(function () {
					//获取文本框的值
					var $value = $(this).val();
					//内容为空的时候不发送ajax
					if ($value != null && $value != '') {
						//清空div
						$("#did").html("");

						$.post("${ lpath}/reader/tempquery", "kw=" + $value, function (d) {
							if (d != '') {
								var i = 0;
								$(d).each(function () {

									$("#did").append($("<div><a id='" + d[i] + "' onclick=use('" + d[i] + "')>" + d[i] + "</a></div>"));
									i++;
								});
								i = 0;
								//将div显示
								$("#did").show();
							}
						}, 'json');

					} else {
						//内容为空的时候 将div隐藏 
						$("#did").hide();
					}
				});
				$("#but01").click(function () {
					var value = $("#tid").val();
					//alert(value);
					$("#table").bootstrapTable('destroy');
					$('#table').bootstrapTable({
						method: 'post',
						url: "${ lpath}/reader/query?name=" + value,
						contentType: "application/x-www-form-urlencoded",
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
							"title": 'id',
							visible: false
						}, {
							"field": 'book_num',
							"title": '编号',
						}, {
							"field": 'book_name',
							"title": '图书名',
						}, {
							"field": 'book_style',
							"title": '类型',
						}, {
							"field": 'book_author',
							"title": '作者',
						}, {
							"field": 'book_publish',
							"title": '出版社'
						}, {
							"field": 'book_amount',
							"title": '馆藏数量'
						}, {
							"title": '状态',
							"class": "td-manage",
							formatter: function (value, row, index) {
								var amount = row.book_amount;
								if (amount <= 2) {
									var a = '<span class="label radius">禁止借阅</span>';
								} else {
									var a = '<span class="label label-success radius">可以借阅</span>';
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
					$("#did").hide();
				});

			});
		</script>
	</body>

	</html>