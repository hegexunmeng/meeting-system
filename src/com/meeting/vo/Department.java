package com.meeting.vo;

public class Department {
	private int departmentid;
	private String departmentname;
	
	public Department() {
		super();
	}

	public int getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	@Override
	public String toString() {
		return "Department [部门id=" + departmentid + ", 部门名字=" + departmentname + "]";
	}
	
	

	
	
	
	
}
