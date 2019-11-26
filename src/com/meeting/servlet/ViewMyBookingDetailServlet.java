package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.dao.EmployeeDAO;
import com.meeting.dao.MeetingDAO;
import com.meeting.dao.MeetingParticipantsDAO;
import com.meeting.service.MeetingService;
import com.meeting.vo.Employee;
import com.meeting.vo.Meeting;

/**
 * 我的预定的详情
 * @author Administrator
 *
 */
public class ViewMyBookingDetailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获得预定id
		int meetingid=Integer.parseInt(request.getParameter("meetingid"));
		MeetingDAO meetingDao=new MeetingDAO();
		MeetingParticipantsDAO parDao=new MeetingParticipantsDAO();	
		
		//通过id查询得到对应的会议
		Meeting meeting=meetingDao.selectById(meetingid);
		
		//通过会议id值查到所有参会人员的id值放在列表employeesList里
		List<Employee> employeesList=parDao.selectAllEmployeesByMeetingId(meeting.getMeetingid());
		
		//保存会议和员工列表
		request.setAttribute("meeting", meeting);
		request.setAttribute("employeesList", employeesList);
		request.getRequestDispatcher("mybookingdetail.jsp").forward(request, response);
	}

}
