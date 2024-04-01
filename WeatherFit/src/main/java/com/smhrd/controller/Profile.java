package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.FollowingVO;
import com.smhrd.model.UserVO;

public class Profile implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InterruptedException {
		
		HttpSession session = request.getSession();
		UserVO sessionUVO = (UserVO)session.getAttribute("member");
		String follower = sessionUVO.getUserId();
		String followee = request.getParameter("userId");
		
		UserVO uvo = new UserVO();
		uvo.setUserId(followee);
		
		FollowingVO flvo = new FollowingVO();
		flvo.setFollower(follower);
		flvo.setFollowee(followee);
		
		DAO dao = new DAO();
		UserVO resultUVO = dao.selectUserInfo(uvo);
		FollowingVO resultFLVO = dao.selectFollow(flvo);
		
		session.setAttribute("userProfileInfo", resultUVO);
		session.setAttribute("followingCheck", resultFLVO);
		
		return "profile";
	}
	
}
