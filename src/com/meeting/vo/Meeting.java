package com.meeting.vo;

import java.sql.Timestamp;

/**
 * 会议
 * @author Administrator
 *
 */
public class Meeting {
	private int meetingid;
	private String meetingname;
	private int roomid;//会议室编号
	private int reservationistid;//预订者的id
	private int numberofparticipants;
	private Timestamp starttime;//开始时间
	private Timestamp endtime;//结束时间
	private Timestamp reservationtime;//预定时间
	private Timestamp canceledtime;//取消时间
	private String description;//描述
	//status为0，表示正常，1表示取消
	private String status="0";
	
	public Meeting() {
		super();
	}
	
	public Meeting(int meetingid) {
		super();
		this.meetingid = meetingid;
	}

	public Meeting(String meetingname, int roomid, int reservationistid,
			int numberofparticipants, Timestamp starttime, Timestamp endtime,
			Timestamp reservationtime, Timestamp canceledtime, String description,
			String status) {
		super();
		this.meetingname = meetingname;
		this.roomid = roomid;
		this.reservationistid = reservationistid;
		this.numberofparticipants = numberofparticipants;
		this.starttime = starttime;
		this.endtime = endtime;
		this.reservationtime = reservationtime;
		this.canceledtime = canceledtime;
		this.description = description;
		this.status = status;
	}

	public Meeting(int meetingid, String meetingname, int roomid,
			int reservationistid, int numberofparticipants, Timestamp starttime,
			Timestamp endtime, Timestamp reservationtime, Timestamp canceledtime,
			String description, String status) {
		super();
		this.meetingid = meetingid;
		this.meetingname = meetingname;
		this.roomid = roomid;
		this.reservationistid = reservationistid;
		this.numberofparticipants = numberofparticipants;
		this.starttime = starttime;
		this.endtime = endtime;
		this.reservationtime = reservationtime;
		this.canceledtime = canceledtime;
		this.description = description;
		this.status = status;
	}

	public int getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(int meetingid) {
		this.meetingid = meetingid;
	}

	public String getMeetingname() {
		return meetingname;
	}

	public void setMeetingname(String meetingname) {
		this.meetingname = meetingname;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public int getReservationistid() {
		return reservationistid;
	}

	public void setReservationistid(int reservationistid) {
		this.reservationistid = reservationistid;
	}

	public int getNumberofparticipants() {
		return numberofparticipants;
	}

	public void setNumberofparticipants(int numberofparticipants) {
		this.numberofparticipants = numberofparticipants;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public Timestamp getReservationtime() {
		return reservationtime;
	}

	public void setReservationtime(Timestamp reservationtime) {
		this.reservationtime = reservationtime;
	}

	public Timestamp getCanceledtime() {
		return canceledtime;
	}

	public void setCanceledtime(Timestamp canceledtime) {
		this.canceledtime = canceledtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Meeting [meetingid=" + meetingid + ", meetingname="
				+ meetingname + ", roomid=" + roomid + ", reservationistid="
				+ reservationistid + ", numberofparticipants="
				+ numberofparticipants + ", starttime=" + starttime
				+ ", endtime=" + endtime + ", reservationtime="
				+ reservationtime + ", canceledtime=" + canceledtime
				+ ", description=" + description + ", status=" + status + "]";
	}
	
}
