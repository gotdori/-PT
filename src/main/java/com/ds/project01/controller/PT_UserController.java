package com.ds.project01.controller;

import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.project01.dto.HobbyDataDto;
import com.ds.project01.dto.UserDto;
import com.ds.project01.service.PT_UserService;

@Controller
public class PT_UserController {
	
	@Autowired
	PT_UserService service;

	@GetMapping("/user/write")
	public String user_write(Model model) {
		
		model.addAttribute("deptList", service.deptList());
		model.addAttribute("hobbyList", service.hobbyList());
		return "user/write";
	}


	@PostMapping("/user/save")
	public String pt_save(UserDto dto, HobbyDataDto hdDto) {
		service.insert(dto);
		service.hobbyDataInsert(hdDto);
		
		return "redirect:/user/write";
	}

	@GetMapping("/admin/list")
	public String admin_list(Model model, String searchKeyword) {
		model.addAttribute("adminList", service.adminList(searchKeyword));
		model.addAttribute("deptList", service.deptList());
		model.addAttribute("hobbyList", service.hobbyList());
		
		return "admin/list";
	}
	
	
	@ResponseBody
	@GetMapping("/admin/view")
	HashMap<String, Object> userView(UserDto userDto){ //ajax로 보낸  userID 정보를 userDto에 담아서 받아옴 
		HashMap<String, Object> map = new HashMap<>(); //attribute로 html 태그 안으로 데이터를 넘길 때는 Model, script 태그 안에는 map
		
		String userId =userDto.getUserId();
		for (int i = 0; i < service.HobbyDataView(userId).size(); i++) {
			map.put("userHobbyChoice"+i, service.HobbyDataView(userId).get(i).getHobbyEntity().getHobbyCd()); //각각 여러 취미데이터를 다른 아이디로 저장
		}
		map.put("deptList", service.deptList()); //받아온 부서 데이터 저장 후 script에서 불러낼것
		map.put("getUerId",service.view(userId).getUserId());
		map.put("getUserNm",service.view(userId).getUserNm());
		map.put("getUserEmlAddr",service.view(userId).getUserEmlAddr());
		map.put("getUserTelno",service.view(userId).getUserTelno());
		map.put("getUserDeptNo",service.view(userId).getDeptEntity().getDeptNo());
		map.put("getUserAprvYn",service.view(userId).getUserAprvYn());
		
		return map;
	}
	
	@PostMapping("/admin/delete")
	public String user_delete(UserDto dto) {
		service.delete(dto);
		return "redirect:admin/list";
	}
	
}