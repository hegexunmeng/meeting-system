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

import com.meeting.service.MeetingRoomService;
import com.meeting.service.MeetingService;
import com.meeting.vo.Meeting;

public class ViewMyBookingServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		//取出预订者的id,也就是登录用户的id
		int reservationistid=(Integer) session.getAttribute("employeeid");
		
		MeetingService service=new MeetingService();
		MeetingRoomService roomService=new MeetingRoomService();
		
		//根据当前的id来查询会议
		List<Meeting> meetingsList=service.viewMyBookingInfo(reservationistid);
		//因为会议在不同的会议室举行,所以把会议室的名字也取出来
		List<String>  roomsNameList=new ArrayList<String>();
		
		//根据roomid来查会议室
		for(Meeting m:meetingsList){
			roomsNameList.add(roomService.viewOneMeetingRoom(m.getRoomid()).getRoomname());
		}
		
		//meeting和字符串键值对地存起来
		Map<Meeting,String> map=new HashMap<Meeting,String>();
		
		for(int i=0;i<meetingsList.size();i++){
			map.put(meetingsList.get(i), roomsNameList.get(i));
		}
		//把map存到前端
		request.setAttribute("map", map);
		
		request.getRequestDispatcher("mybookings.jsp").forward(request, response);
		
		
		
		
	}

}
