package com.smhrd.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.CommentVO;

public class Comments implements AjaxCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
//		System.out.println(postIdx);
//		System.out.println("비동기댓글들어오냐?");

		DAO dao = new DAO();
		List<CommentVO> commentlist = dao.Commentselect(postIdx);

		Gson gson = new Gson();
		String json = gson.toJson(commentlist);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		response.getWriter().write(json);
	}

}
