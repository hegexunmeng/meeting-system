package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.service.MeetingRoomService;
import com.meeting.vo.MeetingRoom;

public class ViewAllMeetingRoomsServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MeetingRoomService service=new MeetingRoomService();
		//调用viewAllMeetingRooms,存结果集
		List<MeetingRoom> list=service.viewAllMeetingRooms();
		request.setAttribute("meetingroomsList", list);
		request.getRequestDispatcher("allmeetingrooms.jsp").forward(request, response);
	}

}
