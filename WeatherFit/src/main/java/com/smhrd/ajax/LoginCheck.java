package com.smhrd.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

public class LoginCheck implements AjaxCommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		UserVO uvo = new UserVO();
		uvo.setUserId(userId);
		uvo.setUserPw(userPw);
		
		DAO dao = new DAO();
		UserVO resultVO = dao.login(uvo);
		
		Gson gson = new Gson();
		String json = gson.toJson(resultVO);
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
		
	}
	
}
