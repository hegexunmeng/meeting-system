package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.service.MeetingService;

public class CancelMeetingServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//通过获得会议id
		int meetingid=Integer.parseInt(request.getParameter("meetingid"));
		MeetingService service=new MeetingService();
		
		//调用取消会议的方法
		service.cancelMeeting(meetingid);
		
		request.getRequestDispatcher("ViewMyBookingServlet").forward(request, response);
	}

}
