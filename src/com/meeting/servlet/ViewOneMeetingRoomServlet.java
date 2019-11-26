package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.service.MeetingRoomService;
import com.meeting.vo.MeetingRoom;

public class ViewOneMeetingRoomServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到roomid
		int roomid=Integer.parseInt(request.getParameter("roomid"));
		//调用方法
		MeetingRoomService service =new MeetingRoomService();
		//把room
		MeetingRoom room=service.viewOneMeetingRoom(roomid);
		request.setAttribute("room", room);
		request.getRequestDispatcher("meetingroomdetail.jsp").forward(request, response);
		
	}

}
