package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.FileVO;

public class Comment implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		int cmtLikes = Integer.parseInt(request.getParameter("cmtLikes"));
		String cmtContent = request.getParameter("cmtContent");
		
		
		
		DAO dao = new DAO();
		//dao.Comment();
		
		
		return null;
	}

}
