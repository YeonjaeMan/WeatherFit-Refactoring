package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

public class Join implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		String userNick = request.getParameter("userNick");
		String userGender = request.getParameter("userGender");
		String userRegion = request.getParameter("userRegion");
		double userWeight = Double.parseDouble(request.getParameter("userWeight"));
		double userHeight = Double.parseDouble(request.getParameter("userHeight"));

		UserVO vo = new UserVO();
		vo.setUserId(userId);
		vo.setUserPw(userPw);
		vo.setUserName(userName);
		vo.setUserNick(userNick);
		vo.setUserGender(userGender);
		vo.setUserWeight(userWeight);
		vo.setUserHeight(userHeight);
		vo.setUserRegion(userRegion);
		vo.setUserProfileImg("base_profile.png");
		vo.setUserProfileInfo("자기소개를 등록해주세요.");

		DAO dao = new DAO();
		int row = dao.join(vo);
		if (row > 0) {
			request.setAttribute("userId", userId);
		}

		return "redirect:/gomain.do";
	}

}
