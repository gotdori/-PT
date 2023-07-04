package com.ds.project01.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
	
	@Column(name = "user_pw", length = 300)
	private String userPw;
	
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
	
    @Enumerated(EnumType.STRING) //enum 엔티티로 적용. 순서가 바뀌지 않게 String으로 저장
    private Role role; // 관리자 여부
	
	
	public static UserEntity toUserEntity(UserDto dto) {
		UserEntity entity = new UserEntity();
		
		DeptEntity deptEntity = new DeptEntity();
		deptEntity.setDeptNo(dto.getDeptNo());
		entity.setDeptEntity(deptEntity);
		
		entity.setUserId(dto.getUserId());
		entity.setUserNm(dto.getUserNm());
//		String UserPw = passwordEncoder.encode(dto.getUserPw());
//		entity.setUserPw(UserPw);
		entity.setUserAddr(dto.getUserAddr());
		entity.setUserEmlAddr(dto.getUserEmlAddr());
		entity.setUserTelno(dto.getUserTelno());
		entity.setUserAprvYn(dto.getUserAprvYn());
		if (dto.getRole()==null) {
			entity.setRole(null);
		}
		else if(dto.getRole()!=Role.USER) {
			entity.setRole(Role.ADMIN);
		} else {
			entity.setRole(Role.USER);
		}
		
		return entity;
	}
	
}
