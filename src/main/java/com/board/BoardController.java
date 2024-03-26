package com.board;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class BoardController {

	BoardService boardService = new BoardService();

	public Object list(HttpServletRequest request, BoardVO boardVO) {
		System.out.println("Board list loading");
		List<BoardVO> list = boardService.list();
		request.setAttribute("list", list);
		return "list";
	}

	public Object view(HttpServletRequest request, BoardVO board) {
		request.setAttribute("board", boardService.view(board));
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

		request.setAttribute("board", boardService.view(boardVO));
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
		// TODO Auto-generated method stub
		return "insertForm";
	}

	public Object insert(HttpServletRequest request, BoardVO boardVO) {
		System.out.println("insert start");
		int res = boardService.insert(boardVO);
		Map<String, Object> map = new HashMap<String, Object>();
		if (res == 1) {
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "failed delete");
		}

		return map;
	}

}
