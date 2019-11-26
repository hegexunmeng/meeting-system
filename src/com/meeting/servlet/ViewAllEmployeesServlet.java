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

public class ViewAllEmployeesServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//这里定义一个code去识别不同的请求
		String code=request.getParameter("code");
		
		//新建一个DAO去调用刚才写的selectAllEmployee()方法
		EmployeeDAO dao=new EmployeeDAO();
		List<Employee> employeesList=dao.selectAllEmployee();
		//把查询得到的集合存起来叫employeesList,并且请求转发到approveaccount.jsp
		request.setAttribute("employeesList", employeesList);
		
		if(code!=null&code.equals("approve")){
			request.getRequestDispatcher("approveaccount.jsp").forward(request, response);
		}
		
	}

}
