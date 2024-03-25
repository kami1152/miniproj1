package com.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.BoardController;
import com.board.BoardVO;

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
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

		BoardVO BoardVO = new BoardVO();
	
		System.out.println("BoardVO " + BoardVO);
		String action = BoardVO.getAction();
		System.out.println(action);
		Object result = switch (action) {
		//case "list" -> BoardController.list(request, BoardVO);
		//case "view" -> BoardController.view(request, BoardVO);
		//case "delete" -> BoardController.delete(request, BoardVO);
		//case "updateForm" -> BoardController.updateForm(request, BoardVO);
		//case "update" -> BoardController.update(request, BoardVO);
		//case "insertForm" -> BoardController.insertForm(request);
		//case "insert" -> BoardController.insert(request, BoardVO);
		//case "existBoardId" -> BoardController.existBoardId(request, BoardVO);
		//case "loginForm" -> BoardController.loginForm(request);
		//case "login" -> BoardController.login(request, BoardVO,response);
		//case "logout" -> BoardController.logout(request);
		//case "mypage" -> BoardController.mypage(request, BoardVO);
		default -> "";
		};

		
	}
}