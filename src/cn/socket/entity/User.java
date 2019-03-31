package cn.socket.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable{
	
	private String loginName;//”√ªß√˚
	private String pwd;//√‹¬Î
	
	public User(){
		
	}

	public User(String loginName, String pwd) {
		super();
		this.loginName = loginName;
		this.pwd = pwd;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public static void main(String[] args) {
		String ss="0000000.00";
		BigDecimal bd=new BigDecimal(ss);
		System.out.println(bd);
	}

}
