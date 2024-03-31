package com.smhrd.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.LikeVO;

public class DeleteLike implements AjaxCommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("좋아요 삭제 요청이 들어옴");
		
		String userId = request.getParameter("userId");
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));

		LikeVO lvo = new LikeVO();
		lvo.setUserId(userId);
		lvo.setPostIdx(postIdx);

		DAO dao = new DAO();
		int row = dao.deleteLike(lvo);

		Gson gson = new Gson();
		String json = gson.toJson(row);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		response.getWriter().write(json);

	}

}
