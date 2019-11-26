package com.meeting.service;

import java.util.List;

import com.meeting.dao.EmployeeDAO;
import com.meeting.vo.Employee;

/**
 * 服务类,封装与Employee有关的业务逻辑
 * 
 * @author Administrator
 *
 */
public class EmployeeService {

	// 关联DAO类,因为登录的话需要使用到查询
	private EmployeeDAO dao = new EmployeeDAO();
	
	// 保存登录成功后的Employee对象
	/**
	 * 登陆成功后的对象
	 */
	private Employee loginedEmployee;
	
//	保存页数	
	private int countOfPages;
//	保存所有记录数量	
	private int countOfEmployees;
//	保存每一页记录数
	private int pageSize=3;
	

	/**
	 * 登录逻辑 :
	 * 用户名密码如果不正确，登录失败；
	 * 用户名密码正确，再看status的值，当且仅当status是1，登录成功
	 * flag=3:用户名密码不正确；
	 * flag=1:登录成功；
	 * flag=0:注册过，但是正在审核中；
	 * flag=2：注册过，审核没通过。
	 */
	public int login(String username, String pwd) {
		//默认为3
		int flag = 3;
		
		//先进行用户名密码来查看
		Employee e = dao.selectByNamePwd(username, pwd);
		
		//当e非空
		if (e != null) {
			
			//登录成功后把对象保存
			loginedEmployee=e;
			
			//获取status
			String status = e.getStatus();
			
			if (status != null && status.equals("1")) {
				flag = 1;
			}

			if (status != null && status.equals("0")) {
				flag = 0;
			}

			if (status != null && status.equals("2")) {
				flag = 2;
			}
		}
		return flag;
	}
	
	/**
	 * 返回登录成功后的员工对象
	 * @return
	 */
	public Employee getLoginedEmployee(){
		return loginedEmployee;
	}
	
	/**
	 * 注册功能，
	 * 如果账号名存在，注册失败，返回0，
	 * 否则注册成功，返回1
	 * @param employee
	 * @return
	 */
	public int regist(Employee employee){
		int flag=0;
		Employee e=dao.selectByUsername(employee.getUsername());
		if(e==null){
			flag=1;
			dao.insert(employee);
		}
		return flag;
	}
	
	/**
	 * 调用DAO的方法,通过员工姓名,用户名和注册状态,起始,还有查询条数来查询员工,
	 * @param employeename
	 * @param username
	 * @param status
	 * @return
	 */
	public List<Employee> searchEmployees(String employeename,String username,String status){
		List<Employee> list=dao.selectEmployeesByNameStatus(employeename, username, status);
//		获得全部的记录数量
		countOfEmployees=list.size();
		return list;
	}
	/**
	 * 查询每一页的数据集合
	 * @param employeename
	 * @param username
	 * @param status
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Employee> searchEmployeesOfOnePage(String employeename,String username,String status,int start,int count){
		return dao.selectEmployeesOfOnePage(employeename, username, status, start, count);
	}
	
	/**
	 * 返回总页数
	 * @return
	 */
	public int getCountOfPages(){
		//查询页数是,如果总数(countOfEmployees)除以每一页的记录数,如果除数不为0,也就是多了东西,就页数加一
		countOfPages=(countOfEmployees%pageSize==0)?countOfEmployees/pageSize:countOfEmployees/pageSize+1;
		return this.countOfPages;
	}
	
	/**
	 * 返回所有记录条数
	 * @return
	 */
	public int getCountOfEmployees(){
		return this.countOfEmployees;
	}
	
	/**
	 * 返回每页的记录条数，默认为3
	 * @return
	 */
	public int getPageSize(){
		return this.pageSize;
	}
	
	/**
	 * 测试用户名密码是否存在,如果存在则查看他的status
	 * @param args
	 */
	public static void main(String[] args) {
		EmployeeService service = new EmployeeService();
//		int flag = service.login("天才英俊", "1");
//		System.out.println(flag);
		System.out.println(service.regist(new Employee("英俊","yingjun","1",1,"13567898765","huangml@qq.com","0","2")));

	}

}
