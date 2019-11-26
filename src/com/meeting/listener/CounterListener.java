package com.meeting.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.meeting.dao.CounterDAO;

public class CounterListener implements ServletContextListener {
	/**
	 * 上下文对象销毁时,自动调用
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ServletContext ctxt=arg0.getServletContext();
		int visitcount=Integer.parseInt(ctxt.getAttribute("visitcount").toString());
		CounterDAO dao=new CounterDAO();
		dao.update(visitcount);
	}
	/**
	 * 上下文创建时候,自动调用
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		CounterDAO dao=new CounterDAO();
		int visitcount=dao.select();
		ServletContext ctxt=arg0.getServletContext();
		ctxt.setAttribute("visitcount", visitcount);

	}

}
