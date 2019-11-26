package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.dao.MeetingDAO;
import com.meeting.dao.MeetingParticipantsDAO;
import com.meeting.vo.Employee;
import com.meeting.vo.Meeting;

public class ViewMyMeetingDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取会议id
		int meetingid=Integer.parseInt(request.getParameter("meetingid"));
		MeetingDAO meetingDao=new MeetingDAO();
		MeetingParticipantsDAO parDao=new MeetingParticipantsDAO();	
		//通过会议id查询会议
		Meeting meeting=meetingDao.selectById(meetingid);	
		//查询这个会议的所有参会人员的信息
		List<Employee> employeesList=parDao.selectAllEmployeesByMeetingId(meeting.getMeetingid());
		request.setAttribute("meeting", meeting);
		request.setAttribute("employeesList", employeesList);
		request.getRequestDispatcher("mymeetingdetail.jsp").forward(request, response);
	}

}
