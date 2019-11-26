package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.dao.EmployeeDAO;
import com.meeting.vo.Employee;

public class SelectEmployeesOfDeptServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取部门id
		int departmentid = Integer.parseInt(request
				.getParameter("departmentid"));
		
		//System.out.println("departmentid " + departmentid);
		
		EmployeeDAO dao = new EmployeeDAO();
		
		//根据部门的id来获取当前部门的员工列表
		List<Employee> employeesList = dao.selectEmployeesByDept(departmentid);

		//将查询得到的员工信息，以XML文档的格式返回到浏览器,用AJAX来解析它
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-cache");
		out.println("<?xml version='1.0' encoding='" + "utf-8" + "' ?>");

		//返回信息,符合XML规范，有根节点，否则解析有问题
		//根节点是employees,option里有每一个员工,值-id跟文本-员工名字
		out.println("<employees>");
		for (Employee e : employeesList) {
			out.println("<option>");
			out.println("<value>" + e.getEmployeeid() + "</value>");
			out.println("<text>" + e.getEmployeename() + "</text>");
			out.println("</option>");

		}
		out.println("</employees>");
		out.close();
	}

}
