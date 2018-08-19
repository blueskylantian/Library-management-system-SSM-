<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="${ares}/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${ares}/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="${ares}/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="${ares}/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="${ares}/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${ares}/lib/datatables/jquery.dataTables.css"/>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>查询</title>
</head>
<body>

	<div class="text-c">
		<div></div>
		<input type="text" name="text" id="tid" placeholder="书名	  作者   出版社"
			style="width: 600px" class="input-text">
		<button name="" id="but01" class="btn btn-success" type="button">
			<i class="Hui-iconfont"></i> 搜索
		</button>
	</div>
	<div id="did"
		style="border: 1px solid #C0C0C0; width: 598px; position: relative; left: 330px;"></div>
	<div>
		<table class="table table-border table-bordered table-bg" id="table">
			<thead>
				<tr>
					<th scope="col" colspan="9">员工列表</th>
				</tr>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="40">编号</th>
					<th width="150">图书名</th>
					<th width="90">类型</th>
					<th width="150">作者</th>
					<th>出版社</th>
					<th width="130">馆藏数量</th>
					<th width="100">是否可借出</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td><input type="checkbox" value="1" name=""></td>
					<td>1</td>
					<td>admin</td>
					<td>13000000000</td>
					<td>admin@mail.com</td>
					<td>超级管理员</td>
					<td>2014-6-11 11:11:42</td>
					<td class="td-status"><span class="label label-success radius">可以</span></td>
					<td class="td-manage"> <a style="text-decoration: none"
						onclick="admin_start(this,'10001')" href="javascript:;" title="启用"><i
							class="Hui-iconfont"></i>借阅</a> </td>
				</tr>
				<tr class="text-c">
					<td><input type="checkbox" value="2" name=""></td>
					<td>2</td>
					<td>zhangsan</td>
					<td>13000000000</td>
					<td>admin@mail.com</td>
					<td>栏目编辑</td>
					<td>2014-6-11 11:11:42</td>
					<td class="td-status"><span class="label radius">禁止</span></td>
					<td class="td-manage">
						<a style="text-decoration: none"
						onclick="admin_start(this,'10001')" href="javascript:;" title="启用"><i
							class="Hui-iconfont"></i></a> 
						<a title="编辑" href="javascript:;"
						onclick="admin_edit('管理员编辑','admin-add.html','2','800','500')"
						class="ml-5" style="text-decoration: none"><i
							class="Hui-iconfont"></i></a> 
						<a title="删除" href="javascript:;"
						onclick="admin_del(this,'1')" class="ml-5"
						style="text-decoration: none"><i class="Hui-iconfont"></i>
						</a>
						</td>
				</tr>
			</tbody>
		</table>
	</div>
	<script type="text/javascript" src="${ares}/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${ares}/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="${ares}/lib/datatables/jquery.dataTables.js"></script>
	
	<script type="text/javascript">
		$(function(){
			//文本框keyup的时候发送ajax
			$("#tid").keyup(function() {
				//获取文本框的值
				var $value = $(this).val();
				//内容为空的时候不发送ajax
				if ($value != null && $value != '') {
					//清空div
					$("#did").html("");

					$.post("${ lpath}/reader/tempquery", "kw=" + $value, function(d) {
						alert(d);
						//不为空的时候切割字符串
						if (d != '') {
							var arr = d.split(",");
							$(arr).each(function() {
								alert(this);
								//可以将每一个值放入一个div 将其内部插入到id为did的div中
								$("#did").append($("<div>" + this + "</div>"));
							});
							//将div显示
							$("#did").show();
						}
					},'json');

				} else {
					//内容为空的时候 将div隐藏 
					$("#did").hide();
				}
			});
			$('#table').DataTable({
		      "aaSorting": [[ 0, "asc" ]],//默认第几个排序
			  "searching": false, 
			  "aLengthMenu" : [ 10, 5, 1 ] , //更改显示记录数选项  
		      "bAutoWidth": true, //是否自适应宽度
		      "oLanguage": { //国际化配置
		    	    "sProcessing": "正在获取数据，请稍后...",
		    	    "sLengthMenu": "显示 _MENU_ 条",
		    	    "sZeroRecords": "没有您要搜索的内容",
		    	    "sInfo": "从 _START_ 到 _END_ 条记录 总记录数为 _TOTAL_ 条",
		    	    "sInfoEmpty": "记录数为0",
		    	    "sInfoFiltered": "(全部记录数 _TOTAL_ 条)",
		    	    "sInfoPostFix": "",
		    	    "sSearch": "搜索",
		    	    "sUrl": "",
		    	    "oPaginate": {
		    	    "sFirst": "第一页",
		    	    "sPrevious": "上一页",
		    	    "sNext": "下一页",
		    	    "sLast": "最后一页",
		    	    }
		    }
		});
		});
		</script>
		</body>
		</html>