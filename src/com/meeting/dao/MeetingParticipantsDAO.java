package com.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.meeting.util.ConnectionFactory;
import com.meeting.vo.Employee;
import com.meeting.vo.Meeting;
import com.meeting.vo.MeetingRoom;

/**
 * 会议参与者的DAO
 * 
 * @author Administrator
 *
 */
public class MeetingParticipantsDAO {
	private Connection conn;

	/**
	 * 向表meetingparticipants中插入记录 每一个参加人员对应一条记录
	 * 
	 * @param meetingid
	 * @param employeeid
	 */
	public void insert(int meetingid, int employeeid) {
		conn = ConnectionFactory.getConnection();
		String sql = "insert into meetingparticipants values(?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, meetingid);
			pstmt.setInt(2, employeeid);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
	}

	/**
	 * 根据参与者的id,来查出来他参加了的所有会议
	 * 
	 * @param participantsid
	 * @return
	 */
	public List<Meeting> selectAllMeetingsByParId(int participantsid) {
		conn = ConnectionFactory.getConnection();
		List<Meeting> meetingslist = new ArrayList<Meeting>();
		Meeting meeting = null;
		try {
			PreparedStatement st = null;
			String sql = "select * from meeting,meetingparticipants where meeting.meetingid=meetingparticipants.meetingid and meetingparticipants.employeeid="
					+ participantsid;
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
	 * 根据会议的id来查出所有的参会员工
	 * 
	 * @param meetingid
	 * @return
	 */
	public List<Employee> selectAllEmployeesByMeetingId(int meetingid) {
		conn = ConnectionFactory.getConnection();
		List<Employee> employeeslist = new ArrayList<Employee>();
		Employee employee = null;
		try {
			PreparedStatement st = null;
			String sql = "select * from employee,meetingparticipants where employee.employeeid=meetingparticipants.employeeid and meetingparticipants.meetingid="
					+ meetingid;
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				employee = new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("departmentid"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employeeslist.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
		return employeeslist;
	}

	/**
	 * 查询某员工最近七天参加的所有会议 
	 * @param participantsid
	 * @return
	 */
	public List<Meeting> selectAllNewMeetings(int participantsid) {
		conn = ConnectionFactory.getConnection();
		List<Meeting> meetingslist = new ArrayList<Meeting>();
		Meeting meeting = null;
		try {
			PreparedStatement st = null;
			//获取现在的时间
			Timestamp now = new Timestamp(System.currentTimeMillis());
			//获取七天后的时间
			Timestamp sevenDays = new Timestamp(System.currentTimeMillis() + 7 * 24 * 3600 * 1000);
			//把两个时间转换为字符串
			String nowTime = now.toString();
			String sevenDaysTime = sevenDays.toString();
			
			//会议表meeting+参会人员信息表meetingparticipants
			//meeting.starttime<'" + sevenDaysTime起始时间小于七天后的时间,且没有被取消(状态是0)
			String sql = "select * from meeting,meetingparticipants where meeting.meetingid=meetingparticipants.meetingid and"
					+ " meetingparticipants.employeeid=" + participantsid + " and meeting.starttime>'" + nowTime
					+ "'and meeting.starttime<'" + sevenDaysTime + "' and status='0'";
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
	  * 查询某员工被要求参加，但是又取消的会议
	  * @param participantsid
	  * @return
	  */
	public List<Meeting> selectAllCanceledMeetings(int participantsid) {
		conn = ConnectionFactory.getConnection();
		List<Meeting> meetingslist = new ArrayList<Meeting>();
		Meeting meeting = null;
		try {
			PreparedStatement st = null;
			Timestamp now = new Timestamp(System.currentTimeMillis());
			String nowTime = now.toString();
			
			//跟上面的不同是状态是1被取消的
			String sql = "select * from meeting,meetingparticipants where meeting.meetingid=meetingparticipants.meetingid and"
					+ " meetingparticipants.employeeid=" + participantsid + " and meeting.starttime>'" + nowTime
					+ "'and status='1'";
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
	
	public List<Meeting> Test(int participantsid) {
		conn = ConnectionFactory.getConnection();
		List<Meeting> meetingslist = new ArrayList<Meeting>();
		Meeting meeting = null;
		try {
			PreparedStatement st = null;
			Timestamp now = new Timestamp(System.currentTimeMillis());
			String nowTime = now.toString();
			
			//跟上面的不同是状态是1被取消的
			String sql = "select * from meeting,meetingparticipants where meeting.meetingid=meetingparticipants.meetingid and"
					+ " meetingparticipants.employeeid=" + participantsid + " and meeting.starttime>'" + nowTime
					+ "'and status='1'";
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
	 * 测试类
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MeetingParticipantsDAO dao = new MeetingParticipantsDAO();
		// dao.insert(5, 8);
		// dao.insert(5, 9);
		// dao.insert(5, 10);

		// List<Meeting> list=dao.selectAllMeetingsByParId(8);
		// for(Meeting meeting:list){
		// System.out.println(meeting);
		// }

		List<Meeting> list = dao.selectAllCanceledMeetings(1);
		for (Meeting meeting : list) {
			System.out.println(meeting);
		}

	}

}
