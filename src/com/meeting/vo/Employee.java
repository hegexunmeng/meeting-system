package com.meeting.vo;
/**
 * 对应Employee表的实体类
 * @author Administrator
 *
 */
public class Employee {
	private Integer employeeid;
	private String employeename;
	private String username;
	private String password;
	private Integer departmentid;//部门id
	private String email;
	private String phone;
	//0表示注册成功,但是没有审核
	private String status="0";
	//2表示普通人工
	private String role="2";
	
	//默认构造方法
	public Employee() {
		super();
	}
	
	//可能会用到的构造方法1
	public Employee(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	//可能会用到的构造方法2
	public Employee(String employeename, String username, String password,
			Integer departmentid, String email, String phone, String status,
			String role) {
		super();
		this.employeename = employeename;
		this.username = username;
		this.password = password;
		this.departmentid = departmentid;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.role = role;
	}

	//可能会用到的构造方法3
	public Employee(Integer employeeid, String employeename, String username, String password, Integer departmentid,
			String email, String phone, String status, String role) {
		super();
		this.employeeid = employeeid;
		this.employeename = employeename;
		this.username = username;
		this.password = password;
		this.departmentid = departmentid;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.role = role;
	}

	public Integer getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", employeename=" + employeename + ", username=" 
				+ username+ ", password=" + password + ", departmentid=" + departmentid + ", email=" 
				+ email + ", phone=" + phone
				+ ", status=" + status + ", role=" + role + "]";
	}
	
	
	
}
