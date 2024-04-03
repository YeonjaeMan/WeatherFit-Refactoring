package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

// 회원의 정보를 수정하여 DB에 저장해주는 컨트롤러
public class Update implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String userPw = request.getParameter("userPw");
		String userNick = request.getParameter("userNick");
		String userRegion = request.getParameter("userRegion");
		double userWeight = Double.parseDouble(request.getParameter("userWeight"));
		double userHeight = Double.parseDouble(request.getParameter("userHeight"));
		String userProfileImg = request.getParameter("userProfileImg");
		String userProfileInfo = request.getParameter("userProfileInfo");

		UserVO sessionVo = (UserVO) (request.getSession().getAttribute("member"));
		String userId = sessionVo.getUserId();

		UserVO vo = new UserVO();
		vo.setUserId(userId);
		vo.setUserPw(userPw);
		vo.setUserNick(userNick);
		vo.setUserWeight(userWeight);
		vo.setUserHeight(userHeight);
		vo.setUserRegion(userRegion);
		vo.setUserProfileImg(userProfileImg);
		vo.setUserProfileInfo(userProfileInfo);

		DAO dao = new DAO();

		dao.update(vo);

		request.getSession().setAttribute("member", vo);

		return "redirect:/gomain.do";

	}

}