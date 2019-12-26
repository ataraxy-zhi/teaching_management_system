/**   
 * Copyright © 2019 --. All rights reserved.
 * 
 * @Package: functionframe 
 * @author: --   
 * @date: 2019年12月24日 下午11:29:13 
 */
package functionframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import courseinformation.BeginCourse;
import dataprocessing.DataHandling;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class AddBeginingCourse extends JFrame {

	private JPanel contentPane;
	public char[] time = new char[30];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBeginingCourse frame = new AddBeginingCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public AddBeginingCourse() throws ClassNotFoundException, SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//让窗口居中
		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenHeight = dimension.height;
		int screenWidth = dimension.width;
		int fram_Height = this.getHeight();
		int fram_Width = this.getWidth();
		this.setLocation((screenWidth-fram_Width)/2,(screenHeight-fram_Height)/2 );
		
		//选择课程时间的面板 (网格布局)
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setBounds(20, 48, 511, 334);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(6, 7, 0, 0));
		JLabel lblNewLabel = new JLabel("周一");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("周二");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("周三");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("周四");
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("周五");
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("周六");
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("周末");
		panel.add(lblNewLabel_6);
		JCheckBox [][] checkboxs = new JCheckBox[7][5];
		for(int j = 0;j<5;j++)
			for(int i = 0;i<7;i++) {
				switch(j) {
				case 0:
					checkboxs[i][j] = new JCheckBox("第一节");
					panel.add(checkboxs[i][j]);
					break;
				case 1:
					checkboxs[i][j] = new JCheckBox("第二节");
					panel.add(checkboxs[i][j]);
					break;
				case 2:
					checkboxs[i][j] = new JCheckBox("第三节");
					panel.add(checkboxs[i][j]);
					break;
				case 3:
					checkboxs[i][j] = new JCheckBox("第四节");
					panel.add(checkboxs[i][j]);
					break;
				case 4:
					checkboxs[i][j] = new JCheckBox("第五节");
					panel.add(checkboxs[i][j]);
					break;
				}
				checkboxs[i][j].setFont(new Font("SimSun", Font.PLAIN, 12));
			}

		
		
		JButton confirmBtn = new JButton("确认添加");
		
		confirmBtn.setBounds(560, 355, 113, 27);
		contentPane.add(confirmBtn);
		
		JComboBox cbbCourseId = new JComboBox();
		cbbCourseId.setBounds(547, 48, 140, 32);
		contentPane.add(cbbCourseId);
		cbbCourseId.setModel(new DefaultComboBoxModel(DataHandling.getCourseId()));
		
		JComboBox cbbTeacherId = new JComboBox();
		cbbTeacherId.setBounds(547, 106, 140, 32);
		contentPane.add(cbbTeacherId);
		cbbTeacherId.setModel(new DefaultComboBoxModel(DataHandling.getTeahersId()));
		
		JLabel lblNewLabel_7 = new JLabel("课程号");
		lblNewLabel_7.setBounds(571, 30, 72, 18);
		contentPane.add(lblNewLabel_7);
		
		
		JLabel teacherLabel = new JLabel("教师工号");
		teacherLabel.setBounds(571, 86, 72, 18);
		contentPane.add(teacherLabel);
		
		JLabel lblNewLabel_9 = new JLabel("开始周");
		lblNewLabel_9.setBounds(571, 242, 72, 18);
		contentPane.add(lblNewLabel_9);
		
		JComboBox cbbBeginWeek = new JComboBox();
		cbbBeginWeek.setModel(new DefaultComboBoxModel(new String[] {"", "第一周", "第二周", "第三周", "第四周", "第五周", "第六周", "第七周", "第八周", "第九周"}));
		cbbBeginWeek.setBounds(546, 260, 140, 27);
		contentPane.add(cbbBeginWeek);
		
		JLabel EndWeek = new JLabel("结束周");
		EndWeek.setBounds(571, 290, 72, 18);
		contentPane.add(EndWeek);
		
		JComboBox cbbEndWeek = new JComboBox();
		cbbEndWeek.setModel(new DefaultComboBoxModel(new String[] {"", "第一周", "第二周", "第三周", "第四周", "第五周", "第六周", "第七周", "第八周", "第九周", "第十周"}));
		cbbEndWeek.setBounds(546, 313, 140, 27);
		contentPane.add(cbbEndWeek);
		
		JLabel lblNewLabel_8 = new JLabel("请选择上课时间");
		lblNewLabel_8.setBounds(21, 24, 157, 18);
		contentPane.add(lblNewLabel_8);
		
		JComboBox cbbclass = new JComboBox();
		cbbclass.setModel(new DefaultComboBoxModel(new String[] {"5-101", "5-102", "5-103", "待定"}));
		cbbclass.setBounds(546, 200, 141, 27);
		contentPane.add(cbbclass);
		
		JLabel label = new JLabel("教室");
		label.setBounds(571, 176, 81, 21);
		contentPane.add(label);

		
		confirmBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BeginCourse bCourse = new BeginCourse(
						cbbCourseId.getSelectedItem().toString(), 
						cbbTeacherId.getSelectedItem().toString(),
						cbbBeginWeek.getSelectedItem().toString(),
						cbbEndWeek.getSelectedItem().toString(),
						getTimeString(checkboxs));
				try {
					dataprocessing.DataHandling.addBeginCourse(bCourse);
					JOptionPane.showMessageDialog(null, "添加成功", 
							"提示", JOptionPane.WARNING_MESSAGE);
					dispose();//推出窗口
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(null, "未能添加,请检查输入的数据是否正确", 
							"提示", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
				
			}
		});
	}
	
public  static String getTimeString(JCheckBox[][] checkboxs) {
	String timeString = " ";
	
	String[] timeStringParts = new String[7];
	for(int i = 0;i<7;i++) timeStringParts[i] = " ";
	
	for(int i = 0; i<7;i++) {
		for(int j = 0;j<5;j++) {
			if(checkboxs[i][j].isSelected()) {
				switch(j) {
					case 0:
						
						timeStringParts[i] = timeStringParts[i].concat("第一节 ");
						break;
					case 1:
						timeStringParts[i] = timeStringParts[i].concat("第二节 ");
						break;
					case 2:
						timeStringParts[i] = timeStringParts[i].concat("第三节 ");
						break;
					case 3:
						timeStringParts[i] = timeStringParts[i].concat("第四节 ");
						break;
					case 4:
						timeStringParts[i] = timeStringParts[i].concat("第五节 ");
						break;
				}
				
			}
				
		}
	}
	
	
	for(int i = 0;i<7;i++) {
		if(!timeStringParts[i].equals(" ")) {
			switch (i) {
			case 0:
				timeString = timeString.concat("|周一: ");
				timeString = timeString.concat(timeStringParts[i]);
				break;
			case 1:
				timeString = timeString.concat("|周二: ");
				timeString = timeString.concat(timeStringParts[i]);
				break;
			case 2:
				timeString = timeString.concat("|周三:　");
				timeString = timeString.concat(timeStringParts[i]);
				break;
			case 3:
				timeString = timeString.concat("|周四：");
				timeString = timeString.concat(timeStringParts[i]);
				break;
			case 4:
				timeString = timeString.concat("|周五：");
				timeString = timeString.concat(timeStringParts[i]);
				break;
			case 5:
				timeString = timeString.concat("|周六：");
				timeString = timeString.concat(timeStringParts[i]);
				break;
			case 6:
				timeString = timeString.concat("|周日：");
				timeString = timeString.concat(timeStringParts[i]);
				break;
			default:
				break;
			}
		}
	}	
	return timeString;
}
}

