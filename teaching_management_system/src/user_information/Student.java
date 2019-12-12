package user_information;

public class Student {
	private String stu_idString;
	private String stu_nameString;
	private String stu_collegeString;
	private String stu_majorString;
	private String stu_classString;
	private double stu_creditString;
	private String ID;
	
	public Student(String stu_idString,String stu_nameString,String stu_collegeString,String stu_majorString,
			String stu_classsString, double stu_criditString, String ID) {
		this.stu_idString = stu_idString;
		this.stu_nameString = stu_nameString;
		this.stu_collegeString = stu_collegeString;
		this.stu_classString = stu_classsString;
		this.stu_creditString = stu_criditString;
		this.ID = ID;
	}
}
