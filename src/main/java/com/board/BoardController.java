package com.board;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.user.UserVO;

public class BoardController {

	BoardService boardService = new BoardService();
	
	public Object list(HttpServletRequest request, BoardVO boardVO) {
		System.out.println("Board list loading");
		List<BoardVO> list = boardService.list(boardVO);
		HttpSession session = request.getSession();
		request.setAttribute("list", list);
		return "list";
	}

	public Object view(HttpServletRequest request, BoardVO board) {
		HttpSession session = request.getSession();
		UserVO loginVO = (UserVO) session.getAttribute("loginVO");
		BoardVO boardVO = boardService.view(board, loginVO);
		request.setAttribute("board", boardVO);
		return "view";
	}

	public Object delete(HttpServletRequest request, BoardVO board) {
		System.out.println("delete start");
		int res = boardService.delete(board);

		Map<String, Object> map = new HashMap<String, Object>();
		if (res == 1) {
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "failed delete");
		}

		return map;
	}

	public Object updateForm(HttpServletRequest request, BoardVO boardVO) {

		request.setAttribute("board", boardService.read(boardVO) );
		return "updateForm";
	}

	public Object update(HttpServletRequest request, BoardVO board) {
		int res = boardService.update(board);
		Map<String, Object> map = new HashMap<String, Object>();
		if (res == 1) {
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "failed delete");
		}

		return map;
	}

	public Object insertForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO loginVO = (UserVO) session.getAttribute("loginVO");
		BoardVO board =null;
		if(loginVO.getUserid() == null) {
			List<BoardVO> list = boardService.list(board);
			request.setAttribute("list", list);
			request.setAttribute("msg", "로그인이 필요한 서비스입니다");
			return "list";
		}
		return "insertForm";
	}

	public Object insert(HttpServletRequest request, BoardVO board) {
		System.out.println("insert start");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		HttpSession session = request.getSession();
		UserVO loginVO = (UserVO) session.getAttribute("loginVO");
		System.out.println("loginVO : " + loginVO);
		if (loginVO.getUsername() != null) {
			board.setBwriter(loginVO.getUsername());
		} else {
			map.put("status", -99);
			map.put("statusMessage", "로그인이 필요한 서비스입니다.");
			return map;
		}
		
		int res = boardService.insert(board);
		if (res == 1) {
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "failed delete");
		}

		return map;
	}

}
