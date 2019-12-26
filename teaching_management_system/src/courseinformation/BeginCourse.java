/**   
 * Copyright © 2019 --. All rights reserved.
 * 
 * @Package: course_information 
 * @author: --   
 * @date: 2019年11月21日 下午6:00:55 
 */
package courseinformation;

/**
 * @ClassName: BeginCourse
 * @Description: 开课信息类
 * @author: --
 * @date: 2019年11月21日 下午6:00:55
 */
public class BeginCourse {

	private String bcId;           //开课号
	private String bcCourse;       //开课课程号
	private String bcTeacher;      //授课老师工号
	private String bcClassroom;    //开课教室
	private String bcClass;
	//private String bcCapacity;     //开课容量
	private int bcCapacity;     //开课容量
	private int bcBeSelected;    //已选数
	private String bcBegintime;    //开始时间
	private String bcEndtime;      //结束时间
	private String bctime;         //上课时间
		
	/** 
	 * @Title:BeginCourse
	 * @Description:TODO 
	 * @param bcId
	 * @param bcCourse
	 * @param bcTeacher
	 * @param bcClassroom
	 * @param bcCapacity
	 * @param bcBeSelected
	 * @param bcBegintime
	 * @param bcEndtime
	 * @param bctime 
	 */  
	public BeginCourse(String bcId, String bcCourse, String bcTeacher, String bcClassroom, int bcCapacity,
			int bcBeSelected, String bcBegintime, String bcEndtime, String bctime) {
		super();
		this.bcId = bcId;
		this.bcCourse = bcCourse;
		this.bcTeacher = bcTeacher;
		this.bcClassroom = bcClassroom;
		this.bcCapacity = bcCapacity;
		this.bcBeSelected = bcBeSelected;
		this.bcBegintime = bcBegintime;
		this.bcEndtime = bcEndtime;
		this.bctime = bctime;
	}


	public String getBcId() {
		return bcId;
	}

	public void setBcId(String bcId) {
		this.bcId = bcId;
		this.bcClass = bcId;
	}

	public int getBcBeSelected() {
		return bcBeSelected;
	}

	public void setBcBeSelected(int bcBeSelected) {
		this.bcBeSelected = bcBeSelected;
	}

	public String getBcCourse() {
		return bcCourse;
	}

	public void setBcCourse(String bcCourse) {
		this.bcCourse = bcCourse;
	}

	public String getBcTeacher() {
		return bcTeacher;
	}

	public void setBcTeacher(String bcTeacher) {
		this.bcTeacher = bcTeacher;
	}

	public String getBcClassroom() {
		return bcClassroom;
	}

	public void setBcClassroom(String bcClassroom) {
		this.bcClassroom = bcClassroom;
	}

	public void setBcCapacity(int bcCapacity) {
		this.bcCapacity = bcCapacity;
	}

	public String getBcBegintime() {
		return bcBegintime;
	}

	public void setBcBegintime(String bcBegintime) {
		this.bcBegintime = bcBegintime;
	}

	public int getBcCapacity() {
		return bcCapacity;
	}

	public String getBcEndtime() {
		return bcEndtime;
	}

	public void setBcEndtime(String bcEndtime) {
		this.bcEndtime = bcEndtime;
	}

	public String getBctime() {
		return bctime;
	}
	
	public void BeSelect(boolean y) {
		if(y) {
			this.bcBeSelected++;
			return;
		}
		this.bcBeSelected--;
	}
	
	public void SetBeSelected(int i) {
		this.bcBeSelected=i;
	}
	
	public void setBctime(String bctime) {
		this.bctime = bctime;
	}
	
	public String toString() {
		return "('" 
	+ this.bcId + "','" 
	+ this.bcCourse + "','"
	+ this.bcTeacher + "','"
	+ this.bcClass + "','"
	+ this.bcClassroom + "','"
	+ this.bcCapacity + "','"
	+ this.bcBeSelected + "','"
	+ this.bcBegintime + "','"
	+ this.bcEndtime + "','"
	+ this.bctime + "')";
	}





	
	public int getBeSelect() {
		return this.bcBeSelected;
	}
	

	//初始化并设置开设课程的信息
		public BeginCourse(String bcCourse, String bcTeacher, 
				String bcBegintime, String bcEndtime, String bctime) {
			super();
			this.bcCourse = bcCourse;
			this.bcTeacher = bcTeacher;
			this.bcClassroom = "0001";
			this.bcCapacity = 50;
			this.bcBeSelected=0;
			this.bcBegintime = bcBegintime;
			this.bcEndtime = bcEndtime;
			this.bctime = bctime;
		}





		/**
		 * @return the bcClass
		 */
		public String getBcClass() {
			return bcId;
		}





		/**
		 * @param bcClass the bcClass to set
		 */
		public void setBcClass(String bcClass) {
			this.bcClass = bcClass;
		}





		/** 
		 * @Title:BeginCourse
		 * @Description:TODO 
		 * @param bcId
		 * @param bcCourse
		 * @param bcTeacher
		 * @param bcClassroom
		 * @param bcClass
		 * @param bcCapacity
		 * @param bcBeSelected
		 * @param bcBegintime
		 * @param bcEndtime
		 * @param bctime 
		 */  
		public BeginCourse(String bcId, String bcCourse, String bcTeacher, String bcClassroom, String bcClass,
				int bcCapacity, int bcBeSelected, String bcBegintime, String bcEndtime, String bctime) {
			super();
			this.bcId = bcId;
			this.bcCourse = bcCourse;
			this.bcTeacher = bcTeacher;
			this.bcClassroom = bcClassroom;
			this.bcClass = bcClass;
			this.bcCapacity = bcCapacity;
			this.bcBeSelected = bcBeSelected;
			this.bcBegintime = bcBegintime;
			this.bcEndtime = bcEndtime;
			this.bctime = bctime;
		}
		
		
		
		
		public BeginCourse(String bcId, String bcCourse, String bcTeacher, String bcClass, String bcClassroom, int bcCapacity,
				String bcBegintime, String bcEndtime, String bctime) {
			super();
			this.bcId = bcId;
			this.bcCourse = bcCourse;
			this.bcClass=bcClass;
			this.bcTeacher = bcTeacher;
			this.bcClassroom = bcClassroom;
			this.bcCapacity = bcCapacity;
			this.bcBeSelected=0;
			this.bcBegintime = bcBegintime;
			this.bcEndtime = bcEndtime;
			this.bctime = bctime;
		}

	

}
