package com.meeting.dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meeting.util.ConnectionFactory;
import com.meeting.vo.Employee;
import com.meeting.vo.Meeting;

public class MeetingDAO {

	private Connection conn;

	/**
	 * 向表meeting中插入记录，其中status使用默认值0
	 * @param meeting
	 * @return
	 */
	public int insert(Meeting meeting) {
		int meetingid = 0;
		conn = ConnectionFactory.getConnection();
		String sql = "insert into meeting"
				+ "(meetingname,roomid,reservationistid,numberofparticipants,starttime,endtime,reservationtime,canceledtime,description,status)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, meeting.getMeetingname());
			pstmt.setInt(2, meeting.getRoomid());
			pstmt.setInt(3, meeting.getReservationistid());
			pstmt.setInt(4, meeting.getNumberofparticipants());
			pstmt.setTimestamp(5, meeting.getStarttime());
			pstmt.setTimestamp(6, meeting.getEndtime());
			pstmt.setTimestamp(7, meeting.getReservationtime());
			pstmt.setTimestamp(8, meeting.getCanceledtime());
			pstmt.setString(9, meeting.getDescription());
			pstmt.setString(10, "0");

			pstmt.executeUpdate();

			ResultSet rs = pstmt.executeQuery("select max(meetingid) from meeting");
			if (rs.next()) {
				meetingid = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
		return meetingid;
	}

	/**
	 * 根据meetingid，更新会议的状态，删除时间
	 * 状态从0变成1 变成取消状态
	 * @param meetingid
	 * @param status
	 * @param canceledtime
	 */
	public void update(int meetingid, String status, Timestamp canceledtime) {
		conn = ConnectionFactory.getConnection();
		String sql = "update meeting set status=?,canceledtime=? where meetingid=" + meetingid;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setTimestamp(2, canceledtime);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
	}

	/**
	 * 查询某员工预定的所有会议
	 * @param reservationistid
	 * @return
	 */
	public List<Meeting> selectAllMeetingsByReserId(int reservationistid) {
		conn = ConnectionFactory.getConnection();
		List<Meeting> meetingslist = new ArrayList<Meeting>();
		Meeting meeting = null;
		try {
			PreparedStatement st = null;
			String sql = "select * from meeting where reservationistid=" + reservationistid;
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				meeting = new Meeting();
				meeting.setMeetingid(rs.getInt("meetingid"));
				meeting.setMeetingname(rs.getString("meetingname"));
				meeting.setRoomid(rs.getInt("roomid"));
				meeting.setReservationistid(rs.getInt("reservationistid"));
				meeting.setNumberofparticipants(rs.getInt("numberofparticipants"));
				meeting.setStarttime(rs.getTimestamp("starttime"));
				meeting.setEndtime(rs.getTimestamp("endtime"));
				meeting.setReservationtime(rs.getTimestamp("reservationtime"));
				meeting.setCanceledtime(rs.getTimestamp("canceledtime"));
				meeting.setDescription(rs.getString("description"));
				meeting.setStatus(rs.getString("status"));
				meetingslist.add(meeting);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
		return meetingslist;
	}

	
	/**
	 * 查询某id值对应的会议
	 * @param meetingid
	 * @return
	 */
	 public Meeting selectById(int meetingid){
		 conn=ConnectionFactory.getConnection();
		 
		 Meeting meeting=null;	
		 try {
			PreparedStatement st=null;
			String sql="select * from meeting where meetingid="+meetingid;
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			while(rs.next()){
				meeting=new Meeting();
				meeting.setMeetingid(rs.getInt("meetingid"));
				meeting.setMeetingname(rs.getString("meetingname"));
				meeting.setRoomid(rs.getInt("roomid"));
				meeting.setReservationistid(rs.getInt("reservationistid"));
				meeting.setNumberofparticipants(rs.getInt("numberofparticipants"));
				meeting.setStarttime(rs.getTimestamp("starttime"));
				meeting.setEndtime(rs.getTimestamp("endtime"));
				meeting.setReservationtime(rs.getTimestamp("reservationtime"));
				meeting.setCanceledtime(rs.getTimestamp("canceledtime"));
				meeting.setDescription(rs.getString("description"));
				meeting.setStatus(rs.getString("status"));
				
			}
		 } catch (SQLException e) {
			    e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		 return meeting;
	 }
	
	/**
	 * 测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MeetingDAO dao = new MeetingDAO();
		List<Meeting> list = dao.selectAllMeetingsByReserId(8);
		for (Meeting m : list) {
			System.out.println(m);
		}
	}

}
