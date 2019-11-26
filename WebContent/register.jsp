<%@ page language="java" import="java.util.*,com.meeting.vo.*"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CoolMeeting会议管理系统</title>
<link rel="stylesheet" href="styles/common.css" />
<style type="text/css">
</style>
<script type="text/javascript">
	var xmlHttp;

	function createXMLHttpRequest() {
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
	}

	function validate() {
		createXMLHttpRequest();
		var username = document.getElementById("username");
		var url = "ValidateUsernameServlet?username=" + escape(username.value);
		xmlHttp.open("GET", url, true);
		xmlHttp.onreadystatechange = callback;
		xmlHttp.send(null);
	}

	function callback() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var message = xmlHttp.responseXML
						.getElementsByTagName("message")[0].firstChild.data;
				var passed = xmlHttp.responseXML.getElementsByTagName("passed")[0].firstChild.data;
				setMessage(message, passed);
			}
		}
	}

	function setMessage(message, passed) {
		var validateMessage = document.getElementById("validateMessage");
		var fontColor = "red";
		if (passed == "true") {
			fontColor = "green";
		}
		validateMessage.innerHTML = "<font color=" + fontColor + ">" + message
				+ " </font>";
	}

	function check() {
		if (form1.firstpassword.value != form1.secondpassword.value) {
			confirminfo.innerHTML = "<font color=red>两次输入的密码不相符</font>";
		} else {
			confirminfo.innerHTML = "<font color=green>两次输入的密码相符</font>";
		}
	}
	
	function checkPhone(){ 
	    var phone = document.getElementById('phone').value;
	    if(!(/^1[3456789]\d{9}$/.test(phone))){ 
	        alert("手机号码格式有误，请重填");  
	        return false; 
	    } 
	}
	

</script>

</head>
<body>
	<div class="page-header">
		<div class="header-banner">
			<img src="images/header.png" alt="CoolMeeting" />
		</div>
		<div class="header-title">欢迎访问Cool-Meeting会议管理系统</div>
	</div>
	<div class="page-content">
		<div class="content-nav">人员管理 > 员工注册</div>
		<form name="form1" action="RegistServlet" method="post">
			<fieldset>
				<legend>员工信息</legend>

				<tr>
					<td>提示信息:</td>
					<td><font color='red'>${requestScope.msg}</font></td>
				</tr>

				<table class="formtable" style="width: 50%">
					<tr>
						<td>姓名：</td>

						<td><input type="text" id="employeename" name="employeename"
							maxlength="20" value="${param.employeename}"></td>
					</tr>
					<tr>
						<td>账户名：</td>

						<td><input type="text" id="username" name="username"
							maxlength="20" value="${param.username}" onchange="validate()">
							<div id="validateMessage"></div></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input type="password" id="firstpassword" name="password"
							maxlength="20" placeholder="请输入6位以上的密码"></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input type="password" id="secondpassword"
							name="password" maxlength="20" onchange="check()" />
							<div id="confirminfo"></div></td>
					</tr>
					<tr>
						<td>联系电话：</td>

						<td><input type="text" id="phone" name="phone" maxlength="20"
							value="${param.phone}" onchange="checkPhone()" ></td>
					</tr>
					<tr>
						<td>电子邮件：</td>

						<td><input type="text" id="email" name="email" maxlength="20"
							value="${param.email}" ></td>
					</tr>

					<tr>
						<td>所在部门：</td>
						<td>
						<select name="deptid">
								<option>请选择部门</option>
								<c:forEach var="department" items="${requestScope.departmentsList}">
										<option value="${department.departmentid}">${department.departmentname}</option>									
								</c:forEach>
						</select>
						</td>
					</tr>

					<tr>
						<td colspan="6" class="command"><input type="submit"
							class="clickbutton" value="注册" /> <input type="reset"
							class="clickbutton" value="重置" /></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	</div>
	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
</body>
</html>