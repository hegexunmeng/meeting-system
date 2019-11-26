package com.meeting.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meeting.service.EmployeeService;
import com.meeting.vo.Employee;

/**
 * Servlet implementation class LoginServlet
 */
// @WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		// 获取请求参数，username,pwd ,得到用户在表单输入的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");

		// 获得时间参数
		String timelength = request.getParameter("timelength");
		int days = 0;
		if (timelength != null) {
			days = Integer.parseInt(timelength);
		}
		if (days != 0) {
			// 如果不等于0,将用户名和密码作为cookie对象进行保存
			Cookie usernamecookie = new Cookie("username", username);
			Cookie passwordcookie = new Cookie("password", password);
			// 设置cookie的有效期
			usernamecookie.setMaxAge(days * 24 * 3600);
			passwordcookie.setMaxAge(days * 24 * 3600);
			// 把cookie加到响应里,存到客户端
			response.addCookie(usernamecookie);
			response.addCookie(passwordcookie);
		}

		// 调用业务逻辑，login方法
		EmployeeService service = new EmployeeService();

		// 定义一个flag来获得login的返回值
		int flag = service.login(username, password);
		// 根据返回值不同，跳转到不同视图,并且传递不同的提示信息属性msg(4种齐全了
		// 脚本通过setAttribute来设置
		if (flag == 1) {
			// 获得上下文对象
			ServletContext ctxt = this.getServletContext();
			// 判断上下文对象中是否存在visitcount,不存在初始为0，存在则取出使用
			int visitcount;
			if (ctxt.getAttribute("visitcount") == null) {
				visitcount = 0;
			} else {
				visitcount = Integer.parseInt(ctxt.getAttribute("visitcount").toString());
			}

			// visitcount自增1，并保存到上下文中
			visitcount++;
			ctxt.setAttribute("visitcount", visitcount);

			// 获得会话对象
			HttpSession session = request.getSession();
			Employee loginedEmployee = service.getLoginedEmployee();
			// 把登录成功的员工姓名和id保存到会话中,后面会用到
			session.setAttribute("employeename", loginedEmployee.getEmployeename());
			session.setAttribute("employeeid", loginedEmployee.getEmployeeid());
			
			// 根据角色，跳转到不同的首页面
			String role = loginedEmployee.getRole();
			if (role.equals("1")) {
				session.setAttribute("roler", "天才管理员");
				request.getRequestDispatcher("adminindex.jsp").forward(request, response);
			}
			if (role.equals("2")) {
				session.setAttribute("roler", "天才员工");
				request.getRequestDispatcher("employeeindex.jsp").forward(request, response);
			}
		} else {
			// 失败
			if (flag == 0) {
				request.setAttribute("msg", "正在审核，请耐心等待。");
			}
			if (flag == 2) {
				request.setAttribute("msg", "审核未通过，请核实后重新注册。");
			}

			if (flag == 3) {
				request.setAttribute("msg", "用户名或密码错误，请重试。");
			}
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
