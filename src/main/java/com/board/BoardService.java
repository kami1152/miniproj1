package com.board;

import java.util.List;

public class BoardService {

	BoardDAO boardDAO = new BoardDAO();
	public List<BoardVO> list() {
		return boardDAO.list();
	}
	public BoardVO view(BoardVO board) {
		BoardVO b = boardDAO.view(board.getBno());
		System.out.println(b);
		return b;
	}
	public int delete(BoardVO board) {
	
		return boardDAO.delete(board.getBno());
	}
	public int update(BoardVO board) {
		System.out.println(board);
		return boardDAO.update(board);
	}
	public int insert(BoardVO board) {
		return boardDAO.insert(board);
	}

}
