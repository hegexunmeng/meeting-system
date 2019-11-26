<%@ page language="java"
	import="java.util.*,com.meeting.vo.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common03.css"/>
    </head>
    <body>
        
            <div class="page-content">
                <div class="content-nav">
                    个人中心 > 最新通知
                </div>
                <table class="listtable">
                    <caption>
                        未来7天我要参加的会议:
                    </caption>
                    <tr class="listheader">
                        <th style="width:300px">会议名称</th>
                        <th>会议室</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th style="width:100px">操作</th>
                    </tr>
                    <c:forEach var="item" items="${requestScope.meetingsMap}">
                    <tr>
                        <td>${item.key.meetingname}</td>
                        <td>${item.value }</td>
                        <td>${item.key.starttime }</td>
                        <td>${item.key.endtime }</td>
                        <td>
                            <a class="clickbutton" href="ViewMyMeetingDetailServlet?meetingid=${item.key.meetingid}">查看详情</a>
                        </td>
                    </tr>
                    </c:forEach>
                    
                </table>
                <table class="listtable">
                    <caption>
                        最近取消的会议:
                    </caption>
                    <tr class="listheader">
                        <th style="width:300px">会议名称</th>
                        <th>会议室</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th style="width:100px">操作</th>
                    </tr>
                   <c:forEach var="item" items="${requestScope.cancelMeetingsMap}">
                    <tr>
                        <td>${item.key.meetingname}</td>
                        <td>${item.value }</td>
                        <td>${item.key.starttime }</td>
                        <td>${item.key.endtime }</td>
                        <td>
                            <a class="clickbutton" href="ViewMyMeetingDetailServlet?meetingid=${item.key.meetingid}">查看详情</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
      
    </body>
</html>