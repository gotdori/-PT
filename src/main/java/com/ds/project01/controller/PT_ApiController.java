package com.ds.project01.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.project01.domain.UserEntity;
import com.ds.project01.dto.HobbyDataDto;
import com.ds.project01.dto.UserDto;
import com.ds.project01.service.PT_ApiService;

@Controller
public class PT_ApiController {
	
	@Autowired
	PT_ApiService service;
	
    @Autowired
    PasswordEncoder passwordEncoder;
	
	

	@GetMapping("/user")
	public String user_write(Model model) {
		
		model.addAttribute("deptList", service.deptList());
		model.addAttribute("hobbyList", service.hobbyList());
		model.addAttribute("userDto", new UserDto());
		return "user/write";
	}
	
	@ResponseBody
	@GetMapping("/user/idcheck")
	HashMap<String, Object> idCheck(UserDto userDto){
		HashMap<String, Object> map = new HashMap<>();
		boolean result = service.idCheck(userDto.getUserId());
		map.put("result",result);
		
		return map;
	}

	
	@PostMapping("/user")
	public String pt_save(@Valid UserDto userDto, BindingResult bindResult, HobbyDataDto hdDto, Model model) {
		if (bindResult.hasErrors()) {
			model.addAttribute("deptList", service.deptList());
			model.addAttribute("hobbyList", service.hobbyList());
			return "user/write";
            }
			UserEntity entity = UserEntity.toUserEntity(userDto, passwordEncoder);

			service.insert(entity);
			service.hobbyDataInsert(hdDto);
			return "redirect:/user";
			
	}
	
	
	@PutMapping("/user")
	public String pt_update(UserDto userDto, HobbyDataDto hdDto, Model model) {
			UserEntity entity = UserEntity.toUserEntity(userDto, passwordEncoder);
			service.insert(entity);
			service.hobbyDataInsert(hdDto);
			return "redirect:/user/success";
	}
	
	@GetMapping("/user/success")
	public String submitPage() {
		return "user/success";
	}

	
	@GetMapping("/admin")
	public String admin_list(Model model, String searchKeyword) {
		model.addAttribute("adminList", service.adminList(searchKeyword));
		model.addAttribute("deptList", service.deptList());
		model.addAttribute("hobbyList", service.hobbyList());
		model.addAttribute("userDto", new UserDto());
		
		return "admin/list";
	}
	
	
	@ResponseBody
	@GetMapping("/admin/view")
	HashMap<String, Object> userView(String userId){ //ajax로 보낸  userID 정보를 userDto에 담아서 받아옴 
		HashMap<String, Object> map = new HashMap<>(); //attribute로 html 태그 안으로 데이터를 넘길 때는 Model, script 태그 안에는 map
		for (int i = 0; i < service.HobbyDataView(userId).size(); i++) {
			map.put("userHobbyChoice"+i, service.HobbyDataView(userId).get(i).getHobbyEntity().getHobbyCd()); //각각 여러 취미데이터를 다른 아이디로 저장
		}
		UserEntity entity = new UserEntity();
		entity = service.view(userId);
		map.put("getUerId",entity.getUserId());
		map.put("getUserNm",entity.getUserNm());
		map.put("getUserEmlAddr",entity.getUserEmlAddr());
		map.put("getUserTelno",entity.getUserTelno());
		map.put("getUserDeptNo",entity.getDeptEntity().getDeptNo());
		map.put("getUserAprvYn",entity.getUserAprvYn());
		map.put("getRole", entity.getRole());
		
		return map;
	}
	
	
	@DeleteMapping("/admin")
	public String user_delete(UserDto dto) {
		service.delete(dto);
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/login")
	public String admin_login() {
		return "admin/login";
	}
	
	@GetMapping("/admin/login-error")
	public String admin_loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디와 비밀번호를 확인해주세요.");
		return "admin/login";
	}
}