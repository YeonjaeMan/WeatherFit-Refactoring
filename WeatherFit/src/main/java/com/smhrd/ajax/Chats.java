package com.smhrd.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.ChatVO;

// 모든 채팅데이터를 가져오되 roomIdx가 조건과 같은 채팅만 가져온다.
public class Chats implements AjaxCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 처리할 데이터의 타입과 인코딩 타입을 선언
		response.setContentType("text/html;charset=UTF-8");
		// 변수로 받은 roomIdx 
		int roomIdx = Integer.parseInt(request.getParameter("roomIdx"));
		
		ChatVO cvo = new ChatVO();
		// ChatVO 타입의 cvo는 roomIdx를 받는다.
		cvo.setRoomIdx(roomIdx);
		
		DAO dao = new DAO();
		// dao의 selectChats에 cvo를 전하고 cvoList에 반환한다.
		List<ChatVO> cvoList = dao.selectChats(cvo);
		
		Gson gson = new Gson();
		// json타입 k:v를  String으로 변환한다
		String json = gson.toJson(cvoList);
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // String을 출력하여 응답한다.
        response.getWriter().write(json);	
	}
}
