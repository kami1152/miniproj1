package com.user;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class UserController {
	UserService userService = new UserService();

	public Object list(HttpServletRequest request, UserVO userVO) {
		System.out.println("list loading...");
		List<UserVO> list = userService.list();
		request.setAttribute("list", list);
		return "list";
	}

	public Object view(HttpServletRequest request, UserVO user) {
		System.out.println("detail: " + user);
		request.setAttribute("user", userService.view(user));
		return "view";
	}

	public Object delete(HttpServletRequest request, UserVO user) {
		System.out.println("delete start " + user);
		int res = userService.delete(user);

		Map<String, Object> map = new HashMap<String, Object>();
		if (res == 1) {
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "failed delete");
		}
		return map;
	}

	public Object updateForm(HttpServletRequest request, UserVO user) {
		request.setAttribute("user", userService.view(user));
		return "updateForm";
	}

	public Object update(HttpServletRequest request, UserVO user) {

		int res = userService.update(user);
		Map<String, Object> map = new HashMap<String, Object>();
		if (res == 1) {
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "update failed");

		}
		return map;
	}

	public Object insertForm(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "insertForm";
	}

	public Object insert(HttpServletRequest request, UserVO user) {
		// TODO Auto-generated method stub
		System.out.println("insert start");
		int res = userService.insert(user);
		Map<String, Object> map = new HashMap<>();
		if(res == 1) {
			map.put("status", 0);
		} else if(res == -1) {
			map.put("status", -98);
			map.put("statusMessage", "이미 존재하는 아이디 입니다.");
		}
		else {
			map.put("status", -99);
			map.put("statusMessage", "회원 가입이 실패하였습니다");
		}
		return map;
	}

}
