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
import com.smhrd.model.ChatVO;

@WebServlet("/Chats")
public class Chats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int roomIdx = Integer.parseInt(request.getParameter("roomIdx"));
		
		ChatVO cvo = new ChatVO();
		cvo.setRoomIdx(roomIdx);
		
		DAO dao = new DAO();
		List<ChatVO> cvoList = dao.selectChats(cvo);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(cvoList);
		out.print(json);
	}

}