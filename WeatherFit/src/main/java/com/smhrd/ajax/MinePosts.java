package com.smhrd.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;
import com.smhrd.model.UserVO;

public class MinePosts implements AjaxCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String userId = request.getParameter("userId");
		
		UserVO uvo = new UserVO();
		uvo.setUserId(userId);
		
		DAO dao = new DAO();
		List<PostVO> minePosts = dao.selectMinePosts(uvo);
		
		Gson gson = new Gson();
		String json = gson.toJson(minePosts);
		
		
		
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(json);
	}
	

}
