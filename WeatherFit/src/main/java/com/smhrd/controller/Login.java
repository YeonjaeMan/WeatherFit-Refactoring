package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

public class Login implements Command {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		

		String userId = request.getParameter("email_id"); // email
		String userPw = request.getParameter("email_pw");	// 비번

		UserVO vo = new UserVO();
		vo.setUserId(userId);
		vo.setUserPw(userPw);
		
		DAO dao = new DAO();
		
		UserVO resultVO = dao.login(vo);

		if(resultVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", resultVO);	// <<<<
		}
		// 로그인 완료 후 메인페이지
    
		return "redirect:/gomain.do";
//		return "redirect:/";
		
	}	
}
