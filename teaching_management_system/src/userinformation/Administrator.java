/**   
 * Copyright © 2019 --. All rights reserved.
 * 
 * @Package: userinformation 
 * @author: --   
 * @date: 2019年12月24日 下午11:23:50 
 */
package userinformation;

/**
 * @author yjw24
 *
 */
public class Administrator {
	private String adm_idString;	//管理员工号
	private String adm_nameString;	//管理员姓名
	private String idString;	//身份证号
	public Administrator(String adm_idString,String adm_nameString,String idString) {
		super();
		this.adm_idString = adm_idString;
		this.adm_nameString = adm_nameString;
		this.idString = idString;
	}
}
