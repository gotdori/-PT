package com.ds.project01.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ds.project01.dto.DeptDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="dept_tb")
public class DeptEntity {
	

	@Id
	@NotNull
	@Column(name = "dept_no", length = 20, unique = true)
	private String deptNo;
	
	@NotNull
	@Column(name = "dept_nm", length = 300, unique = true)
	private String deptNm;
	
	
	//entity는 DB테이블과 컬럼을 스프링화 한거
	//entity랑 dto는 거의 같지만 view단에서 받은 정보들은 dto에 담아서 entity로 변환 후에 DB에 넣어서 view랑 db랑 직접적인 연결을 막음   
	public static DeptEntity toDeptEntity(DeptDto dto) { 	//dto를 entity로 변환하는 메소드

		DeptEntity entity = new DeptEntity();
		entity.setDeptNm(dto.getDeptNm());
		entity.setDeptNo(dto.getDeptNo());
		return entity;
	}
}
