package com.meeting.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.dao.DepartmentDAO;
import com.meeting.service.DepartmentService;
import com.meeting.service.EmployeeService;
import com.meeting.vo.Department;
import com.meeting.vo.Employee;

/**
 * Servlet implementation class RegistServlet
 */
//@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//			设置请求的编码格式保证中文编码正确
		//request.setCharacterEncoding("utf-8");

//			获取注册页面填写的请求参数
		String employeename = request.getParameter("employeename");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		// 把获取的信息new成一个Employee对象
		Employee employee = new Employee(employeename, username, password, deptid, email, phone, "0", "2");
		EmployeeService service = new EmployeeService();
		// 把Employee对象作为参数调用registe方法
		int flag = service.regist(employee);
		// 注册成功
		if (flag == 1) {
			request.setAttribute("msg", "注册成功，正在审核。");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			// 注册失败
			request.setAttribute("msg", "用户名已存在，请重新注册。");
			//加上遍历部门的代码,保证能够返回jsp页面
			
			DepartmentService ds = new DepartmentService();
			List<Department> departmentsList=ds.showDepartment();
			
			request.setAttribute("departmentsList", departmentsList);						
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}

	}

}
