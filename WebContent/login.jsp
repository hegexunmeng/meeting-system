<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>CoolMeeting会议管理系统</title>
<link rel="stylesheet" href="styles/common.css" />
</head>
<body>

	<div class="page-content">
		<div class="content-nav">登录</div>
		<form action="LoginServlet" method="post">
			<fieldset>
				<legend>登录信息</legend>

				<tr>
					<td>提示信息:</td>
					<td><font color='red'> ${requestScope.msg}</font></td>
				</tr>

				<table class="formtable" style="width: 50%">
					<tr>
						<td>账号名:</td>
						<td><input id="accountname" name="username" type="text" /></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input id="new" name="pwd" type="password" /></td>
					</tr>
					<tr>
						<td></td>
						<td><select id="timelength" name="timelength">
								<option value="0" selected>每次都需要登录</option>
								<option value="10">10天内</option>
								<option value="30">30天内</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" class="command"><input type="submit"
							value="登录" class="clickbutton" /> <input type="button"
							value="返回" class="clickbutton" onclick="window.history.back();" />
							<input type="button" value="注册" class="clickbutton"
							onclick="window.location.href='ViewAllDepartmentsServlet?code=regist'" />
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	</div>

</body>
</html>