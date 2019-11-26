package com.meeting.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCookieFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		
		String username=null;
		String password=null;
		//获取请求中所有cookie对象
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				//查找名字是username,password的cookie
				if(cookie.getName().equals("username")){
					username=cookie.getValue();
				}
				
				if(cookie.getName().equals("password")){
					password=cookie.getValue();
				}
			}
		}
		
		//如果不存在cookie,转到登录页面
		if(username==null||password==null){
			arg2.doFilter(arg0, arg1);	
		}else{
			//如果存在用户名和密码cookie,就直接到loginservlet去验证登录
			request.getRequestDispatcher("LoginServlet?username="+username+"&pwd="+password).forward(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
