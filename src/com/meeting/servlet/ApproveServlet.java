package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.dao.EmployeeDAO;

public class ApproveServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到需要审批的员工id
		int employeeid=Integer.parseInt(request.getParameter("employeeid"));
		//用oper来知道审批的操作是通过还是不通过
		String oper =request.getParameter("oper");
		EmployeeDAO dao=new EmployeeDAO();
		
		if(oper!=null&&oper.equals("yes")){
			//通过
			dao.updateStatus(employeeid,"1");
			request.getRequestDispatcher("ViewAllEmployeesServlet?code=approve").forward(request, response);
		}
		
		if(oper!=null&&oper.equals("no")){
			//不通过
			dao.updateStatus(employeeid,"2");
			request.getRequestDispatcher("ViewAllEmployeesServlet?code=approve").forward(request, response);
		}
		if(oper!=null&&oper.equals("close")){
			dao.updateStatus(employeeid,"2");
			//根据现在的状态,重新做了一次查询
			request.getRequestDispatcher("SearchEmployeesServlet").forward(request, response);
		}	
		
	}

}
