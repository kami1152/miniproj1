package com.board;

import java.util.List;

import com.user.UserVO;

public class BoardService {

	BoardDAO boardDAO = new BoardDAO();
	public List<BoardVO> list() {
		return boardDAO.list();
	}
	public BoardVO view(BoardVO board, UserVO user) {
		BoardVO b = boardDAO.view(board.getBno(), user);
		System.out.println(b);
		System.out.println(user);
		return b;
	}
	
	public BoardVO read(BoardVO board) {
		BoardVO b = boardDAO.read(board.getBno());
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
