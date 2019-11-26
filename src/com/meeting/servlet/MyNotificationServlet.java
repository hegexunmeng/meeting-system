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

import com.meeting.dao.MeetingDAO;
import com.meeting.dao.MeetingParticipantsDAO;
import com.meeting.service.MeetingRoomService;
import com.meeting.service.MeetingService;
import com.meeting.vo.Meeting;


/**
 * 最新通知
 * @author Administrator
 *
 */
public class MyNotificationServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//取出当前登录者的id,作为参会者id保存
		HttpSession session=request.getSession();
		int participantsid=(Integer) session.getAttribute("employeeid");
		
		
		MeetingParticipantsDAO dao=new MeetingParticipantsDAO();
//取出参会者七天内的参加的会议,以及 被取消的会议的列表
		List<Meeting> meetingsList=dao.selectAllNewMeetings(participantsid);
		List<Meeting> canceledMeetings=dao.selectAllCanceledMeetings(participantsid);
        
		//查询会议室的名字的列表
		MeetingRoomService roomService=new MeetingRoomService();		
		List<String>  roomsNameList=new ArrayList<String>();
		
		//利用循环把会议室的名字和会议室的id组合起来
		for(Meeting m:meetingsList){
			roomsNameList.add(roomService.viewOneMeetingRoom(m.getRoomid()).getRoomname());
		}
		
		//利用循环把七天内参加的会议的名字和会议放在map里
		Map<Meeting,String> meetingsMap=new HashMap<Meeting,String>();
		for(int i=0;i<meetingsList.size();i++){
			meetingsMap.put(meetingsList.get(i), roomsNameList.get(i));
		}
		request.setAttribute("meetingsMap", meetingsMap);
		
		
		
		//把其七天内取消的会议室的名字和会议的id组合起来
		List<String>  cancelRoomNameList=new ArrayList<String>();
		for(Meeting m:canceledMeetings){
			cancelRoomNameList.add(roomService.viewOneMeetingRoom(m.getRoomid()).getRoomname());
		}
		
		//利用循环把取消的会议室的名字和会议放在map里
		Map<Meeting,String> cancelMeetingsMap=new HashMap<Meeting,String>();
		for(int i=0;i<canceledMeetings.size();i++){
			cancelMeetingsMap.put(canceledMeetings.get(i),cancelRoomNameList.get(i));
		}
		request.setAttribute("cancelMeetingsMap", cancelMeetingsMap);		
		request.getRequestDispatcher("mynotification.jsp").forward(request, response);
	}

}
