package com.smhrd.frontController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;

@WebServlet("/Posts")
public class Posts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청이 들어옴.");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		DAO dao = new DAO();
		List<Map<String, Object>> posts = dao.Postselect();
		
		
		Gson gson = new Gson();
		String json = gson.toJson(posts);
		
		out.print(json);
		
		
		
		
		
		
		
		
//		for(int i = 0; i < posts.size(); i++) {
//			String json = gson.toJson(posts.get(i));
//			System.out.println(json);
//			out.print(json);
//		}
	}

}
