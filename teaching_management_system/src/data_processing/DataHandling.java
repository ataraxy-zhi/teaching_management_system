/**   
 * Copyright © 2019 --. All rights reserved.
 * 
 * @Package: data_processing 
 * @author: --   
 * @date: 2019年11月20日 下午6:13:24 
 */
package data_processing;

import java.sql.*;
import java.util.ArrayList;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.regexp.internal.recompile;

import user_information.User;

/** 
 * @ClassName: DataHandling 
 * @Description: 涉及数据库信息处理
 * @author: --
 * @date: 2019年11月20日 下午6:13:24  
 */
public class DataHandling {
	

	/** 
	 * @Title: Init 
	 * @Description: 初始化数据库连接驱动
	 * @throws ClassNotFoundException void 
	 * @author: --
	 * @date 2019年11月21日下午6:26:30 
	 */
	private static void Init() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @Title: con 
	 * @Description: 连接数据库
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException Connection 
	 * @author: --
	 * @date 2019年11月21日下午6:27:06 
	 */
	private static Connection con() throws SQLException, ClassNotFoundException{
		Init();
		String url = "jdbc:mysql://localhost:3306/new_schema?characterEncoding=utf8&useSSL=false";//utf-8编码
		String user = "root";
		String password = "412554";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/** 
	 * @Title: login 
	 * @Description: 登录用户时，在数据库中搜索相关信息是否正确，并创建User对象
	 * @param id
	 * @param password
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException User 
	 * @author: --
	 * @date 2019年11月21日下午6:42:56 
	 */
	public static User login(String id,String password) throws ClassNotFoundException, SQLException{
		User user=null;
		Connection connection=con();
		Statement statement=connection.createStatement();
		String sql="select * from user where user_id='"+id+"'and user_password='"+password+"'";
		
		try {
			ResultSet resultSet=statement.executeQuery(sql);
			if(resultSet.next()) {
				user=new User(resultSet.getString(1), resultSet.getString(2), 
						resultSet.getString(3), resultSet.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		statement.close();
		connection.close();
				
		return user;
	}
	
	/** 
	 * @Title: modifyPassword 
	 * @Description: 修改密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @throws ClassNotFoundException
	 * @throws SQLException void 
	 * @author: --
	 * @date 2019年11月22日上午9:56:52 
	 */
	public static void modifyPassword(String id,String oldPassword,String newPassword) throws ClassNotFoundException, SQLException {
		Connection connection=con();
		Statement statement=connection.createStatement();
		String sql="select * from user where user_id='"+id+"'and user_password='"+oldPassword+"'";
		ResultSet resultSet=statement.executeQuery(sql);
		
		if(resultSet.next()) {
			resultSet.close();
			sql="update user set user_password='"+newPassword+"' where user_id='"+id+"'";
			statement.executeUpdate(sql);			
			//return;
		}	
		statement.close();
		connection.close();
	}
	

	/** 
	 * @Title: getCourse 
	 * @Description: 根据教师工号得到其所授课程的课程号-课程名 
	 * @param teacId 教师工号
	 * @return ArrayList<String> 课程号-课程名(拼接成一个字符串)
	 * @throws ClassNotFoundException
	 * @throws SQLException ArrayList<String> 
	 * @author: --
	 * @date 2019年11月27日下午8:42:46 
	 */
	public static ArrayList<String> getCourse(String teacId)  throws ClassNotFoundException, SQLException  {
		Connection connection=con();
		Statement statement=connection.createStatement();
		String sql=
				"select cour_id,cour_name from course where cour_id in(select bc_course from begin_course where bc_teacher='"+teacId+"')"; 		
		ResultSet resultSet=statement.executeQuery(sql);			
		ArrayList<String> listCourse=new ArrayList<String>();
		
		while(resultSet.next()) {
			listCourse.add(resultSet.getString(1)+"-"+resultSet.getString(2));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return listCourse;
	}
	
	/** 
	 * @Title: getStudent 
	 * @Description: 根据课程号得到选该课程的学生学号
	 * @param courseId
	 * @return ArrayList<String> 选课学生
	 * @author: --
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @date 2019年11月27日下午9:02:41 
	 */
	public static ArrayList<String> getStudent(String courseId) throws ClassNotFoundException, SQLException{
		Connection connection=con();
		Statement statement=connection.createStatement();
		String sql=
				"select stu_id,stu_name from student where stu_id in(select sc_student from select_course where sc_course='"+courseId+"')"; 				
		ResultSet resultSet=statement.executeQuery(sql);			
		ArrayList<String> listStudent=new ArrayList<String>();
		
		while(resultSet.next()) {
			listStudent.add(resultSet.getString(1)+"-"+resultSet.getString(2));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return listStudent;
	}
	
	
	/** 
	 * @Title: getGrade 
	 * @Description: 根据学生学号、课程号查询学生成绩
	 * @param stuId
	 * @param courId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException double 
	 * @author: --
	 * @date 2019年11月28日下午3:04:03 
	 */
	public static double getGrade(String stuId,String courId) throws ClassNotFoundException, SQLException {
		Connection connection=con();
		Statement statement=connection.createStatement();
		String sql=
				"select sc_grade from select_course where sc_student='"+stuId+"'and sc_course='"+courId+"'";
		ResultSet resultSet=statement.executeQuery(sql);
		
		double grade = 0;
		if(resultSet.next()) {
			grade=resultSet.getDouble(1);
		}
		
		return grade;
	}
	
	
	/** 
	 * @Title: updateGrade 
	 * @Description: 更新学生成绩
	 * @param stuId
	 * @param courId
	 * @param grade
	 * @throws ClassNotFoundException
	 * @throws SQLException void 
	 * @author: --
	 * @date 2019年11月28日下午3:37:23 
	 */
	public static void updateGrade(String stuId,String courId,double grade) throws ClassNotFoundException, SQLException {
		Connection connection=con();
		Statement statement=connection.createStatement();
		String sql=
				"select * from select_course where sc_student='"+stuId+"'and sc_course='"+courId+"'";
		ResultSet resultSet=statement.executeQuery(sql);
		
		if(resultSet.next()) {
			resultSet.close();
			sql=
					"update select_course set sc_grade='"+grade+"'where sc_student='"+stuId+"'and sc_course='"+courId+"'";
			statement.executeUpdate(sql);
		}
		
		statement.close();
		connection.close();
	}
	
	//添加老师,更新teacher表和user表
	public static void addTeacherInfomation(user_information.Teacher[] teachers) throws ClassNotFoundException, SQLException {
		Connection connection = con();
		Statement statement = connection.createStatement();
		//生成老师的id
		ResultSet rs = statement.executeQuery("select * from teacher");
		int baseId = 0;
		while(rs.next()) {
			baseId = Integer.parseInt(rs.getString(1).toString());
		}
		rs.close();
		for(int i=0;i<100;i++) {
			if(teachers[i].getName()==null) break;
			teachers[i].SetTeacherId(getId(i,baseId));
			//更新数据库
			statement.executeUpdate("insert into teacher(teac_id,teac_name,teac_college) values"+teachers[i].toString());
		}
		//更新数据库
		statement.close();
		connection.close();
		
	}
	
	//自动生成工号或者学号
	public static String getId(int count,int baseId)
	{
		baseId=baseId + 1 + count;
		String id = null;
		char[] Str =new char[6];
		for(int i=0;i<5;i++) {
			Str[5-i] = (char) (baseId%10+'0');
			baseId=baseId/10;
		}
		Str[0] = '0';
		id =String.valueOf(Str);
		return id;
	}
	
}
