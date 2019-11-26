package com.meeting.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.meeting.dao.MeetingDAO;
import com.meeting.dao.MeetingParticipantsDAO;
import com.meeting.vo.Meeting;

/**
 * 会议:对外提供的服务类
 * @author Administrator
 *
 */
public class MeetingService {
	private MeetingDAO meetingDao=new MeetingDAO();
	private MeetingParticipantsDAO parDao=new MeetingParticipantsDAO();	
	
	/**
	 * 预定会议
	 * @param meeting
	 * @param employeeidList
	 */
	public void bookMeeting(Meeting meeting,List<Integer> employeeidList){
		//插入会议信息
		int meetingid=meetingDao.insert(meeting);
		//循环列表,把参会人员的信息插入到会议信息表里
		for(Integer employeeid:employeeidList){
			parDao.insert(meetingid, employeeid);
		}
	}
	
	/**
	 * 取消会议,把会议状态变为1,并传入取消会议的时间(当前时间
	 * @param meetingid
	 */
	public void cancelMeeting(int meetingid){
		meetingDao.update(meetingid, "1", new Timestamp(System.currentTimeMillis()));
	}
	
	/**
	 * 根据当前id来查询预定的会议
	 * @param reservationistid
	 * @return
	 */
	public List<Meeting> viewMyBookingInfo(int reservationistid){
		return meetingDao.selectAllMeetingsByReserId(reservationistid);
	}
	
	/**
	 * 根据当前的id来查询所有的参会信息
	 * @param participantsid
	 * @return
	 */
	public List<Meeting> viewMymeetingsInfo(int participantsid){
		return parDao.selectAllMeetingsByParId(participantsid);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MeetingService service=new MeetingService();
//		Meeting meeting=new Meeting("运营会wuwuw",5,8,10,Timestamp.valueOf("2015-01-02 11:09:00"),Timestamp.valueOf("2015-01-03 11:09:00"),Timestamp.valueOf("2015-01-02 11:09:00"),Timestamp.valueOf("2015-01-02 11:09:00"),"看当月运营情况","0");
//		List<Integer> idList=new ArrayList<Integer>();
//		idList.add(8);
//		idList.add(9);
//		
//		service.bookMeeting(meeting, idList);		
		//service.cancelMeeting(23);

	}

}
