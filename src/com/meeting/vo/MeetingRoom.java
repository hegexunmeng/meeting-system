package com.meeting.vo;

public class MeetingRoom {
	private Integer roomid;
	private Integer roomnum;
	private Integer capacity;//容量
	private String roomname;//名称
	private String  status;//状态:启用/停用
	private String description;//描述
	
	public MeetingRoom() {
		super();
	}
	
	public MeetingRoom(Integer roomid) {
		super();
		this.roomid = roomid;
	}
	
	public MeetingRoom(Integer roomnum, String roomname, Integer capacity,String status, String description) {
		super();
	
		this.roomnum = roomnum;
		this.capacity = capacity;
		this.roomname = roomname;
		this.status = status;
		this.description = description;
	}
	
	public MeetingRoom(Integer roomid, Integer roomnum, String roomname, Integer capacity,
			String status, String description) {
		super();
		this.roomid = roomid;
		this.roomnum = roomnum;
		this.capacity = capacity;
		this.roomname = roomname;
		this.status = status;
		this.description = description;
	}
	public Integer getRoomid() {
		return roomid;
	}
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	public Integer getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(Integer roomnum) {
		this.roomnum = roomnum;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "MeetingRoom [roomid=" + roomid + ", roomnum=" + roomnum
				+ ", capacity=" + capacity + ", roomname=" + roomname
				+ ", status=" + status + ", description=" + description + "]";
	}
	
	
}
