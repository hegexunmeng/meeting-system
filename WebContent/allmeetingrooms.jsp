<%@ page language="java" import="java.util.*,com.meeting.vo.*"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CoolMeeting会议管理系统</title>
<link rel="stylesheet" href="styles/common03.css" />
</head>
<body>

	<div class="page-content">
		<div class="content-nav">会议预定 > 查看会议室</div>
		<table class="listtable">
			<caption>所有会议室:</caption>
			<tr class="listheader">
				<th>门牌编号</th>
				<th>会议室名称</th>
				<th>容纳人数</th>
				<th>当前状态</th>
				<th>操作</th>
			</tr>
			<c:forEach var="room" items='${requestScope.meetingroomsList}'>
				<tr>
					<!-- 门牌编号 -->
					<td>${room.roomnum}</td>
					<!-- 会议室名称 -->
					<td>${room.roomname}</td>
					<!-- 容纳人数 -->
					<td>${room.capacity }</td>
					<!-- 当前状态 -->
					<!--0表示可用  -->
					<c:if test="${room.status eq 0}">
						<td>可用</td>
					</c:if>
					<!--1表示不可用  -->
					<c:if test="${room.status eq 1}">
						<td>不可用</td>
					</c:if>
					<td>
						<!-- 操作 --> <a class="clickbutton" href="ViewOneMeetingRoomServlet?roomid=${room.roomid}">查看详情</a>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
</body>
</html>