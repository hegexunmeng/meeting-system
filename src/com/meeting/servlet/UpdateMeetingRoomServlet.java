package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.service.MeetingRoomService;
import com.meeting.vo.MeetingRoom;

public class UpdateMeetingRoomServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//查看会议室-查看详情-确认修改功能
		//获取会议室编号,门牌号,容纳人数
		int roomid=Integer.parseInt(request.getParameter("roomid"));
		int roomnum=Integer.parseInt(request.getParameter("roomnum"));
		int capacity=Integer.parseInt(request.getParameter("capacity"));
		//获取会议室名称,状态,说明
		String roomname=request.getParameter("roomname");
		String status=request.getParameter("status");
		String description=request.getParameter("description");
		//调用方法
		MeetingRoomService service=new MeetingRoomService();
		service.updateMeetingRoom(new MeetingRoom(roomid,roomnum,roomname,capacity,status,description));
		//跳转页面
		request.getRequestDispatcher("ViewAllMeetingRoomsServlet").forward(request, response);
	}

}
