package com.ds.project01.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HobbyDataPK implements Serializable{ //복합키(composit key)를 쓸 때 만들어줘야 하는 클래스
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // 경고 뜨길래 누르니까 추가 됨

	private String userEntity;
	
	private String hobbyEntity;
	

}