package function_frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import course_information.BeginCourse;
import course_information.Course;
import course_information.SelectCourse;
import data_processing.DataHandling;
import user_information.User;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;


public class CourseSelecting extends JFrame {
	
	private static final long serialVersionUID = 2L;
	
	private User user;
	
	private DefaultTableModel tableModel;
	private JTable table_select,table_drop;
	private JList<String> jList;
	private JButton btnSelectButton;
	private JButton btnDropButton;
	JPanel panel_up,panel_down;
	JTextField DoctextField,filetextField;
	JTabbedPane tabbedPane ;
	JTextArea textArea;
	
	@SuppressWarnings("deprecation")
	public CourseSelecting(final User user){
		this.user=user;
		setTitle("选课");
		setSize(1200, 750);
		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenHeight = dimension.height;
		int screenWidth = dimension.width;
		int frm_Height = this.getHeight();
		int frm_width = this.getWidth();
		this.setLocation((screenWidth - frm_width) / 2,
				(screenHeight - frm_Height) / 2);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		//课程列表
		final JPanel selectCourses = new JPanel();
		tabbedPane.addTab("", null, selectCourses, null);
		selectCourses.setLayout(null);	
		
		showCoursesListToTable();
		
		JScrollPane CoursesList = new JScrollPane(jList);
		//CoursesList.add();
		selectCourses.add(CoursesList);
		CoursesList.setBounds(0, 0, 300, 720);
		/*
		{
			String s="00010";
			int y  = Integer.parseInt(s);
			System.out.print("\n");
			System.out.print(y);
			System.out.print("\n");
		}
		*/
		
		//table_select.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//table_select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_select=new JTable();
		table_drop=new JTable();
		
		//选课
		JScrollPane CoursesSelect = new JScrollPane(table_select);
		selectCourses.add(CoursesSelect);
		btnSelectButton=new JButton("选课");
		selectCourses.add(btnSelectButton);
		btnSelectButton.setBounds(300, 0, 80, 25);
		CoursesSelect.setBounds(300, 25, 880, 320);
		
		//showCoursesTableToTable();
		//退课
		JScrollPane CoursesDrop =new JScrollPane(table_drop);
		selectCourses.add(CoursesDrop);
		btnDropButton =new JButton("退选");
		selectCourses.add(btnDropButton);
		btnDropButton.setBounds(300,360,80,25);
		CoursesDrop.setBounds(300, 390, 880, 280);
		
		
		
		jList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				showCoursesTableToTable();
				table_select.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table_select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				showDropTableToTable();
				table_drop.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table_drop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				
			}

			
		});
		
		btnSelectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Calendar cal = Calendar.getInstance();
				int i=table_select.getSelectedRow(); 
				if(i<0) return;
				String cour_name=jList.getSelectedValue();
				String stu_id=user.getUserId();
				String bc_class=table_select.getValueAt(i, 3).toString();
				//System.out.print(bc_class);
				
				SelectCourse  Sc = new SelectCourse("0000", cour_name, stu_id, "");
				Sc.setScClass(bc_class);
				//学期
				int year=cal.get(Calendar.YEAR);
				int month=cal.get(Calendar.MONTH)+1;
				int term=1;
				if(month<10) term = 1;
				else term = 2;
				if(month<4) term=2;
				else term=1;
				if(term==1) Sc.setScterm(year+"-"+(year+1)+"-"+term);
				else Sc.setScterm(year-1+"-"+year+"-"+term);
				boolean can=true;
				try {
					ArrayList<BeginCourse> list = DataHandling.getDropCourses(user.getUserId(), Sc.getScterm());
					for(int j=0;j<list.size();j++) {
						if(Sc.getScCourse().equals(list.get(j).getBcCourse())) {
							//DataHandling.StuSelectCourse(Sc);
							JOptionPane.showMessageDialog(selectCourses, "选课重复", "出错",JOptionPane.WARNING_MESSAGE);
							System.out.print("\n选课重复："+Sc.getScCourse()+" "+list.get(j).getBcCourse()+"\n");
							can=false;
						}
						
					}
					if(can) DataHandling.StuSelectCourse(Sc);
						//showCoursesTableToTable();
						//showDropTableToTable();
						//
						showCoursesTableToTable();
						table_select.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						table_select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						
						showDropTableToTable();
						table_drop.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						table_drop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnDropButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Calendar cal = Calendar.getInstance();
				int i=table_drop.getSelectedRow(); 
				if(i<0) return;
				String cour_name=table_drop.getValueAt(i,1 ).toString();
				String stu_id=user.getUserId();
				String bc_class=table_drop.getValueAt(i, 3).toString();
				
				//System.out.print(bc_class);
				
				SelectCourse  Sc = new SelectCourse("0000", cour_name, stu_id, "");
				Sc.setScClass(bc_class);
				//学期
				int year=cal.get(Calendar.YEAR);
				int month=cal.get(Calendar.MONTH)+1;
				int term=1;
				if(month<10) term = 1;
				else term = 2;
				if(month<4) term=2;
				else term=1;
				if(term==1) Sc.setScterm(year+"-"+(year+1)+"-"+term);
				else Sc.setScterm(year-1+"-"+year+"-"+term);
				boolean can=true;
				
				try {
					DataHandling.stuDropCourse(user.getUserId(), cour_name, bc_class,Sc.getScterm());
					//showCoursesTableToTable();
					//showDropTableToTable();
					
					showCoursesTableToTable();
					table_select.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					table_select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					
					showDropTableToTable();
					table_drop.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					table_drop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
	}

	/** 
	 * @Title: showCoursesTableToTable 
	 * @Description: TODO  void 
	 * @author: --
	 * @date 2019年12月5日下午2:04:03 
	 */
	private void showCoursesTableToTable() {
		// TODO Auto-generated method stub
		try {
			//String[] colName = {"课程号", "课程名", "班号","老师","容量","已选","上课时间","起始周","结束周","教室"};
			//String[][] tableValue = new String[10][10];
			String cour_name = jList.getSelectedValue();
			ArrayList<BeginCourse> list = DataHandling.getCoursesTable(cour_name);
			String[] colName = {"课程号","课程名","老师","开课班号","容量","已选","上课时间","起始周","结束周","教室"};
			String[][] tableValue = new String[list.size()][10];
			
			
			for(int row=0;row<list.size();row++ ) {
				BeginCourse bc = list.get(row);
				tableValue[row][0]=bc.getBcId();
				tableValue[row][1]=cour_name;
				tableValue[row][2]=bc.getBcTeacher();
				tableValue[row][4]=Integer.toString(bc.getBcCapacity());
				tableValue[row][3]=bc.getBcClass();
				tableValue[row][5]=Integer.toString(bc.getBeSelect());
				tableValue[row][6]=bc.getBctime();
				tableValue[row][7]=bc.getBcBegintime();
				tableValue[row][8]=bc.getBcEndtime();
				tableValue[row][9]=bc.getBcClassroom();
			}
			DefaultTableModel tableModel = new DefaultTableModel(tableValue, colName);
			table_select.setModel(tableModel);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/** 
	 * @Title: showDropTableToTable 
	 * @Description: TODO  void 
	 * @author: --
	 * @date 2019年12月8日上午11:32:57 
	 */
	private void showDropTableToTable() {
		// TODO Auto-generated method stub
		try {
			
			Calendar cal = Calendar.getInstance();
			//
			int year=cal.get(Calendar.YEAR);
			int month=cal.get(Calendar.MONTH)+1;
			int term=1;
			String TERM="";
			if(month<10) term = 1;
			else term = 2;
			if(month<4) term=2;
			else term=1;
			if(term==1) TERM=year+"-"+(year+1)+"-"+term;
			else TERM=year-1+"-"+year+"-"+term;
			//
			ArrayList<BeginCourse> list = DataHandling.getDropCourses(user.getUserId(), TERM);
			
			String[] colName = {"课程号","课程名","老师","开课班号","容量","已选","上课时间","起始周","结束周","教室"};
			String[][] tableValue = new String[list.size()][10];
			
			for(int row=0;row<list.size();row++ ) {
				BeginCourse bc = list.get(row);
				tableValue[row][0]=bc.getBcId();
				tableValue[row][1]=bc.getBcCourse();
				tableValue[row][2]=bc.getBcTeacher();
				tableValue[row][4]=Integer.toString(bc.getBcCapacity());
				tableValue[row][3]=bc.getBcClass();
				tableValue[row][5]=Integer.toString(bc.getBeSelect());
				tableValue[row][6]=bc.getBctime();
				tableValue[row][7]=bc.getBcBegintime();
				tableValue[row][8]=bc.getBcEndtime();
				tableValue[row][9]=bc.getBcClassroom();
			}
			
			DefaultTableModel tableModel = new DefaultTableModel(tableValue, colName);
			table_drop.setModel(tableModel);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void showCoursesListToTable() {
		// TODO Auto-generated method stub
		final DefaultListModel<String> CourseName = new DefaultListModel<String>();

		try {
			ArrayList<Course> list = DataHandling.getCoursesList(user.getUserId());
			for(int i=0;i<list.size();i++) {
				CourseName.addElement(list.get(i).getCourseName());
			}
			jList=new JList(CourseName);
			jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		
		
	}


	/*public void setUser(User user) {
		this.user=user;
	}
	
	
	public static void launch() {	   
		
	}
	*/
	public static void main(String[] args) {
		//CourseSelecting.launch();	
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
		            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		        } catch (ClassNotFoundException | InstantiationException | 
		        		IllegalAccessException | UnsupportedLookAndFeelException e) {
		            e.printStackTrace();
		        }
				
				new CourseSelecting(new User("030001",
						"tom", "1234", "Student")).setVisible(true);
			}
		});
	}
	

}
