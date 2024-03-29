package com.user;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.UserVO;

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
		List<String> list = userService.insertForm();
		request.setAttribute("hobbylist", list);
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

	public Object insertForm(HttpServletRequest request, UserVO user) {
		List<String> list = userService.insertForm();
		request.setAttribute("hobbylist", list);
		return "insertForm";
	}

	public Object insert(HttpServletRequest request, UserVO user) {
		// TODO Auto-generated method stub
		System.out.println("insert start");
		System.out.println("insert : " + user);
		int res = userService.insert(user);
		request.setAttribute("msg", String.valueOf(res));
		if(res == 1) {
			return "redirect:board.do?action=list";	
		}else {
			return "lnsertForm";
		}
		
	}

	public Object loginForm(HttpServletRequest request) {
		return "loginForm";
	}

	public Object login(HttpServletRequest request, UserVO userVO, HttpServletResponse response) {
		int res = 0;
		System.out.println("login start");
		Map<String, Object> map = new HashMap<>();
		UserVO loginVO = userService.login(userVO);
		System.out.println(loginVO);
		if (loginVO == null) {
			map.put("status", -99);
			map.put("statusMessage", "로그인에 실패하였습니다");
		} else {
			map.put("status", 0);
			HttpSession session = request.getSession();
			session.setAttribute("loginVO", loginVO);
			session.setMaxInactiveInterval(30 * 60 * 1000);
			
			// uuid 구현
			if (userVO.getAutologin().equals("Y")) {
				String uuid = UUID.randomUUID().toString();
				userVO.setUseruuid(uuid);
				userService.updateUUID(userVO);
				System.out.println("good autologin set:"+userVO);
				Cookie uuidCookie = new Cookie("uuidCookie", uuid);
				uuidCookie.setMaxAge(24 * 60 * 60); // 24시간
				uuidCookie.setPath("/");

				response.addCookie(uuidCookie);
			}
			return "redirect:board.do?action=list";
		}

		return "redirect:user.do?action=loginForm&err=invalidUserId";
	}

	public Object logout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		UserVO loginVO = (UserVO) session.getAttribute("loginVO");
		System.out.println("logout: "+loginVO);
		loginVO.setUseruuid("");
		Cookie cookie = new Cookie("uuidCookie", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		userService.updateUUID(loginVO);
		session.removeAttribute("loginVO");
		session.invalidate();
		map.put("status", 0);
		return map;
	}

	public Object mypage(HttpServletRequest request, UserVO userVO) {
		// TODO Auto-generated method stub
		return "mypage";
	}

	public Object mypagehome(HttpServletRequest request, UserVO userVO) {
		
		request.setAttribute("user", userService.view(userVO));
		return "mypagehome";
	}
	public Object existUserId(HttpServletRequest request, UserVO userVO) throws ServletException, IOException {
		// 1. 처리
		System.out.println("existUserId userid->" + userVO.getUserid());
		boolean existUser = userService.existUserId(userVO);
		Map<String, Object> map = new HashMap<>();
		System.out.println(existUser);

		if (existUser) { // 사용가능한 아이디
			map.put("existUser", false);
		} else { // 사용 불가능 아아디
			map.put("existUser", true);
		}
		return map;
	}


}
