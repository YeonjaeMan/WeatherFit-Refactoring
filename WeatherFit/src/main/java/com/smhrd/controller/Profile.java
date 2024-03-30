package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

public class Profile implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InterruptedException {
		
		String userId = request.getParameter("userId");
		
		UserVO uvo = new UserVO();
		uvo.setUserId(userId);
		
		DAO dao = new DAO();
		UserVO resultVO = dao.selectUserInfo(uvo);
		
		HttpSession session = request.getSession();
		session.setAttribute("userProfileInfo", resultVO);
		
		return "profile";
	}
	
}
