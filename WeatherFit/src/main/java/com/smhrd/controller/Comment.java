package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.CommentVO;
import com.smhrd.model.UserVO;
// 댓글을 입력받아 처리하는 클래스
public class Comment implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// postIdx와 cmtContent를 요청데이터로 받는다.
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		String cmtContent = request.getParameter("cmtContent");
		// 세션정보중 userId를 받는다.
		HttpSession session = request.getSession();
		UserVO uvo = (UserVO)(session.getAttribute("member"));
		String userId = uvo.getUserId();
		
		System.out.println(userId);
		CommentVO cvo = new CommentVO();
		cvo.setPostIdx(postIdx);
		cvo.setCmtContent(cmtContent);
		cvo.setUserId(userId);
		
		DAO dao = new DAO();
		// comment에 cvo를 보내 저장한다.
		dao.comment(cvo);
		
		
		return null;
	}

}
