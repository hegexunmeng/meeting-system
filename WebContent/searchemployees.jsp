<%@ page language="java"
	import="java.util.*,com.meeting.vo.*" pageEncoding="utf-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CoolMeeting会议管理系统</title>
<link rel="stylesheet" href="styles/common03.css" />
<style type="text/css">
</style>

<script type="text/javascript">
        function goToOnePage(employeename,username,status) {
           var pageNum=document.getElementById("pageNum").value;
           if(pageNum==""){
           	 window.location.href="#";    
           }else{
          	 window.location.href="SearchEmployeesServlet?employeename="+employeename+"&username="+username+"&status="+status+"&pageNum="+pageNum;        
           }
        }
        </script>
</head>
<body>
	<div class="page-content">
		<div class="content-nav">会议预定 >搜索员工</div>
		<form method="post" action="SearchEmployeesServlet">
			<fieldset>
				<legend>搜索员工</legend>
				<table class="formtable">
					<tr>
						<td>姓名：</td>
						<td><input type="text" id="employeename" name="employeename"
							value="${param.employeename}" maxlength="20" /></td>
						<td>账号名：</td>
						<td><input type="text" id="username" name="username"
							value="${param.username}" maxlength="20" /></td>
						<td>状态：</td>
						<td><c:if test="${param.status eq null or param.status eq 3}">
								<input type="radio" id="status" name="status" value="1" />
								<label>已批准</label>
								<input type="radio" id="status" name="status" value="0" />
								<label>待审批</label>
								<input type="radio" id="status" name="status" value="2" />
								<label>已关闭</label>
								<input type="radio" id="status" name="status" value="3"	checked>
								<label>所有</label>
							</c:if> <c:if test="${param.status eq '1'}">
								<input type="radio" id="status" name="status" value="1" checked />
								<label>已批准</label>
								<input type="radio" id="status" name="status" value="0" />
								<label>待审批</label>
								<input type="radio" id="status" name="status" value="2" />
								<label>已关闭</label>
								<input type="radio" id="status" name="status" value="3" />
								<label>所有</label>
							</c:if> <c:if test="${param.status eq '0'}">
								<input type="radio" id="status" name="status" value="1" />
								<label>已批准</label>
								<input type="radio" id="status" name="status" value="0" checked />
								<label>待审批</label>
								<input type="radio" id="status" name="status" value="2" />
								<label>已关闭</label>
								<input type="radio" id="status" name="status" value="3" />
								<label>所有</label>
							</c:if> <c:if test="${param.status eq '2'}">
								<input type="radio" id="status" name="status" value="1" />
								<label>已批准</label>
								<input type="radio" id="status" name="status" value="0" />
								<label>待审批</label>
								<input type="radio" id="status" name="status" value="2" checked />
								<label>已关闭</label>
								<input type="radio" id="status" name="status" value="3" />
								<label>所有</label>
							</c:if></td>
					</tr>
					<tr>
						<td colspan="6" class="command"><input type="submit"
							class="clickbutton" value="查询" /> <input type="reset"
							class="clickbutton" value="重置" /></td>
					</tr>
				</table>
			</fieldset>
		</form>

		<c:if test="${requestScope.search eq 1 }">
			<div>
				<h3 style="text-align:center;color:black">查询结果</h3>
				<div class="pager-header">
					<div class="header-info">
					<!-- 取值 -->
						共<span class="info-number">${requestScope.countOfEmployees}</span>条结果，
						分成<span class="info-number">${requestScope.countOfPages}</span>页显示，
						当前第<span class="info-number">${requestScope.pageNum}</span>页
					</div>
					<div class="header-nav">
					<!-- pageNum=1 就是首页 -->
						<input type="button" class="clickbutton" value="首页"							
							onclick="window.location.href='SearchEmployeesServlet?employeename=${param.employeename}&username=${param.username}&status=${param.status}&pageNum=1'" />
						<!-- 这里要判断不是1也就是不是首页的时候,才会有上一页   pageNum-1-->
						<c:if test="${requestScope.pageNum ne '1'}">
							<input type="button" class="clickbutton" value="上页"
								onclick="window.location.href='SearchEmployeesServlet?employeename=${param.employeename}&username=${param.username}&status=${param.status}&pageNum=${requestScope.pageNum-1}'" />
						</c:if>
						<!-- pageNum+1 下一页 -->
						<c:if test="${requestScope.pageNum ne requestScope.countOfPages}">
							<input type="button" class="clickbutton" value="下页"
								onclick="window.location.href='SearchEmployeesServlet?employeename=${param.employeename}&username=${param.username}&status=${param.status}&pageNum=${requestScope.pageNum+1}'" />
						</c:if>
						
						<input type="button" class="clickbutton" value="末页"
							onclick="window.location.href='SearchEmployeesServlet?employeename=${param.employeename}&username=${param.username}&status=${param.status}&pageNum=${requestScope.countOfPages}'" />
						<!-- 自己跳转页数 -->
						跳到第<input type="text" id="pageNum" name="pageNum"
							class="nav-number"  value=${param.pageNum}>页 <input type="button" class="clickbutton"
							value="跳转"
							onclick="goToOnePage('${param.employeename}','${param.username}','${param.status}')" />
					</div>
				</div>
			</div>
			<table class="listtable">
				<tr class="listheader">
					<th>姓名</th>
					<th>账号名</th>
					<th>联系电话</th>
					<th>电子邮件</th>
					<th>操作</th>
				</tr>
				<!-- 显示当前页面的结果 -->
				<c:forEach var="emp" items="${requestScope.employeesList}">
					<tr>
						<td>${emp.employeename}</td>
						<td>${emp.username}</td>
						<td>${emp.phone}</td>
						<td>${emp.email}</td>
						<c:if test="${emp.status eq '2' }">
							<td>账号已关闭</td>
						</c:if>
						<c:if test="${emp.status ne '2' }">
							<td><a class="clickbutton"
								href="ApproveServlet?employeeid=${emp.employeeid}&employeename=${param.employeename}&username=${param.username}&status=${param.status}&pageNum=${requestScope.pageNum}&oper=close">关闭账号</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
	</div>
	</c:if>

</body>
</html>