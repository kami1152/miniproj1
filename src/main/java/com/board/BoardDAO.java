package com.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Secret;
import com.user.UserVO;

public class BoardDAO implements Secret {

	private static Connection conn = null;
	private static PreparedStatement Pstmt = null;
	private static ResultSet rs = null;
	static {
		try {
			System.out.println("connect board db");
			Class.forName(classname);
			conn = DriverManager.getConnection(url, accountname, accountpassword);
			System.out.println("connect board db ok");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<BoardVO> list() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			String sql = "select bno, btitle,bwriter,bdate,views,recommend_count from board";
			Pstmt = conn.prepareStatement(sql);
			rs = Pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setViews(rs.getInt("views"));
				board.setRecommend(rs.getInt("recommend_count"));
				list.add(board);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public BoardVO read(int bno) {
		BoardVO board = new BoardVO();
		try {
			String sql = "select bno, bcontent, btitle, bwriter,bdate,views,recommend_count from board where bno = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setInt(1, bno);
			rs = Pstmt.executeQuery();

			if (rs.next()) {
				board.setBno(rs.getInt("bno"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setViews(rs.getInt("views"));
				board.setRecommend(rs.getInt("recommend_count"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	public BoardVO view(int bno, UserVO user) {
		BoardVO board = new BoardVO();
		try {
			String sql = "select bno, bcontent, btitle, bwriter,bdate,views,recommend_count from board where bno = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setInt(1, bno);
			rs = Pstmt.executeQuery();

			if (rs.next()) {
				board.setBno(rs.getInt("bno"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setViews(rs.getInt("views"));
				board.setRecommend(rs.getInt("recommend_count"));
			} else {
				return null;
			}
			rs.close();
			
			if (user != null) {
				boolean viewer = false;
				System.out.println("어서와");
				String sql3 = "select bno from boardlog where user_id =? ";
				Pstmt = conn.prepareStatement(sql3);
				Pstmt.setString(1, user.getUserid());
				rs = Pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						if (rs.getInt("bno") == bno) {
							System.out.println("있내");
							viewer = true;
						}
					}
				}

				if (!viewer) {
					sql = "insert into boardlog (bno, user_id) values(?,?)";
					Pstmt = conn.prepareStatement(sql);
					Pstmt.setInt(1, bno);
					Pstmt.setString(2, user.getUserid());
					Pstmt.executeUpdate();

					sql = "update board set views = ? where bno = ?";
					Pstmt = conn.prepareStatement(sql);
					Pstmt.setInt(1, board.getViews()+1);
					Pstmt.setInt(2, bno);
					Pstmt.executeUpdate();
					board.setViews(board.getViews() + 1);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	public int delete(int bno) {
		int res = 0;
		try {
			String sql = "delete from board where bno=?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setInt(1, bno);
			res = Pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int update(BoardVO board) {
		int res = 0;
		try {
			String sql = "update board set btitle=?, bcontent=? where bno = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, board.getBtitle());
			Pstmt.setString(2, board.getBcontent());
			Pstmt.setInt(3, board.getBno());
			res = Pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int insert(BoardVO board) {
		int res = 0;
		try {
			String sql = "insert into board(btitle, bcontent, bwriter) values (?,?,?)";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, board.getBtitle());
			Pstmt.setString(2, board.getBcontent());
			Pstmt.setString(3, board.getBwriter());
			res = Pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
