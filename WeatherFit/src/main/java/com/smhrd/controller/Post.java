package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;
import com.smhrd.model.UserVO;

public class Post implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)(session.getAttribute("member"));
		String userId = vo.getUserId(); 
		
		String postContent = request.getParameter("postContent");
		String hashTag = request.getParameter("hashTag");
		
		
		
		
		PostVO pvo = new PostVO();
		pvo.setUserId(userId);
		pvo.setPostContent(postContent);
		pvo.setHashTag(hashTag);
		
		
		DAO dao = new DAO();
		int row = dao.post(pvo);
		if(row > 0) {
			System.out.println("글작성 성공");
			return "redirect:/gomain.do";
		}else {
			System.out.println("글작성 실패");
			return "redirect:/gomain.do";
		}
		
	
	}
}
