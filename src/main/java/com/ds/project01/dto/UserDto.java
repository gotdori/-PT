package com.ds.project01.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ds.project01.constant.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
	
	@NotBlank(message = "ID은 필수 입력 값입니다.")
	private String userId;
	
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Length(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하로 입력해주세요. ")
	private String userPw;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String userNm;
	
	@NotEmpty(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String userEmlAddr;
	
	private String deptNo;
	
	@NotEmpty(message = "전화번호는 필수 입력 값입니다.")
	private String userTelno;
	
	private String userAddr;
	
	private String userAprvYn="n";
	
	private Role role;
	
}
