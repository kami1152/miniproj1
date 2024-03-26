package com.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.Secret;
import com.user.UserVO;

public class UserDAO implements Secret {

	private static Connection conn = null;
	private static PreparedStatement Pstmt = null;
	private static ResultSet rs = null;

	static {
		try {
			System.out.println("연결 1");
			Class.forName(classname);
			conn = DriverManager.getConnection(url, accountname, accountpassword);
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<UserVO> list() {

		List<UserVO> list = new ArrayList<>();
		try {

			String sql = "select user_id, username, age, email from user";
			Pstmt = conn.prepareStatement(sql);
			rs = Pstmt.executeQuery();
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setUserid(rs.getString("user_id"));
				user.setUsername(rs.getString("username"));
				user.setUserage(rs.getInt("age"));
				user.setUseremail(rs.getString("email"));
				list.add(user);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String arg[]) {
		UserDAO d = new UserDAO();
	}

	public UserVO view(UserVO user) {
		UserVO u = new UserVO();
		try {
			String sql = "select user_id,username,email,password,created_at,age,hobby from user where user_id = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, user.getUserid());
			rs = Pstmt.executeQuery();
			if (rs.next()) {
				u.setUserid(rs.getString("user_id"));
				u.setUsername(rs.getString("username"));
				u.setUseremail(rs.getString("email"));
				u.setUserpassword(rs.getString("password"));
				u.setUserdate(rs.getDate("created_at"));
				u.setUserage(rs.getInt("age"));
				u.setUserhobby(rs.getString("hobby"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public int delete(UserVO user) {
		int res = 0;
		try {
			String sql = "delete from user where user_id=?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, user.getUserid());
			res = Pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;

	}

	public int update(UserVO user) {
		int res = 0;
		try {
			String sql = "update user set username=?, password=?, email=?, age=?, hobby=? where user_id = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, user.getUsername());
			Pstmt.setString(2, user.getUserpassword());
			Pstmt.setString(3, user.getUseremail());
			Pstmt.setInt(4, user.getUserage());
			Pstmt.setString(5, user.getUserhobby());
			Pstmt.setString(6, user.getUserid());
			res = Pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public int insert(UserVO user) {
		int res = 0;
		try {
			String sql = "insert into user (user_id, username, email, password, age, hobby) values(?,?,?,?,?,?)";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, user.getUserid());
			Pstmt.setString(2, user.getUsername());
			Pstmt.setString(3, user.getUseremail());
			Pstmt.setString(4, user.getUserpassword());
			Pstmt.setInt(5, user.getUserage());
			Pstmt.setString(6, user.getUserhobby());
			res = Pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public boolean idcheck(UserVO user) {
		try {
			String sql = "select * from user where user_id = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, user.getUserid());
			rs = Pstmt.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}

	public UserVO login(UserVO user) {
		UserVO u = new UserVO();
		try {
			String sql = "SELECT user_id, username, email, password, created_at, age, hobby, uuid FROM user WHERE user_id =? AND password = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, user.getUserid());
			Pstmt.setString(2, user.getUserpassword());
			rs = Pstmt.executeQuery();

			if (rs.next()) {
				u.setUserid(rs.getString("user_id"));
				u.setUsername(rs.getString("username"));
				u.setUseremail(rs.getString("email"));
				u.setUserpassword(rs.getString("password"));
				u.setUserdate(rs.getDate("created_at"));
				u.setUserage(rs.getInt("age"));
				u.setUserhobby(rs.getString("hobby"));
				u.setUseruuid(rs.getString("uuid"));
			} else {
				return null;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public UserVO getUserVOFromUUID(UserVO user) {

		UserVO u = new UserVO();

		try {
			String sql = "select user_id,username,email,password,created_at,age,hobby from user where uuid = ?";
			Pstmt.setString(1, user.getUseruuid());

			ResultSet rs = Pstmt.executeQuery();
			if (rs.next()) {
				u.setUserid(rs.getString("user_id"));
				u.setUsername(rs.getString("username"));
				u.setUseremail(rs.getString("email"));
				u.setUserpassword(rs.getString("password"));
				u.setUserdate(rs.getDate("created_at"));
				u.setUserage(rs.getInt("age"));
				u.setUserhobby(rs.getString("hobby"));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

}