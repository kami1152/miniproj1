package com.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
		List<String> hobbys = new ArrayList<String>();
		try {
			String nsql = "SELECT u.user_id, u.username, u.email, u.password, u.created_at, u.age, GROUP_CONCAT(uh.hobby) AS hobbies FROM `user` u LEFT JOIN userhobby uh ON u.user_id = uh.user_id where u.user_id = ? GROUP BY u.user_id";
			// String sql = "select user_id,username,email,password,created_at,age from user
			// where user_id = ?";
			Pstmt = conn.prepareStatement(nsql);
			Pstmt.setString(1, user.getUserid());
			rs = Pstmt.executeQuery();
			if (rs.next()) {
				u.setUserid(rs.getString("user_id"));
				u.setUsername(rs.getString("username"));
				u.setUseremail(rs.getString("email"));
				u.setUserpassword(rs.getString("password"));
				u.setUserhobby(rs.getString("hobbies"));
				u.setUserdate(rs.getDate("created_at"));
				u.setUserage(rs.getInt("age"));
			}
			rs.close();
			System.out.println(u);
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
			String sql = "update user set username=?, password=?, email=?, age=? where user_id = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, user.getUsername());
			Pstmt.setString(2, user.getUserpassword());
			Pstmt.setString(3, user.getUseremail());
			Pstmt.setInt(4, user.getUserage());
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
			String sql = "insert into user (user_id, username, email, password, age ) values(?,?,?,?,?)";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, user.getUserid());
			Pstmt.setString(2, user.getUsername());
			Pstmt.setString(3, user.getUseremail());
			Pstmt.setString(4, user.getUserpassword());
			Pstmt.setInt(5, user.getUserage());
			res = Pstmt.executeUpdate();
			Queue<String> hobbies = user.getUserhobbies();
			String sql2 = "insert into userhobby (user_id, hobby) values (?,?)";
			while(!hobbies.isEmpty()) {
				String hobby = hobbies.poll();
				Pstmt = conn.prepareStatement(sql2);
				Pstmt.setString(1, user.getUserid());
				Pstmt.setString(2, hobby);
				Pstmt.executeUpdate();
			}
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
			String sql = "SELECT user_id, username, email, password, created_at, age, uuid FROM user WHERE user_id =? AND password = ?";
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
		UserVO u =null;
		try {
			String sql = "SELECT u.user_id, u.username, u.email, u.password, u.created_at, u.age, GROUP_CONCAT(uh.hobby) AS hobbies FROM `user` u LEFT JOIN userhobby uh ON u.user_id = uh.user_id where u.uuid = ? GROUP BY u.user_id";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, user.getUseruuid());

			ResultSet rs = Pstmt.executeQuery();
			if (rs.next()) {
				u.setUserid(rs.getString("user_id"));
				u.setUsername(rs.getString("username"));
				u.setUseremail(rs.getString("email"));
				u.setUserpassword(rs.getString("password"));
				u.setUserdate(rs.getDate("created_at"));
				u.setUserage(rs.getInt("age"));
				u.setUserhobby(rs.getString("hobbies"));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public void updateUUID(UserVO userVO) {
		int updated = 0;
		try {
			String sql = "update user set uuid=? where user_id = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, userVO.getUseruuid());
			Pstmt.setString(2, userVO.getUserid());
			Pstmt.executeUpdate();
			System.out.println("ok uuid updated");
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<String> hobbyList() {
		List<String> list = new ArrayList<String>();
		try {
			String sql = "Select hobbyname from tbhobby";
			Pstmt = conn.prepareStatement(sql);
			rs = Pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("hobbyname"));
			}
			rs.close();
		}catch(SQLException e) {
			
		}
		return list;
	}

}