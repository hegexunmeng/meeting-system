package com.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.meeting.util.ConnectionFactory;
import com.meeting.vo.Department;

public class DepartmentDAO {
	// DAO类关联连接工厂类
	private Connection conn;
	
	/**
	 * 查询所有部门信息，返回到集合中
	 * 
	 * @return departmentsList
	 */
	public List<Department> selectAll() {
		conn = ConnectionFactory.getConnection();
		// 新建一个集合departmentsList
		List<Department> departmentsList = new ArrayList<Department>();
		try {
			Statement st = null;
			String sql = "select * from department";
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Department department;
			while (rs.next()) {
				// 新建一个department来接收数据库的信息
				department = new Department();
				department.setDepartmentid(rs.getInt("departmentid"));
				department.setDepartmentname(rs.getString("departmentname"));
				departmentsList.add(department);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
		// 返回集合
		return departmentsList;
	}

	
	/**
	 * 插入部门
	 * 
	 * @param departmentname
	 */
	//参数是个字符串部门名称就ok了,因为id的话是自增
	public void insert(String departmentname) {
		conn = ConnectionFactory.getConnection();
		String sql = "insert into department (departmentname) values(?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, departmentname);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
	}

	/**
	 * 根据id删除一个部门
	 * 
	 * @param departmentid
	 */
	//参数是部门id
	public void delete(int departmentid) {
		conn = ConnectionFactory.getConnection();
		String sql = "delete from department where departmentid=?;";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, departmentid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DepartmentDAO dao = new DepartmentDAO();
		//dao.insert("2");
		List<Department> list = dao.selectAll();		
		// 集合遍历
		for (Department d : list) {
			System.out.println(d);
		}
		
		

	}

}
