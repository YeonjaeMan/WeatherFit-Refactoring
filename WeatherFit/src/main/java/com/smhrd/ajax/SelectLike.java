package com.smhrd.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.LikeVO;

public class SelectLike implements AjaxCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		String userId = request.getParameter("userId");
		
		LikeVO lvo = new LikeVO();
		lvo.setPostIdx(postIdx);
		lvo.setUserId(userId);
		
		DAO dao = new DAO();
		LikeVO resultVO = dao.selectLike(lvo);
		
		Gson gson = new Gson();
		String json = gson.toJson(resultVO);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		response.getWriter().write(json);
		
	}
	
}
