package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meeting.dao.EmployeeDAO;
import com.meeting.service.EmployeeService;
import com.meeting.service.MeetingRoomService;
import com.meeting.service.MeetingService;
import com.meeting.vo.Meeting;

public class ViewMyMeetingsServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		//用当前登录的id作为参会者id
		int employeeid=(Integer) session.getAttribute("employeeid");
		MeetingService service=new MeetingService();
		MeetingRoomService roomService=new MeetingRoomService();
		EmployeeDAO empDao=new EmployeeDAO();
		
		//查询当前id的参会信息
		List<Meeting> meetingsList=service.viewMymeetingsInfo(employeeid);
		List<String[]>  nameList=new ArrayList<String[]>();
		
		//用数组来存预订者的姓名,会议室的姓名
		for(Meeting m:meetingsList){
			String empName=empDao.selectById(m.getReservationistid()).getEmployeename();
			String roomName=roomService.viewOneMeetingRoom(m.getRoomid()).getRoomname();
			nameList.add(new String[]{empName,roomName});
		}
		
		//
		Map<Meeting,String[]> map=new HashMap<Meeting,String[]>();
		
		for(int i=0;i<meetingsList.size();i++){
			map.put(meetingsList.get(i), nameList.get(i));
		}
		
		request.setAttribute("map", map);
		
		request.getRequestDispatcher("mymeetings.jsp").forward(request, response);
		
		
		
		
	}

}
