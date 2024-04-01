package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

public class UpdateUserInfo implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	
		UserVO sessionVO = (UserVO)request.getSession().getAttribute("member");
		String userId = sessionVO.getUserId();
		String password = request.getParameter("new-password");
		String nick = request.getParameter("new-nickname");
		String region = request.getParameter("new-region");
		double height = Double.parseDouble(request.getParameter("new-height"));
		double weight = Double.parseDouble(request.getParameter("new-weight"));
		
		UserVO uvo = new UserVO();
		uvo.setUserId(userId);
		uvo.setUserPw(password);
		uvo.setUserNick(nick);
		uvo.setUserRegion(region);
		uvo.setUserHeight(height);
		uvo.setUserWeight(weight);
		
		DAO dao = new DAO();
		dao.updateUserInfo(uvo);
		
		return "redirect:/gomain.do";
	}
	
}
