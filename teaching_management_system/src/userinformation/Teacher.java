/**   
 * Copyright © 2019 --. All rights reserved.
 * 
 * @Package: userinformation 
 * @author: --   
 * @date: 2019年12月24日 下午11:25:12 
 */
package userinformation;

/**
 * @author yjw24
 *
 */
public class Teacher {
	private String teac_id; //教师工号
	private String teac_college; //教师学院
	private String teac_name; //教师姓名
	private String ID;//身份证号
	public Teacher(String teac_id,String teac_college,String teac_name,String ID) {
		super();
		this.teac_id = teac_id;
		this.teac_college = teac_college;
		this.teac_name = teac_name;
		this.ID = ID;
	}
	
	public void setBaseinfo(String teac_id,String teac_college,String teac_name,String ID) {
		this.teac_id = teac_id;
		this.teac_college = teac_college;
		this.teac_name = teac_name;
		this.ID = ID;
	}
	public String getName() {
		return this.teac_name;
	}
	public void SetTeacherId(String teacId) {
		this.teac_id = teacId;
	}
	
	public String toString() {
		return "('" + this.teac_id + "','" + this.teac_name + "','" + this.teac_college + "')";
	}
	public Teacher() {
	}
}
