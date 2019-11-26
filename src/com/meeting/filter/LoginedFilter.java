package com.meeting.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginedFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		//获得客户端请求
		HttpServletRequest request=(HttpServletRequest)req;
		
		//先获取登录成功后的员工对象
		HttpSession session = request.getSession();
		String employeename=(String)session.getAttribute("employeename");
		
		//当这个对象为空时,就是没有登录,页面会跳转到登录页面,并且传一个消息msg为"请先登录"提醒它登录
		if(employeename==null){
			request.setAttribute("msg", "请先登录。");
			request.getRequestDispatcher("login.jsp").forward(req, res);
		}
		//当不为空的时候,doFilter,沿着过滤链往下传,请求会响应
		chain.doFilter(req, res);
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
