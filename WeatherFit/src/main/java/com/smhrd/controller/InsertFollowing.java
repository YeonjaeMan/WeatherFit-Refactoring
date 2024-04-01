package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.FollowingVO;

public class InsertFollowing implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InterruptedException {
		
		String follower = request.getParameter("follower");
		String followee = request.getParameter("followee");
		
		FollowingVO flvo = new FollowingVO();
		flvo.setFollower(follower);
		flvo.setFollowee(followee);
		
		DAO dao = new DAO();
		int row = dao.insertFollowing(flvo);
		
		if(row > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("followingCheck", flvo);
		}
		
		return "profile";
		
	}
	
}
