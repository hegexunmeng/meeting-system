package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meeting.dao.DepartmentDAO;
import com.meeting.dao.MeetingRoomDAO;
import com.meeting.service.MeetingService;
import com.meeting.vo.Department;
import com.meeting.vo.Meeting;
import com.meeting.vo.MeetingRoom;

/**
 * 预定会议控制器
 * 
 * @author Administrator
 *
 */
public class BookMeetingServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String code = request.getParameter("code");
		// 当?code=prepare时候才执行
		if (code != null && code.equals("prepare")) {
			MeetingRoomDAO roomDao = new MeetingRoomDAO();
			DepartmentDAO deptDao = new DepartmentDAO();

			// 查出所有会议
			List<MeetingRoom> roomsList = roomDao.selectAllMeetingRooms();
			// 查出所有部门
			List<Department> deptsList = deptDao.selectAll();

			// 把上面查出的返回到页面
			request.setAttribute("roomsList", roomsList);
			request.setAttribute("deptsList", deptsList);

			request.getRequestDispatcher("bookmeeting.jsp").forward(request, response);
		}
		
		//新建分支,当?code=book时,实现预定会议功能
		if (code != null && code.equals("book")) {
			MeetingService meetingService = new MeetingService();

			String meetingname = request.getParameter("meetingname");
			int roomid = Integer.parseInt(request.getParameter("roomid"));

			HttpSession session = request.getSession();
			
			//这里预订者的id是登录者的id,从LoginServlet存的session那获取employeeid(那里的代码为session.setAttribute("employeeid", loginedEmployee.getEmployeeid());)
			int reservationistid = (Integer) session.getAttribute("employeeid");

			int numofparticipants = Integer.parseInt(request.getParameter("numofparticipants"));
			
			//时间取出来的时候是字符串,现在把他强转为Timestamp类型
			Timestamp starttime = Timestamp.valueOf(request.getParameter("starttime"));
			Timestamp endtime = Timestamp.valueOf(request.getParameter("endtime"));
			Timestamp reservationtime = new Timestamp(System.currentTimeMillis());
			Timestamp canceledtime = null;
			String status = "0";
			String description = request.getParameter("description");
			
			//获得meeting的信息
			Meeting meeting = new Meeting(meetingname, roomid, reservationistid, numofparticipants, starttime, endtime,
					reservationtime, canceledtime, description, status);
			
			//获得多个参会人员的id,用字符串数组存起来
			String[] employeesid = request.getParameterValues("selSelectedEmployees");
			
			
			//获得参会人员employeesidList信息
			List<Integer> employeesidList = new ArrayList<Integer>();
			
			//迭代循环字符串数组把参会人员的id放在集合里
			for (String s : employeesid) {
				employeesidList.add(Integer.parseInt(s));
			}
			
			//调用service的预定会议的方法(会议,参会人员的集合信息)
			meetingService.bookMeeting(meeting, employeesidList);
			request.getRequestDispatcher("ViewMyBookingServlet").forward(request,response);

		}

	}

}
