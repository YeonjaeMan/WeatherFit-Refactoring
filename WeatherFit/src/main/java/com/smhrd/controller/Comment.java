package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.CommentVO;
import com.smhrd.model.UserVO;

public class Comment implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("댓글입력들어옴?");
		
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		String cmtContent = request.getParameter("cmtContent");
		
		HttpSession session = request.getSession();
		UserVO uvo = (UserVO)(session.getAttribute("member"));
		String userId = uvo.getUserId();
		
		System.out.println(userId);
		CommentVO cvo = new CommentVO();
		cvo.setPostIdx(postIdx);
		cvo.setCmtContent(cmtContent);
		cvo.setUserId(userId);
		
		DAO dao = new DAO();
		dao.comment(cvo);
		
		
		return null;
	}

}
