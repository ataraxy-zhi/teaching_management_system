/**   
 * Copyright © 2019 --. All rights reserved.
 * 
 * @Package: functionframe 
 * @author: --   
 * @date: 2019年12月25日 上午7:52:52 
 */
package functionframe;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import courseinformation.BeginCourse;
import courseinformation.Course;
import courseinformation.SelectCourse;
import dataprocessing.DataHandling;
import userinformation.User;
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
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

/** 
 * @ClassName: CheckGrade 
 * @Description: TODO
 * @author: --
 * @date: 2019年12月19日 下午3:18:07  
 */
public class CheckGrade extends JFrame{
	
    private static final long serialVersionUID = 1L;
	
	private User user;
	
	private DefaultTableModel tableModel;
	private JTable grade;
	private JList<String> jList;
	private JComboBox jcb;
	private JLabel label;
	private JButton btnCheck;
	JPanel panel_grade;
	JPanel panel_table;
	JTextField DoctextField,filetextField;
	JTextArea textArea;
	JScrollPane jsp;
	
    /** 
	 * @Title:CheckGrade
	 * @Description:TODO 
	 * @param user2 
     * @throws SQLException 
     * @throws ClassNotFoundException 
	 */  
	public CheckGrade(User user) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		this.user=user;
		setTitle("查看成绩");
		setSize(1200, 750);
		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenHeight = dimension.height;
		int screenWidth = dimension.width;
		int frm_Height = this.getHeight();
		int frm_width = this.getWidth();
		this.setLocation((screenWidth - frm_width) / 2,
				(screenHeight - frm_Height) / 2);
		panel_grade = new JPanel();
		getContentPane().add(panel_grade, BorderLayout.LINE_START);
		//this.add(panel_grade);
		panel_grade.getLayout();
		
		
		showTermListToTable();
		label=new JLabel("学期：");
		panel_grade.add(label);
		panel_grade.add(jcb);
		jcb.setBounds(0, 0, 100, 50);
		
		btnCheck=new JButton("查询");
		panel_grade.add(btnCheck);
		
		grade=new JTable();
		showGradeTOTable();
		jsp=new JScrollPane(grade);
		this.add(jsp);

		//jsp.setBounds(0, 50, 1200, 700);
		btnCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					showGradeTOTable();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
	

	
	
	
	
	
	/** 
	 * @Title: showGradeTOTable 
	 * @Description: TODO  void 
	 * @author: --
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @date 2019年12月19日下午4:19:37 
	 */
	private void showGradeTOTable() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//String cour_name = jList.getSelectedValue();
		String term = jcb.getSelectedItem().toString();
		System.out.print(term);
		ArrayList<SelectCourse> list = DataHandling.getGradeTable(this.user.getUserId(),term);
		String[] colName = {"学期","课程号","课程名","开课班号","成绩"};
		String[][] tableValue = new String[list.size()][5];
		
		
		for(int row=0;row<list.size();row++ ) {
			//System.out.print(term);
			SelectCourse sc = list.get(row);
			tableValue[row][0]=sc.getScterm();
			tableValue[row][1]=sc.getScCourse();
			tableValue[row][2]=sc.getScStudent();
			tableValue[row][3]=sc.getScClass();
			tableValue[row][4]=String.valueOf(sc.getScGrade());
			
		}
		DefaultTableModel tableModel = new DefaultTableModel(tableValue, colName);
		grade.setModel(tableModel);
		
	}





	/** 
	 * @Title: showTermListToTable 
	 * @Description: TODO  void 
	 * @author: --
	 * @date 2019年12月19日下午3:44:01 
	 */
	private void showTermListToTable() {
		// TODO Auto-generated method stub
		//final DefaultListModel<String> term = new DefaultListModel<String>();
		jcb=new JComboBox();
		try {
			ArrayList<String> list = DataHandling.getTermList(user.getUserId());
			for(int i=0;i<list.size();i++) {
				//System.out.print(list.get(i));
				//term.addElement(list.get(i));
				jcb.addItem(list.get(i));
			}
			//jcb=new JComboBox();
			
			//jList=new JList(term);
			//jList.setVisibleRowCount(1);
			//jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		
	}





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
				
				try {
					new CheckGrade(new User("030001",
							"tom", "1234", "Student")).setVisible(true);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	
	
	

}
