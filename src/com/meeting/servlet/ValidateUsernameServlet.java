package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.dao.EmployeeDAO;
import com.meeting.vo.Employee;

public class ValidateUsernameServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag=true;
		String message="";
		EmployeeDAO dao=new EmployeeDAO();
		//取出用户名
		Employee e=dao.selectByUsername(request.getParameter("username"));
		if(e==null){
			message="用户名可以使用";
		}else{
			flag=false;
			message="用户名已经存在，请选择使用其他用户名";
		}
		//返回的是xml,设置相应的结果类型
		 response.setContentType("text/xml;charset=gb2312");
		 PrintWriter out = response.getWriter();  
		 response.setHeader("Cache-Control","no-cache");
	     out.println("<?xml version='1.0' encoding='"+"gb2312"+"' ?>");
	     //根节点response
	     out.println("<response>");
	     //两个子元素passwd和message
	     out.println("<passed>" + Boolean.toString(flag) + "</passed>");
	     out.println("<message>" + message + "</message>");
	     out.println("</response>");
	     out.close();
		
	}

}
