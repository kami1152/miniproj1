package com.user;

import java.util.List;

public class UserService {

	UserDAO userDAO = new UserDAO();

	public List<UserVO> list() {
		return userDAO.list();
	}

	public UserVO view(UserVO user) {
		return userDAO.view(user);
	}

	public int delete(UserVO user) {

		return userDAO.delete(user);

	}

	public int update(UserVO user) {

		return userDAO.update(user);
	}

	public int insert(UserVO user) {
		if (!userDAO.idcheck(user)) {
			return -1;
		}
		return userDAO.insert(user);
	}

	public UserVO login(UserVO user) {
		UserVO u = userDAO.login(user);
		if(u == null) {
			return null;
		}
		return u;
	}

	public void updateUUID(UserVO userVO) {
	
		userDAO.updateUUID(userVO);
		
	}

	public List<String> insertForm() {
		
		return userDAO.hobbyList();
	}

	public boolean existUserId(UserVO userVO) {
		if(!userDAO.idcheck(userVO)) {
			return false;
		}
		return true;
	}

}
