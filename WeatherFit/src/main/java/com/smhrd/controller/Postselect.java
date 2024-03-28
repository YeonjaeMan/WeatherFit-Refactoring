package com.smhrd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;

public class Postselect implements Command{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		DAO dao = new DAO();
		List<PostVO> list = dao.Postselect();
		request.setAttribute("list", list);
		return null;
		
		
	}
	
}
