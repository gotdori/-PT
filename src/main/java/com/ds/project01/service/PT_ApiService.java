package com.ds.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ds.project01.domain.DeptEntity;
import com.ds.project01.domain.HobbyDataEntity;
import com.ds.project01.domain.HobbyEntity;
import com.ds.project01.domain.UserEntity;
import com.ds.project01.dto.HobbyDataDto;
import com.ds.project01.dto.UserDto;



@Service
public class PT_ApiService implements UserDetailsService {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${btUrl}")
	String btUrl;
	
	
	public List<UserEntity> adminList(String searchKeyword){
		String url;
		if(searchKeyword != null)
		url = btUrl + "/bt/list?searchKeyword="+searchKeyword;
		else
		url = btUrl + "/bt/list";
		ResponseEntity<List<UserEntity>> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<UserEntity>>() {});
		
		return response.getBody();
	}
	
	public void insert(UserEntity entity) {
		String url = btUrl + "/bt/userSave";
        restTemplate.postForObject(url, entity, UserEntity.class);
	}
	
	public void hobbyDataInsert(HobbyDataDto hdDto) {
		String url = btUrl + "/bt/hobbyDataSave";
		restTemplate.postForObject(url, hdDto, HobbyDataDto.class);
	}
	public void delete(UserDto dto) {
		String url = btUrl + "/bt/delete";
		restTemplate.postForObject(url, dto, UserEntity.class);
	}
	
	public UserEntity view(String userId) {
		String url = btUrl + "/bt/view?userId="+userId;
		ResponseEntity<UserEntity> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<UserEntity>() {});
		
		return response.getBody();
	}
	
	public Boolean idCheck(String userId) {
		String url = btUrl + "/bt/idCheck?userId="+userId;
		ResponseEntity<Boolean> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<Boolean>() {});
		return response.getBody();
	}
	
	public List<DeptEntity> deptList(){
		String url = btUrl + "/bt/deptList";
		ResponseEntity<List<DeptEntity>> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<DeptEntity>>() {});
		
		return response.getBody();
	}
	
	public List<HobbyEntity> hobbyList(){
		String url = btUrl + "/bt/hobbyList";
		ResponseEntity<List<HobbyEntity>> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<HobbyEntity>>() {});
		
		return response.getBody();
	}
	
	public List<HobbyDataEntity> HobbyDataView(String userId) {
		String url = btUrl + "/bt/hobbyDataList?userId="+userId;
		ResponseEntity<List<HobbyDataEntity>> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<HobbyDataEntity>>() {});
		
		return response.getBody();
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserEntity userEntity = view(userId);
		
		if (userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}
		
//		String rawPassword = "12345678"; // 입력된 평문 비밀번호
//
//	    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	    boolean matches = passwordEncoder.matches(rawPassword, userEntity.getUserPw());
//
//		if (matches) {
//			System.err.println("똑같다");
//		}else {
//			System.err.println("다르다");
//		}
		return User.builder()
				.username(userEntity.getUserId())
				.password(userEntity.getUserPw())
				.roles(userEntity.getRole().toString())
				.build();
	}

}
