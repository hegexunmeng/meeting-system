<%@ page language="java"
	import="java.util.*,com.meeting.vo.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common03.css"/>
        <style type="text/css">
            
        </style>
    </head>
    <body>
        
            <div class="page-content">
                <div class="content-nav">
                    个人中心 > 我的预定
                </div>
                <table class="listtable">
                    <caption>我预定的会议：</caption>
                    <tr class="listheader">
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议预定时间</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach var="item" items="${requestScope.map}">
                    <tr>
                        <td>${item.key.meetingname}</td>
                        <td>${item.value}</td>
                        <td>${item.key.starttime}</td>
                        <td>${item.key.endtime}</td>
                        <td>${item.key.reservationtime}</td>
                        <td>
                        <c:if test="${item.key.status.equals('0')}">
                            <a class="clickbutton" href="ViewMyBookingDetailServlet?meetingid=${item.key.meetingid}">查看/取消</a>
                       </c:if>
                         <c:if test="${item.key.status.equals('1')}">
                            <a class="clickbutton" href="ViewMyBookingDetailServlet?meetingid=${item.key.meetingid}">查看（已取消）</a>
                       </c:if>
                        </td>
                    </tr>
             </c:forEach>
                </table>
            </div>
     
    </body>
</html>