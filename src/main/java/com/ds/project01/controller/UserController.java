package com.ds.project01.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.project01.dto.DeptDto;
import com.ds.project01.dto.HobbyDataDto;
import com.ds.project01.dto.HobbyDto;
import com.ds.project01.dto.UserDto;

@Controller
public class UserController {

	@GetMapping("/pt/write")
	public String pt_write() {
		
		return "redirect:http://localhost:8081/bt/write";
	}
	
	@GetMapping("/user/write")
	public String user_write(Model model, DeptDto deptList, HobbyDto hobbyList) {
		model.addAttribute("deptList",deptList);
		model.addAttribute("hobbyList",hobbyList);
		return "user/write";
	}
	
	
	@PostMapping("/pt/save")
	public String pt_save(UserDto dto, HobbyDataDto hdDto) {
		
		return "redirect:/user/write";
	}

	@GetMapping("/admin/list")
	public String admin_list(UserDto dto, Model model, String searchKeyword) {
		
		return "admin/list";
	}
	
	//검색한 리스트 화면을 고정한 상태로 view를 보이고 싶었음. 그래서 비동기인 ajax를 사용하기 위해 json형태로 데이터를 받으니 @ResponseBody
	@ResponseBody
	@GetMapping("/admin/view")
	HashMap<String, Object> userView(UserDto userDto, Model model){ //ajax로 보낸  userID 정보를 userDto에 담아서 받아옴 
		HashMap<String, Object> map = new HashMap<>(); //attribute로 html 태그 안으로 데이터를 넘길 때는 Model, script 태그 안에는 map
		
		return map;
	}
	
	@PostMapping("/admin/delete")
	public String user_delete(UserDto dto) {
		return "redirect:admin/list";
	}
	
}