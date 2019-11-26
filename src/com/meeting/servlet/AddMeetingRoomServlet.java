package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.service.MeetingRoomService;
import com.meeting.vo.MeetingRoom;

public class AddMeetingRoomServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//取出输入的信息
		int roomnum=Integer.parseInt(request.getParameter("roomnum"));
		int capacity=Integer.parseInt(request.getParameter("capacity"));
		String roomname=request.getParameter("roomname");
		String status=request.getParameter("status");
		String description=request.getParameter("description");
		//调用addMeetingRoom()方法
		MeetingRoomService service=new MeetingRoomService();
		service.addMeetingRoom(new MeetingRoom(roomnum,roomname,capacity,status,description));
		//跳转页面
		request.getRequestDispatcher("ViewAllMeetingRoomsServlet").forward(request, response);
	}

}
