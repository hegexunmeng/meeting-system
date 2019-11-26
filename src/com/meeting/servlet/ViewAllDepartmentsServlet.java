package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.dao.DepartmentDAO;
import com.meeting.vo.Department;

public class ViewAllDepartmentsServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DepartmentDAO dao=new DepartmentDAO();
		
		//查询所有部门信息
		List<Department> departmentsList=dao.selectAll();
		
		// 把部门请求保存到请求属性
		request.setAttribute("departmentsList", departmentsList);
		
		String code=request.getParameter("code");
		//注册时候显示的所有部门
		if(code!=null&&code.equals("regist")){
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		//登录进去后的部门管理
		if(code!=null&&code.equals("viewalldepartments")){
			request.getRequestDispatcher("departments.jsp").forward(request, response);
		}
	}

}
