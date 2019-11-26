<%@ page language="java" import="java.util.*,com.meeting.vo.*" pageEncoding="utf-8"%>

<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common03.css"/>            
    </head>
    <body>
            <div class="page-content">
                <div class="content-nav">
                    人员管理 > 注册审批
                </div>
                <table class="listtable">
                    <caption>所有待审批注册信息：</caption>
                    <!-- 取出刚刚存起来的待审批的员工列表 -->
                   <%--  <%List<Employee> employeesList=(List<Employee>)request.getAttribute("employeesList"); %> --%>
                    <tr class="listheader">
                        <th>姓名</th>
                        <th>账号名</th>
                        <th>联系电话</th>
                        <th>电子邮件</th>
                        <th>操作</th>
                    </tr>
                  <!--   循环迭代这个列表 -->
                   <%--  <%for(Employee e:employeesList){ %> --%>
                    <c:forEach var="emp" items="${requestScope.employeesList}">
                    <tr>
                       <%--  <td><%=e.getEmployeename()%></td>
                        <td><%=e.getUsername()%></td>
                        <td><%=e.getPhone()%></td>
                        <td><%=e.getEmail()%></td> --%>
                         <td>${emp.employeename}</td>
                        <td>${emp.username}</td>
                        <td>${emp.phone}</td>
                        <td>${ emp.email}</td>
                        <td>
                        	<!--传入上面迭代得到的e.getEmployeeid(),并且传入 oper是yes还是no -->
                            <input type="button" class="clickbutton" value="通过" onclick="window.location.href='ApproveServlet?employeeid=${emp.employeeid}&oper=yes'"/>
                            <input type="button" class="clickbutton" value="不通过" onclick="window.location.href='ApproveServlet?employeeid=${emp.employeeid}&oper=no'"/>
                        </td>
                    </tr>
                    <%-- <%}%> --%>
                    </c:forEach>
                </table>
            </div>
    

    </body>
</html>