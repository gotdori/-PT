package com.ds.project01.domain;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ds.project01.dto.HobbyDataDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@IdClass(HobbyDataPK.class)//복합키를 사용 하면 아이디 클래스를 지정해줘야함
@Table(name="hobbydata_tb")
public class HobbyDataEntity {
	

	@Id
	@ManyToOne //유저엔티티에 블로그 있으니 확인
	@JoinColumn(name = "user_id")
	private UserEntity userEntity; //복합키(composit key)을 할 때 조인이 아닌 객체를 가져옴, 자세한 내용은 유저엔티티에 블로그 있음
	
	
	@Id
	@ManyToOne
	@JoinColumn(name = "hobby_cd")
	private HobbyEntity hobbyEntity;
	
	
	public static HobbyDataEntity toHobbyDataEntity(HobbyDataDto dto) {
		HobbyDataEntity hdEntity = new HobbyDataEntity();
		UserEntity userEntity = new UserEntity();
		HobbyEntity hobbyEntity = new HobbyEntity();
		
		userEntity.setUserId(dto.getUserId());
		hobbyEntity.setHobbyCd(dto.getHobbyCd());
		hdEntity.setUserEntity(userEntity);
		hdEntity.setHobbyEntity(hobbyEntity);
		
		System.out.println(hdEntity);
		
		return hdEntity;
	}
}
