package com.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meeting.util.ConnectionFactory;
import com.meeting.vo.Employee;

/**
 * 实现与Employee有关的增删改查操作
 * 
 * @author Administrator
 *
 */
public class EmployeeDAO {
	// 获得一个数据库连接
	private Connection conn = ConnectionFactory.getConnection();

	
	/**方法一:
	 * 根据用户名、密码进行查询，将查询得到的记录封装成Employee对象返回
	 * @param username
	 * @param pwd
	 * @return
	 */
	public Employee selectByNamePwd(String username, String pwd) {
		Employee employee = null;
		try {
			//创建PreparedStatement对象
			PreparedStatement st = null;
			//查询语句
			String sql = "select * from employee where username='" + username + "' and  password='" + pwd + "'";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			//判断结果集有无记录,如果有:则把内容取出来,变成一个employee对象,并且返回它
			if (rs.next() == true) {				
				employee = new Employee();				
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("status"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
		return employee;
	}
	
		/**
		 * 方法二:
		 * 通过用户名查询，返回Employee对象，把查到的所有属性返回，以便后续使用。
		 * 如果返回值为null，表示用户名不存在
		 * 查询用户名是否存在
		 * @param username
		 * @return
		 */
	 public Employee selectByUsername(String username){
		 conn=ConnectionFactory.getConnection();
		 Employee employee=null;	
		 try {
			 PreparedStatement st=null;
			String sql="select * from employee where username='"+username+"'";
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			if(rs.next()==true){
				employee=new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("status"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
			}
		 } catch (SQLException e) {
			    e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		 return employee;
	 }
	 
	 
	 	/**
	 	 * 方法三:
	 	 * 向表employee中插入记录，其中status和role使用默认值
	 	 * @param employee
	 	 */
	  public void insert(Employee employee){
		  conn=ConnectionFactory.getConnection();
		  String sql="insert into employee"
				  +
					"(employeename,username,password,phone,email,departmentid,status,role)" +
					" values(?,?,?,?,?,?,?,?)";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,employee.getEmployeename());
			pstmt.setString(2,employee.getUsername());
			pstmt.setString(3,employee.getPassword() );
			pstmt.setString(4,employee.getPhone() );
			pstmt.setString(5,employee.getEmail());
			pstmt.setInt(6,employee.getDepartmentid());			
			//注册成功后，默认为正在审核，status为0
			pstmt.setString(7,"0");
			//注册时，默认为员工角色，role值为2
			pstmt.setString(8,"2");
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}	  
	  }
	
	  /**
	   * 方法四:查询所有员工信息，返回到集合中
	   * @return
	   */
	  public List<Employee> selectAllEmployee(){
			 conn=ConnectionFactory.getConnection();
			 List<Employee> employeeslist=new ArrayList<Employee>();
			 Employee employee=null;	
			 try {
				PreparedStatement st=null;
				//只查询已注册且未审批 且 角色是员工的
				String sql="select * from employee where role='2' and status='0'";
		 		st = conn.prepareStatement(sql);
				ResultSet rs =st.executeQuery(sql);
				while(rs.next()){
					employee=new Employee();
					employee.setEmployeeid(rs.getInt("employeeid"));
					employee.setEmployeename(rs.getString("employeename"));
					employee.setUsername(rs.getString("username"));
					employee.setPhone(rs.getString("phone"));
					employee.setEmail(rs.getString("email"));
					employee.setStatus(rs.getString("status"));
					employee.setDepartmentid(rs.getInt("departmentid"));
					employee.setPassword(rs.getString("password"));
					employee.setRole(rs.getString("role"));
					employeeslist.add(employee);
				}
			 } catch (SQLException e) {
				    e.printStackTrace();
			}finally{
				//最后总要关闭连接
				ConnectionFactory.closeConnection();
			}
			 return employeeslist;
		 }
	  
	  /**
	   * 方法五:
	   * 向表employee中插入记录，其中status和role使用默认值
	   * @param employeeid 传入的员工id
	   * @param status	传入的员工状态
	   */
	  public void updateStatus(int employeeid,String status){
		  conn=ConnectionFactory.getConnection();
		  String sql="update employee set status='"+status+"'where employeeid="+employeeid;
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}	  
	  }
	  
		/**
		 * 方法六:
		 *  根据姓名、用户名、状态， 查询所有员工信息，返回到集合中。 
		 * @param employeename
		 * @param username
		 * @param status
		 * @return
		 */
		 public List<Employee> selectEmployeesByNameStatus(String employeename,String username,String status){
			 conn=ConnectionFactory.getConnection();
			 
			 List<Employee> employeeslist=new ArrayList<Employee>();
			 Employee employee=null;	
			 try {
				PreparedStatement st=null;
				String sql=null;
				String usernamesql,employeenamesql,statussql;
				//如果名字是null的,或者什么都没有
				if(employeename==null||employeename.equals("")){
					//就将员工名sql设置为空
					employeenamesql="";
				}else{
					//如果有,就把参数传进去
					employeenamesql=" and employeename='"+employeename+"'";
				}
				//下面是同理
				
				if(username==null||username.equals("")){
					usernamesql="";
				}else{
					usernamesql=" and username='"+username+"'";
				}
					
				
				if(status==null||status.equals("")||status.equals("3")){
					statussql="";
				}else{
					statussql=" and status='"+status+"'";
				}
				
				//role为2的是员工,把所有员工都查出来
				sql="select * from Employee where role='2' "+usernamesql+employeenamesql+statussql;
				
		 		st = conn.prepareStatement(sql);
				ResultSet rs =st.executeQuery(sql);
				while(rs.next()){
					employee=new Employee();
					employee.setEmployeeid(rs.getInt("employeeid"));
					employee.setEmployeename(rs.getString("employeename"));
					employee.setUsername(rs.getString("username"));
					employee.setPhone(rs.getString("phone"));
					employee.setEmail(rs.getString("email"));
					employee.setStatus(rs.getString("status"));
					employee.setDepartmentid(rs.getInt("departmentid"));
					employee.setPassword(rs.getString("password"));
					employee.setRole(rs.getString("role"));
					employeeslist.add(employee);
				}
			 } catch (SQLException e) {
				    e.printStackTrace();
			}finally{
				ConnectionFactory.closeConnection();
			}
			 return employeeslist;
		 }
	  
		 
		 /**
		  * 方法七:分页查询
		  * 根据姓名、用户名、状态， 从哪开始,count是多少行的意思。 
		  * @param employeename
		  * @param username
		  * @param status
		  * @param start
		  * @param count
		  * @return
		  */
		 public List<Employee> selectEmployeesOfOnePage(String employeename,String username,String status,int start,int count){
			 conn=ConnectionFactory.getConnection();
			 
			 List<Employee> employeeslist=new ArrayList<Employee>();
			 Employee employee=null;	
			 try {
				PreparedStatement st=null;
				String sql=null;
				String usernamesql,employeenamesql,statussql;
				
				//这里三个查询条件可以任意为空
				if(employeename==null||employeename.equals("")){
					employeenamesql="";
				}else{
					employeenamesql=" and employeename='"+employeename+"'";
				}
				
				
				if(username==null||username.equals("")){
					usernamesql="";
				}else{
					usernamesql=" and username='"+username+"'";
				}
					
				
				if(status==null||status.equals("")||status.equals("3")){
					statussql="";
				}else{
					statussql=" and status='"+status+"'";
				}
				
				//limit是MySQL中用来分页查询的，第一个int参数表示开始的索引，从0开始，第二个参数表示要查询的条数
				//role为2表示是员工
				sql="select * from Employee where role='2' "+usernamesql+employeenamesql+statussql+" limit "+start+
						" ,"+count;
				
		 		st = conn.prepareStatement(sql);
				ResultSet rs =st.executeQuery(sql);
				while(rs.next()){
					//把结果集放在employee那
					employee=new Employee();
					employee.setEmployeeid(rs.getInt("employeeid"));
					employee.setEmployeename(rs.getString("employeename"));
					employee.setUsername(rs.getString("username"));
					employee.setPhone(rs.getString("phone"));
					employee.setEmail(rs.getString("email"));
					employee.setStatus(rs.getString("status"));
					employee.setDepartmentid(rs.getInt("departmentid"));
					employee.setPassword(rs.getString("password"));
					employee.setRole(rs.getString("role"));
					employeeslist.add(employee);
				}
			 } catch (SQLException e) {
				    e.printStackTrace();
			}finally{
				ConnectionFactory.closeConnection();
			}
			 return employeeslist;
		 }
		 
		 
		/**
		 * 方法八:添加会议时,添加不同部门参加会议的人员
		 *  根据部门查询当前部门的员工
		 * @param departmentid
		 * @return
		 */
		 public List<Employee> selectEmployeesByDept(int departmentid){
			 conn=ConnectionFactory.getConnection();
			 List<Employee> employeeslist=new ArrayList<Employee>();
			 Employee employee=null;	 
			 try {
				PreparedStatement st=null;
				String sql="select * from employee where departmentid="+departmentid;
		 		st = conn.prepareStatement(sql);
				ResultSet rs =st.executeQuery(sql);
				while(rs.next()){
					employee=new Employee();
					employee.setEmployeeid(rs.getInt("employeeid"));
					employee.setEmployeename(rs.getString("employeename"));
					employee.setUsername(rs.getString("username"));
					employee.setPhone(rs.getString("phone"));
					employee.setEmail(rs.getString("email"));
					employee.setStatus(rs.getString("status"));
					employee.setDepartmentid(rs.getInt("departmentid"));
					employee.setPassword(rs.getString("password"));
					employee.setRole(rs.getString("role"));
					employeeslist.add(employee);
				}
			 } catch (SQLException e) {
				    e.printStackTrace();
			}finally{
				ConnectionFactory.closeConnection();
			}
			 return employeeslist;
		 }
		 
		 /**
		  * 方法九:根据id查询登陆者的信息		 
		  *  通过用户名查询，返回Employee对象，把查到的所有属性返回，以便后续使用。如果返回值为null，表示用户名不存在
		  * @param id
		  * @return
		  */
		 public Employee selectById(int id){
			 conn=ConnectionFactory.getConnection();
			 Employee employee=null;	
			 try {
				 PreparedStatement st=null;
				String sql="select * from employee where employeeid="+id;
		 		st = conn.prepareStatement(sql);
				ResultSet rs =st.executeQuery(sql);
				if(rs.next()==true){
					employee=new Employee();
					employee.setEmployeeid(rs.getInt("employeeid"));
					employee.setEmployeename(rs.getString("employeename"));
					employee.setUsername(rs.getString("username"));
					employee.setPhone(rs.getString("phone"));
					employee.setEmail(rs.getString("email"));
					employee.setStatus(rs.getString("status"));
					employee.setDepartmentid(rs.getInt("departmentid"));
					employee.setPassword(rs.getString("password"));
					employee.setRole(rs.getString("role"));
				}
			 } catch (SQLException e) {
				    e.printStackTrace();
			}finally{
				ConnectionFactory.closeConnection();
			}
			 return employee;
		 }
		 
	/**
	 * 用来测试的main方法
	 * @param args
	 */
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		
		//测试wangxh这个对象存不存在
		//Employee e = dao.selectByNamePwd("wangxh", "1");
		//Employee e=dao.selectByUsername("wangxh");
		//System.out.println(e);
		
		//dao.insert(new Employee("黄河","huangm2","1",1,"13567898765","huangml@qq.com","0","2"));
		

//		if (e != null) {
//			//存在则打印出来
//			System.out.println(e);
//		} else {
//			//不存在则打印失败
//			System.out.println("登录失败");
//		}
//	}
		List<Employee> list=dao.selectEmployeesByNameStatus(null, "linyk", null);
		for(Employee e:list){
			System.out.println(e);
		}
	}
}
