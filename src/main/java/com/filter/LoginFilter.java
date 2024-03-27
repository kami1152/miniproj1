package com.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.*;
import com.user.UserDAO;
import com.user.UserVO;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("*.do")
public class LoginFilter extends HttpFilter implements Filter {

	UserDAO usersDAO = new UserDAO();
	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public LoginFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 사용자가 요청한 URL 얻기
		System.out.println("filter operation");
		
		if (request instanceof HttpServletRequest req) {
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			UserVO loginVO = (UserVO) session.getAttribute("loginVO");
			String contentType = request.getContentType();
			String url = req.getRequestURI();
			String action = req.getParameter("action");
			System.out.println("actions : " + action);
			Set<String> actionSet = new HashSet<String>();

			actionSet.add("loginForm");
			actionSet.add("login");
			actionSet.add("insertForm");
			actionSet.add("insert");

			System.out.println("url = " + url);
			if (!((url.equals("/miniproject1/board.do") || url.equals("/miniproject1/user.do"))&& actionSet.contains(action))) {
				if (loginVO == null) {
					System.out.println("99");
					Cookie[] cookies = req.getCookies();
					if (cookies != null) {
						System.out.println("95");
						for (Cookie cookie : cookies) {
							if (cookie.getName().equals("uuidCookie")) {
								// 2. uuid값을 이용하여 로그인 정보를 얻는다
								UserVO userVO = new UserVO();
								userVO.setUseruuid(cookie.getValue());
								loginVO = usersDAO.getUserVOFromUUID(userVO);
								// 3. 로그인 정보를 세션에 기록한다
								if (loginVO != null) {
									session.setAttribute("loginVO", loginVO);
									chain.doFilter(request, response);
								} else {
									resp.sendRedirect(req.getContextPath() + "/user.do?action=loginForm");
								}
								return;
							}
						}
					}
					resp.sendRedirect(req.getContextPath() + "/user.do?action=loginForm");
					
					return;
				}
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("loginFilter.init()...");
	}

}