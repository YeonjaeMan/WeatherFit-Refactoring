package com.smhrd.frontController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.FileVO;

@WebServlet("/Images")
public class Images extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		
		FileVO fvo = new FileVO();
		fvo.setPostIdx(postIdx);
		
		DAO dao = new DAO();
		FileVO resultVO = dao.selectFile(fvo);
		
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(resultVO);
		out.print(json);
	}

}
