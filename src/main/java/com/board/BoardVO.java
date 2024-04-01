package com.board;

import java.util.Date;

import lombok.Data;


@Data
public class BoardVO {
	
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	
	private int views;
	private int recommend;
	
	private String searchKey;
	
	private String action;
	
	public boolean isEmptySearchKey() {
		return searchKey == null || searchKey.length() == 0; 
	}


}
