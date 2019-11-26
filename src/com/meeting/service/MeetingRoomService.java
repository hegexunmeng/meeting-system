package com.meeting.service;

import java.util.List;

import com.meeting.dao.MeetingRoomDAO;
import com.meeting.vo.MeetingRoom;

public class MeetingRoomService {

	private MeetingRoomDAO dao=new MeetingRoomDAO();
	//一共四个方法
	
	/**
	 * 查询所有会议室
	 * @return
	 */
	public List<MeetingRoom> viewAllMeetingRooms(){
		return dao.selectAllMeetingRooms();
	}
	
	/**
	 * 根据id查询会议室
	 * @param roomid
	 * @return
	 */
	public MeetingRoom viewOneMeetingRoom(int roomid){
		return dao.selectByRoomid(roomid);
	}
	
	/**
	 * 添加会议室
	 * @param meetingroom
	 */
	public void addMeetingRoom(MeetingRoom meetingroom){
		dao.insert(meetingroom);
	}
	
	/**
	 * 根据id更新会议室状态
	 * @param meetingroom
	 */
	public void updateMeetingRoom(MeetingRoom meetingroom){
		dao.updateMeetingroom(meetingroom);
	}
}
