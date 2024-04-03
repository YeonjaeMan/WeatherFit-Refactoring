package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

// 회원정보를 수정할 때 유효성 검사를 해주는 컨트롤러
public class Valid implements Command {

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

		if (userNick.equals("")) {

		}

		DAO dao = new DAO();

		dao.update(vo);

		request.getSession().setAttribute("member", vo);

		return "redirect:/goupdate.do";
	}

}