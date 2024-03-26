package com.user;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO {
	private String userid =null;
	private String userpassword;
	private String username ;
	
	private String useremail;
	private int userage;
	private Date userdate;
	
	
	private String userhobby = null;
	

	private String useruuid = null;
	
	
	private String action;
}
