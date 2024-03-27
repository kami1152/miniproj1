package com.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.*;

import lombok.Data;

@Data
public class UserVO {
	private String userid =null;
	private String userpassword;
	private String username ;
	
	private String useremail;
	private int userage;
	private Date userdate;
	
	private String userhobby;
	
	private Queue<String> userhobbies;
	
	private String autologin = "N";
	private String useruuid = null;
	
	
	private String action;
}
