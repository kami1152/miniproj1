package com.user;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.UserVO;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	UserController userController = new UserController();
	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}

	private Map<String, Object> convertMap(Map<String, String[]> map) {
		Map<String, Object> result = new HashMap<>();

		for (var entry : map.entrySet()) {
			if (entry.getValue().length == 1) {
				// 문자열 1건
				result.put(entry.getKey(), entry.getValue()[0]);
			} else {
				// 문자열 배열을 추가한다
				result.put(entry.getKey(), entry.getValue());
			}
		}

		return result;
	}

	// 공통 처리 함수
	private void doService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 설정
		request.setCharacterEncoding("utf-8");
		String contentType = request.getContentType();

		ObjectMapper objectMapper = new ObjectMapper();
		UserVO userVO = null;
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if(contentType == null || contentType.startsWith("application/x-www-form-urlencoded")) {
			userVO = objectMapper.convertValue(convertMap(request.getParameterMap()), UserVO.class);
		}else if (contentType.startsWith("application/json")){
			userVO = objectMapper.readValue(request.getInputStream(), UserVO.class);
		}
	
		System.out.println("userVO " + userVO);
		String action = userVO.getAction();
		System.out.println(action);
		Object result = switch (action) {
		case "list" -> userController.list(request, userVO);
		case "view" -> userController.view(request, userVO);
		case "delete" -> userController.delete(request, userVO);
		case "updateForm" -> userController.updateForm(request, userVO);
		case "update" -> userController.update(request, userVO);
		case "insertForm" -> userController.insertForm(request, userVO);
		case "insert" -> userController.insert(request, userVO);
		case "loginForm" -> userController.loginForm(request);
		case "login" -> userController.login(request, userVO,response);
		case "logout" -> userController.logout(request,response);
		case "mypage" -> userController.mypage(request, userVO);
		case "mypagehome" -> userController.mypagehome(request, userVO);
		default -> "";
		};
		if (result instanceof Map map) {
			// json 문자열을 리턴
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(objectMapper.writeValueAsString(map));
		} else if (result instanceof String url) {
			if (url.startsWith("redirect:")) {
				// 리다이렉트
				response.sendRedirect(url.substring("redirect:".length()));
			} else {
				// 3. jsp 포워딩
				// 포워딩
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/" + url + ".jsp");
				rd.forward(request, response);
			}
		}

		
	}
}