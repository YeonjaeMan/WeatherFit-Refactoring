package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;

public class DeletePost implements Command {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));

		PostVO pvo = new PostVO();
		pvo.setPostIdx(postIdx);

		DAO dao = new DAO();

		dao.deletePost(pvo);
		
		return "redirect:/gomain.do";
	}
	
}
