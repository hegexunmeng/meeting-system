package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.dao.MeetingDAO;
import com.meeting.dao.MeetingRoomDAO;
import com.meeting.vo.Employee;
import com.meeting.vo.MeetingRoom;

public class RefreshRoomsServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到起始时间和结束时间
		try{
			Timestamp starttime=Timestamp.valueOf(request.getParameter("starttime"));
			Timestamp endtime=Timestamp.valueOf(request.getParameter("endtime"));
			//查询出所有可用的会议室
			MeetingRoomDAO dao=new MeetingRoomDAO();
			List<MeetingRoom> roomList=dao.selectMeetingRoomsByTime(starttime, endtime);
			//将查询得到的部门信息，以XML文档的格式返回到浏览器
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			response.setHeader("Cache-Control", "no-cache");
			out.println("<?xml version='1.0' encoding='" + "utf-8" + "' ?>");

			//符合XML规范，有根节点，否则解析有问题
			out.println("<rooms>");
			for (MeetingRoom m : roomList) {
				out.println("<option>");
				out.println("<value>" + m.getRoomid()+ "</value>");
				out.println("<text>" + m.getRoomname() + "</text>");
				out.println("</option>");

			}
			out.println("</rooms>");
			out.close();
		}catch(IllegalArgumentException e){
			System.out.println("请先选起始和结束日期再选择会议室");
			e.printStackTrace();
		}
		
		

	}

}
