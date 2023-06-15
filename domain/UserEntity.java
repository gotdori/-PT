package com.ds.project01.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ds.project01.constant.Role;
import com.ds.project01.dto.UserDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="user_tb")
public class UserEntity { 
	

	@Id
	@Column(name = "user_id", length = 20,  unique = true)
	public String userId;
	
	@Column(name = "user_nm", length = 300)
	private String userNm;
	
	@Column(name = "user_eml_addr", length = 320)
	private String userEmlAddr;
	
	@Column(name = "user_telno", length = 13)
	private String userTelno;
	
	@Column(name = "user_addr", length = 600)
	private String userAddr;
	
	@Column(name = "user_aprv_yn", length = 1)
	private String userAprvYn;
	
	@ManyToOne //https://jeong-pro.tistory.com/231 단방향 N:1
	@JoinColumn(name = "dept_no")
	private DeptEntity deptEntity;
	
	
	public static UserEntity toUserEntity(UserDto dto, PasswordEncoder passwordEncoder) {
		UserEntity entity = new UserEntity();
		
		DeptEntity deptEntity = new DeptEntity();
		deptEntity.setDeptNo(dto.getDeptNo());
		entity.setDeptEntity(deptEntity);
		
		entity.setUserId(dto.getUserId());
		entity.setUserNm(dto.getUserNm());
		entity.setUserAddr(dto.getUserAddr());
		entity.setUserEmlAddr(dto.getUserEmlAddr());
		entity.setUserTelno(dto.getUserTelno());
		entity.setUserAprvYn(dto.getUserAprvYn());
		entity.setRole(Role.USER);
		return entity;
	}
	
}