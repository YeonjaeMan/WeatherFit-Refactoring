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


@WebServlet("/Searchs")
public class Searchs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("검색요청이 들어옴.");
		response.setContentType("text/html;charset=UTF-8");
		
		String keyWord = request.getParameter("keyWord");
		
		DAO dao = new DAO();
		System.out.println(keyWord);
		List<Map<String, Object>> search = dao.Search(keyWord);
		PrintWriter out = response.getWriter();
		
		
		Gson gson = new Gson();
		String json = gson.toJson(search);
		
			
		out.print(json);
		
	}

}
