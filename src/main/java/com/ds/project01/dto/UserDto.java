package com.ds.project01.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
	
	private String userId;
	private String userNm;
	
	private String userEmlAddr;
	
	private String deptNo;
	
	private String userTelno;
	
	private String userAddr;
	
	private String userAprvYn="n";
	
}
