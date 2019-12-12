/**   
 * Copyright © 2019 --. All rights reserved.
 * 
 * @Package: data_processing 
 * @author: --   
 * @date: 2019年11月20日 下午6:13:24 
 */
package data_processing;

import java.awt.List;
import java.awt.print.Printable;
import java.sql.*;
import java.util.ArrayList;

import course_information.BeginCourse;
import course_information.Course;
import course_information.SelectCourse;
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
		String url = "jdbc:mysql://localhost:3306/teaching_system?characterEncoding=utf8&useSSL=false";//utf-8编码
		String user = "root";
		String password = "123456";
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
			return;
		}
		
		statement.close();
		connection.close();
	}
	
	
	public static void courseSelecting() {
		
	}
	
	//
	/** 
	 * @Title: getCoursesList 
	 * @Description: TODO 
	 * @param user_id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException ArrayList<Course> 
	 * @author: --
	 * @date 2019年12月5日下午4:13:08 
	 */
	public static ArrayList<Course> getCoursesList(String user_id) throws ClassNotFoundException, SQLException{
		ArrayList<Course> list = new ArrayList<Course>();
		Connection connection=con();
		Statement statement=connection.createStatement();
		String sql_col="select stu_college from student where stu_id='"+user_id+"'";
		ResultSet rs=statement.executeQuery(sql_col);
		String college = null;
		if(rs.next()) {
			college=rs.getString(1);
			//System.out.print(college);
		}
		
		
		String sql_cour="select * from course where cour_college='"+college+"'";
		ResultSet resultSet=statement.executeQuery(sql_cour);
		
		while(resultSet.next()){
			String cour_id = resultSet.getString(1);
			String cour_name = resultSet.getString(2);
			String cour_credit = resultSet.getString(3);
			Course cour = new Course(cour_id,cour_name,cour_credit,college) ;
			list.add(cour);
		}
		statement.close();
		connection.close();
		return list;
		
	}
		
		/** 
		 * @Title: getCoursesTable 
		 * @Description: TODO 
		 * @param course_name
		 * @return
		 * @throws ClassNotFoundException
		 * @throws SQLException ArrayList<BeginCourse> 
		 * @author: --
		 * @date 2019年12月5日下午4:13:18 
		 */
		public static ArrayList<BeginCourse> getCoursesTable(String course_name) throws ClassNotFoundException, SQLException{
			ArrayList<BeginCourse> list = new ArrayList<BeginCourse>();
			Connection connection=con();
			Statement statement=connection.createStatement();
			String sql_col="select cour_id from course where cour_name='"+course_name+"'";
			ResultSet rs=statement.executeQuery(sql_col);
			String course_id = null;
			if(rs.next()) {
				course_id=rs.getString(1);
				//System.out.print("获得课程：'"+course_name+"'的开课表");
			}
			
			
			String sql_cour="select * from begin_course where bc_course='"+course_id+"'";
			ResultSet resultSet=statement.executeQuery(sql_cour);
			
			
			while(resultSet.next()){
				String bc_id = resultSet.getString(1);
				String bc_cour = resultSet.getString(2);
				String bc_class = resultSet.getString(3);
				String bc_teacher = resultSet.getString(4);
				String bc_classroom= resultSet.getString(5);
				int bc_capacity = resultSet.getInt(6);
				int bc_beS = resultSet.getInt(7);
				String bc_BTime= resultSet.getString(8);
				String bc_ETime= resultSet.getString(9);
				String bc_time= resultSet.getString(10);
				//System.out.print("   '"++"'");
				
				BeginCourse cour = new BeginCourse(bc_id,bc_cour,bc_teacher,bc_class,bc_classroom,bc_capacity,bc_BTime,bc_ETime,bc_time) ;
				cour.SetBeSelected(bc_beS);
				//System.out.print("   '"+cour.getBeSelect()+"'");
				list.add(cour);
			}
			for(int i=0;i<list.size();i++) {
				String bc_teacher=list.get(i).getBcTeacher();
				String sql_teac="select user_name from user where user_id='"+bc_teacher+"'";
				ResultSet rs_teac=statement.executeQuery(sql_teac);
				String teac_name = null;
				if(rs_teac.next())
					teac_name=rs_teac.getString(1);
				list.get(i).setBcTeacher(teac_name);
				//System.out.print("   '"+teac_name+"'");
			}
			for(int i=0;i<list.size();i++) {
				String bc_classroom=list.get(i).getBcClassroom();
				String sql_teac="select classroom_name from classroom where classroom_id='"+bc_classroom+"'";
				ResultSet rs_room=statement.executeQuery(sql_teac);
				String classroom_name = null;
				if(rs_room.next())
					classroom_name=rs_room.getString(1);
				list.get(i).setBcClassroom(classroom_name);
				//System.out.print("   '"+classroom_name+"'");
			}
		
		statement.close();
		connection.close();
		return list;
		
	}
		
	/** 
	 * @Title: getDropCourses 
	 * @Description: TODO 
	 * @param stu_id
	 * @param term
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException ArrayList<BeginCourse> 
	 * @author: --
	 * @date 2019年12月8日下午12:10:09 
	 */
	public static ArrayList<BeginCourse> getDropCourses(String stu_id,String term) throws ClassNotFoundException, SQLException{
		ArrayList<BeginCourse> list = new ArrayList<BeginCourse>();
		Connection connection=con();
		Statement statement=connection.createStatement();
		String Drop="select * from begin_course where bc_course = any(select sc_course from select_course where sc_student='"+stu_id+"' and sc_term='"+term+"')"
				+ " and bc_class =any(select sc_course from select_course where sc_student='"+stu_id+"' and sc_term='"+term+"')";
		ResultSet resultSet=statement.executeQuery(Drop);
		
		while(resultSet.next()){
			String bc_id = resultSet.getString(1);
			String bc_cour = resultSet.getString(2);
			String bc_class = resultSet.getString(3);
			String bc_teacher = resultSet.getString(4);
			String bc_classroom= resultSet.getString(5);
			int bc_capacity = resultSet.getInt(6);
			int bc_beS = resultSet.getInt(7);
			String bc_BTime= resultSet.getString(8);
			String bc_ETime= resultSet.getString(9);
			String bc_time= resultSet.getString(10);
			//System.out.print("   '"++"'");
			
			BeginCourse cour = new BeginCourse(bc_id,bc_cour,bc_teacher,bc_class,bc_classroom,bc_capacity,bc_BTime,bc_ETime,bc_time) ;
			cour.SetBeSelected(bc_beS);
			//System.out.print("   '"+cour.getBeSelect()+"'");
			list.add(cour);
		}
		for(int i=0;i<list.size();i++) {
			String bc_teacher=list.get(i).getBcTeacher();
			String sql_teac="select user_name from user where user_id='"+bc_teacher+"'";
			ResultSet rs_teac=statement.executeQuery(sql_teac);
			String teac_name = null;
			if(rs_teac.next())
				teac_name=rs_teac.getString(1);
			list.get(i).setBcTeacher(teac_name);
			//System.out.print("   '"+teac_name+"'");
		}
		for(int i=0;i<list.size();i++) {
			String bc_course=list.get(i).getBcCourse();
			String sql_cour="select cour_name from course where cour_id='"+bc_course+"'";
			ResultSet rs_cour=statement.executeQuery(sql_cour);
			String cour_name = null;
			if(rs_cour.next())
				cour_name=rs_cour.getString(1);
			list.get(i).setBcCourse(cour_name);;
			//System.out.print("   '"+teac_name+"'");
		}
		for(int i=0;i<list.size();i++) {
			String bc_classroom=list.get(i).getBcClassroom();
			String sql_teac="select classroom_name from classroom where classroom_id='"+bc_classroom+"'";
			ResultSet rs_room=statement.executeQuery(sql_teac);
			String classroom_name = null;
			if(rs_room.next())
				classroom_name=rs_room.getString(1);
			list.get(i).setBcClassroom(classroom_name);
			//System.out.print("   '"+classroom_name+"'");
		}
		statement.close();
		connection.close();
		
		return list;
		
	}
	
		
	/** 
	 * @Title: StuSelectCourse 
	 * @Description: TODO 
	 * @param selectCourse
	 * @throws ClassNotFoundException
	 * @throws SQLException void 
	 * @author: --
	 * @date 2019年12月8日上午10:34:26 
	 */
	public static void StuSelectCourse(SelectCourse selectCourse) throws ClassNotFoundException, SQLException {
		Connection connection=con();
		Statement statement=connection.createStatement();
		PreparedStatement stmt;
		
		String sql_cour="select cour_id from course where cour_name='"+selectCourse.getScCourse()+"'";
		ResultSet rs_cour=statement.executeQuery(sql_cour);
		String cour_id = null;
		if(rs_cour.next()) {
			cour_id=rs_cour.getString(1);
			selectCourse.setScCourse(cour_id);
			//System.out.print(college);
		}
		
		String sql_col="select sc_id from select_course order by sc_id desc LIMIT 1";
		ResultSet rs=statement.executeQuery(sql_col);
		String NewRecord = null;
		if(rs.next()) {
			NewRecord=rs.getString(1);
			//System.out.print(college);
		}
		if(NewRecord==null) NewRecord="0000";
		int id  = Integer.parseInt(NewRecord);
		id++;
		int s=id;
		String new_id="";
		for(int i=0;i<4;i++) {
			new_id=s%10+new_id;
			s=s/10;
		}
		selectCourse.setScId(new_id);
		
		//System.out.print("\n");
		//System.out.print(selectCourse.getScClass());
		
		String insert_data="insert into select_course values (?,?,?,?,?,?)";
		stmt = (PreparedStatement) connection.prepareStatement(insert_data);
		stmt.setString(1, selectCourse.getScId());
		stmt.setString(3, selectCourse.getScClass());
		stmt.setString(2, selectCourse.getScCourse());
		stmt.setString(4, selectCourse.getScStudent());
		stmt.setString(5, selectCourse.getScterm());
		stmt.setInt(6, -1);
		stmt.executeUpdate();
		
		String sql_bc="select bc_selected from begin_course where bc_course='"+selectCourse.getScCourse()+"' and bc_class='"+selectCourse.getScClass()+"'";
		ResultSet rs_bc=statement.executeQuery(sql_bc);
		int num=0;
		if(rs_bc.next()) {
			num=rs_bc.getInt(1);
		}
		if(num<0) num=0;
		num++;
		
		String sql_update_bc="update begin_course set bc_selected ='"+num+"' where bc_course='"+selectCourse.getScCourse()+"' and bc_class='"+selectCourse.getScClass()+"'";
		statement.executeUpdate(sql_update_bc);
		
		statement.close();
		connection.close();
		
		
	}
	
	public static void stuDropCourse(String stu_id,String Cour_name,String cour_class,String term) throws ClassNotFoundException, SQLException {
		Connection connection=con();
		Statement statement=connection.createStatement();

		String sql_cour="select cour_id from course where cour_name='"+Cour_name+"'";
		ResultSet rs_cour=statement.executeQuery(sql_cour);
		String cour_id = null;
		if(rs_cour.next()) {
			cour_id=rs_cour.getString(1);
			Cour_name=cour_id;
			//System.out.print(college);
		}
		
		String sql_drop="delete from select_course where sc_student='"+stu_id+"' and sc_course='"+Cour_name+"' and sc_term='"+term+"'";
		statement.executeUpdate(sql_drop);
		
		String sql_bc="select bc_selected from begin_course where bc_course='"+Cour_name+"' and bc_class='"+cour_class+"'";
		ResultSet rs_bc=statement.executeQuery(sql_bc);
		int num=0;
		if(rs_bc.next()) {
			num=rs_bc.getInt(1);
		}
		num--;
		if(num<0) num=0;
		
		String sql_update="update begin_course set bc_selected ='"+num+"' where bc_course='"+Cour_name+"' and bc_class='"+cour_class+"'";
		statement.executeUpdate(sql_update);
		
		statement.close();
		connection.close();
	}
}
