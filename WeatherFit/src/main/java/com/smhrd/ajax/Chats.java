package com.smhrd.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.ChatVO;

public class Chats implements AjaxCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		
		int roomIdx = Integer.parseInt(request.getParameter("roomIdx"));
		
		ChatVO cvo = new ChatVO();
		cvo.setRoomIdx(roomIdx);
		
		DAO dao = new DAO();
		List<ChatVO> cvoList = dao.selectChats(cvo);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(cvoList);
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(json);
		
		
	}

}
