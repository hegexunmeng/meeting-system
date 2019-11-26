<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="styles/common02.css"/>
</head>
<body>
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">个人中心</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="01.html" target="main">最新通知</a></li>
                        <li class="sidebar-menuitem active"><a href="01.html" target="main">我的预定</a></li>
                        <li class="sidebar-menuitem"><a href="01.html" target="main" >我的会议</a></li>
                    </ul>
                </div>
                  
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">会议预定</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="01.html"target="main">查看会议室</a></li>
                        <li class="sidebar-menuitem"><a href="01.html"target="main">预定会议</a></li>
                       
                    </ul>
                </div>
            </div>
</body>
</html>
