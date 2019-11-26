package com.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meeting.dao.EmployeeDAO;

import com.meeting.service.EmployeeService;
import com.meeting.vo.Employee;
/**
 * 搜索员工功能的
 * @author Administrator
 *
 */
public class SearchEmployeesServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 取出前端的参数,获取输入的查询条件:用户名,账户名,注册状态
		String employeename = request.getParameter("employeename");
		String username = request.getParameter("username");
		String status = request.getParameter("status");
		
		//调用业务逻辑,得到结果集,存到请求中,跳转到JSP页面的过程
		EmployeeService service = new EmployeeService();
		
		//当前页码  假设当前页码是三,那么就会显示第三页,那么我们需要知道它是从第几条记录开始
		String pageNumStr=request.getParameter("pageNum");
		int pageNum=0;
		if(pageNumStr==null||pageNumStr.equals("")){
			pageNum=1;
		}else{
			pageNum=Integer.parseInt(pageNumStr);
		}
		
//		每页的记录数量
		int pageSize=service.getPageSize();			
//		起始记录索引 三页,每页三条,那么起始记录的是6
		int start=(pageNum-1)*pageSize;			
//		查询的数量=每一页的数量
		int count=pageSize;
//		获得所有记录数量，先调用DAO中的search方法
		service.searchEmployees(employeename, username, status);
		int countOfEmployees=service.getCountOfEmployees();
//		页数
		int countOfPages=service.getCountOfPages();
		
		
		
		
		//调用searchEmployee
		//List<Employee> list = service.searchEmployees(employeename, username, status);
		//调用这个方法去取出值出来
		List<Employee> list=service.searchEmployeesOfOnePage(employeename, username, status,start,count);
		
		
		//得到一个集合赋给Employeelist,然后把它存到searchemployees.jsp
		request.setAttribute("employeesList", list);
		//这里存了个属性为1的search
		request.setAttribute("search", "1");
		
		//存储页数、所有记录的数量、当前页码 存成属性
		request.setAttribute("countOfPages", countOfPages);
		request.setAttribute("countOfEmployees", countOfEmployees);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("searchemployees.jsp").forward(request, response);

	}

}
