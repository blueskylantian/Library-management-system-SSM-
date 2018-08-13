<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<form action="user/login.action" method="post">
<label>账号：</label>
<input type="text" id="txtUsername" name="username" placeholder="请输入账号" /><br/>
<label>密码：</label>
<input type="password" id="txtPassword" name="password" placeholder="请输入密码" /><br/>
<input type="submit" value="提交" />
<input type="reset" value="重置" />
</form>
</body>
</html>