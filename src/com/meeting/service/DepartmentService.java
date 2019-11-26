package com.meeting.service;

import java.util.List;

import com.meeting.dao.DepartmentDAO;
import com.meeting.vo.Department;

public class DepartmentService {
	private DepartmentDAO dao=new DepartmentDAO();

	public List<Department> viewAllDepartments(){
		return dao.selectAll();
	}
	/**
	 * 新增部门方法
	 * @param departmentname
	 */
	public void addDepartment(String departmentname){
		dao.insert(departmentname);
	}
	/**
	 * 删除部门方法
	 * @param departmentid
	 */
	public void deleteDepartment(int departmentid){
		dao.delete(departmentid);
	}
	
	
	public List<Department> showDepartment(){
		List<Department> departmentsList = dao.selectAll();		
		return departmentsList;
	}
	
	public static void main(String[] args) {
		DepartmentService ds = new DepartmentService();
		System.out.println(ds.showDepartment());
	}
}
