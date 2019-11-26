package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.service.DepartmentService;

public class AddDeleteDepartmentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//因为部门的名字是中文,设置请求的编码格式保证中文编码正确
		//request.setCharacterEncoding("utf-8");
		//获得code值
		String code=request.getParameter("code");
		
		DepartmentService service=new DepartmentService();
		
		if(code!=null&&code.equals("add")){
			service.addDepartment(request.getParameter("departmentname"));
		}
		
		if(code!=null&&code.equals("delete")){
			//Integer.parseInt 强转int
			service.deleteDepartment(Integer.parseInt(request.getParameter("departmentid")));
		}
		
		//全部转发到ViewAllDepartmentsServlet 这个Servlet类
		request.getRequestDispatcher("ViewAllDepartmentsServlet?code=viewalldepartments").forward(request, response);
	}

}
