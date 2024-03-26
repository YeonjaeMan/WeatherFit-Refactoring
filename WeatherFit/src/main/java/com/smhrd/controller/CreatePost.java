package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;

public class CreatePost implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userId = request.getParameter("userId");
		String postImg = request.getParameter("postImg");
		String postContent = request.getParameter("postContent");
		String postOption = request.getParameter("postOption");
		String hashTags = request.getParameter("hashTags");
		
		PostVO pvo = new PostVO();
		pvo.setUserId(userId);
		pvo.setPostContent(postContent);
		pvo.setHashTag(hashTags);
		
		//FileVO fvo = new FileVO();
		// 이미지 이름, 이미지 타입, 이미지 데이터.
		//fvo.set
		
		DAO dao = new DAO();
		
		System.out.println(userId);
		System.out.println(postImg);
		System.out.println(postContent);
		System.out.println(postOption);
		System.out.println(hashTags);
		
		return "redirect:/gomain.do";
	}
	
}
