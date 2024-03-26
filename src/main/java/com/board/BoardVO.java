package com.board;

import java.util.Date;

import lombok.Data;


@Data
public class BoardVO {
	
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter = "임시 세션 사용자";
	private Date bdate;
	
	private int views;
	private int recommend;
	
	private String action;

}
