package com.smhrd.frontController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;
import com.smhrd.model.UserVO;

@WebServlet("/MinePosts")
public class MinePosts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("userId");
		
		UserVO uvo = new UserVO();
		uvo.setUserId(userId);
		
		DAO dao = new DAO();
		List<PostVO> minePosts = dao.selectMinePosts(uvo);
		
		Gson gson = new Gson();
		String json = gson.toJson(minePosts);
		out.print(json);
	}

}
