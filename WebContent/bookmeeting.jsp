<%@ page language="java"
	import="java.util.*,com.meeting.vo.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>CoolMeeting会议管理系统</title>
<link rel="stylesheet" href="styles/common03.css" />
<style type="text/css">
#divfrom {
	float: left;
	width: 150px;
}

#divto {
	float: left;
	width: 150px;
}

#divoperator {
	float: left;
	width: 50px;
	padding: 60px 5px;
}

#divoperator input[type="button"] {
	margin: 10px 0;
}

#selDepartments {
	display: block;
	width: 100%;
}

#selEmployees {
	display: block;
	width: 100%;
	height: 200px;
}

#selSelectedEmployees {
	display: block;
	width: 100%;
	height: 225px;
}
</style>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	    
	    var xmlHttp;

        function createXMLHttpRequest() {
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } 
            else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();                
            }
        }

        function showEmployees() {
            createXMLHttpRequest();
       		var deptid=document.getElementById("selDepartments").value;   	
       		var url = "SelectEmployeesOfDeptServlet?departmentid=" + escape(deptid);           
            xmlHttp.open("GET", url, true);     
            xmlHttp.onreadystatechange = callback;
            xmlHttp.send(null);
        }

        function callback() {
           clearEmployees();
           var selEmployees=document.getElementById("selEmployees");
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                    var elements = xmlHttp.responseXML.getElementsByTagName("option");                      
                    for (var i = 0; i < elements.length; i++) {
	                    var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
	                    var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;                
	                    selEmployees.options.add(new Option(text,value));
                    }       
                }
            }
              
        }
        
        function clearEmployees(){
         	document.getElementById("selEmployees").options.length=0;
        }
        
         function selectEmployees(){
         		var selEmployees=document.getElementById("selEmployees");
         		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
                for(var i=0;i<selEmployees.options.length;i++){
                    if (selEmployees.options[i].selected){
                        var opt=new Option(selEmployees.options[i].text,selEmployees.options[i].value);
                        opt.selected=true;
                        selSelectedEmployees.options.add(opt);
                        selEmployees.options.remove(i);
                    }
                }
            }
        
        function deSelectEmployees(){
        		var selEmployees=document.getElementById("selEmployees");
         		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
                for(var i=0;i<selSelectedEmployees.options.length;i++){
                    if (selSelectedEmployees.options[i].selected){
                        selEmployees.options.add(new Option(selSelectedEmployees.options[i].text,selSelectedEmployees.options[i].value));
                        selSelectedEmployees.options.remove(i);
                    }
                }
                setSelected();
            }     
             
        function setSelected(){
         		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
                for(var i=0;i<selSelectedEmployees.options.length;i++){
                    selSelectedEmployees.options[i].selected=true;
                }
        }
        
        function refreshRooms(){
            createXMLHttpRequest();
            /* 获得时间 */
	        var starttime=document.getElementById("starttime").value;   	
	        var endtime=document.getElementById("endtime").value;  
	        
	       	var url = "RefreshRoomsServlet?starttime=" + escape(starttime)+"&endtime="+escape(endtime);           
	        xmlHttp.open("GET", url, true);     
	        xmlHttp.onreadystatechange = refresh;
	        xmlHttp.send(null);
        }
        
         function refresh() {
  		   clearMeetingRooms(); 
           var roomid=document.getElementById("roomid");
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                	
                    var elements = xmlHttp.responseXML.getElementsByTagName("option");                      
                    for (var i = 0; i < elements.length; i++) {
	                    var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
	                    var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue; 
	                    /* 加载到roomid这个下拉列表里 */
	                    roomid.options.add(new Option(text,value),i+1);
                    }       
                }
            }
        
        }
        
        function clearMeetingRooms(){
         	document.getElementById("roomid").options.length=1;
        }
            
</script>
</head>
<body >
	<div class="page-content">
		<div class="content-nav">会议预定 > 预定会议</div>
		<form method="post" action="BookMeetingServlet">
			<fieldset>
				<legend>会议信息</legend>
				<table class="formtable">
					<tr>
						<td>会议名称：</td>
						<td><input type="text" id="meetingname"  name="meetingname" maxlength="20" /></td>
					</tr>
					<tr>
						<td>预计参加人数：</td>
						<td><input type="text" id="numofattendents" name="numofparticipants"/></td>
					</tr>
					<tr>
						<td>预计开始时间：</td>
						<td><input class="Wdate" type="text" id="starttime" name="starttime"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					</tr>
					<tr>
						<td>预计结束时间：</td>
						<td><input class="Wdate" type="text" id="endtime" name="endtime"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
						</td>
					</tr>

					<tr>
						<td>选择会议室：</td>
						<!--onfocus聚焦就会调用refreshRooms()方法  -->
						<td><select id="roomid" name="roomid" onfocus="refreshRooms()">
						<option>请选择会议室</option>
								<c:forEach var="room" items="${requestScope.roomsList}">
									<option value="${room.roomid}">${room.roomname}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td>会议说明：</td>
						<td><textarea id="description" name="description" rows="5"></textarea></td>
					</tr>
					<tr>
						<td>选择参会人员：</td>
						<td>
							<div id="divfrom">
								<select id="selDepartments" onchange="showEmployees()">
								    <option>请选择部门</option>
									<c:forEach var="dept" items="${requestScope.deptsList}">
										<option value="${dept.departmentid}">${dept.departmentname}</option>
									</c:forEach>
								</select> <select id="selEmployees"  multiple="multiple">

								</select>
							</div>
							<div id="divoperator">
								<input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()" /> 
								<input type="button"
									class="clickbutton" value="&lt;" onclick="deSelectEmployees()" />
							</div>
							<div id="divto">
								<select id="selSelectedEmployees"  name="selSelectedEmployees" multiple="multiple" >
								</select>
							</div></td>
					</tr>
					<tr>
						<td class="command" colspan="2">
						<input type="hidden" name="code" value="book">
						<input type="submit" class="clickbutton" value="预定会议" /> 
						<input type="reset"	class="clickbutton" value="重置" /></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>

	
</body>
</html>