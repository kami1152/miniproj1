package com.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.BoardController;
import com.board.BoardVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.UserVO;

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	BoardController boardController = new BoardController();
	//BoardController BoardController = new BoardController();
	public BoardServlet() {
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

		BoardVO BoardVO = null;
	
		ObjectMapper objectMapper = new ObjectMapper();
		if(contentType == null || contentType.startsWith("application/x-www-form-urlencoded")) {
			BoardVO  = objectMapper.convertValue(convertMap(request.getParameterMap()), BoardVO.class);
		}else if (contentType.startsWith("application/json")){
			BoardVO  = objectMapper.readValue(request.getInputStream(), BoardVO.class);
		}
	
		
		
		
		System.out.println("BoardVO " + BoardVO);
		String action = BoardVO.getAction();
		System.out.println(action);
		Object result = switch (action) {
		case "list" -> boardController.list(request, BoardVO);
		case "view" -> boardController.view(request, BoardVO);
		case "delete" -> boardController.delete(request, BoardVO);
		case "updateForm" -> boardController.updateForm(request, BoardVO);
		case "update" -> boardController.update(request, BoardVO);
		case "insertForm" -> boardController.insertForm(request);
		case "insert" -> boardController.insert(request, BoardVO);
		//case "searchKey" ->boardController.search(request);
		//case "loginForm" -> BoardController.loginForm(request);
		//case "login" -> BoardController.login(request, BoardVO,response);
		//case "logout" -> BoardController.logout(request);
		//case "mypage" -> BoardController.mypage(request, BoardVO);
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
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/board/" + url + ".jsp");
				rd.forward(request, response);
			}
		}

		
	}
}