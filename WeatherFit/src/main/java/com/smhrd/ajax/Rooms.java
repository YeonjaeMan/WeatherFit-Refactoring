package com.smhrd.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.RoomVO;
import com.smhrd.model.UserVO;

// 채팅방의 목록을 ajax로 응답해주는 클래스
public class Rooms implements AjaxCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 클라이언트로부터 세션에 담긴 userId를 전송받음.
		String userId = request.getParameter("userId");
		
		RoomVO rvo = new RoomVO();
		rvo.setUserId(userId);
		
		DAO dao = new DAO();
		
		// 해당 userId가 속해있는 채팅방 목록 리스트
		List<RoomVO> rvoList = dao.selectRooms(rvo);
		
		// 채팅방에는 생성 요청을 보낸 사람과 받은 사람으로 나뉘기 때문에
		// 사용자별로 상대방의 아이디가 보이도록 응답해주기 위한 리스트
		List<UserVO> uvoList = new ArrayList<>();
		
		for(int i = 0; i < rvoList.size(); i++) {
	         UserVO uvo = new UserVO();
	         
	         // 세션에서 가져온 userId와 채팅방 생성 요청을 보낸 사람이 같다면, 받는 사람 정보를 담아줌.
	         if(rvoList.get(i).getUserId().equals(userId))
	            uvo.setUserId(rvoList.get(i).getRoomTitle());
	         
	         // 다르다면, 보낸 사람 정보를 담아줌.
	         else
	        	 uvo.setUserId(rvoList.get(i).getUserId());
	         
	         uvoList.add(dao.selectUserInfo(uvo));
	      }
		
		// RoomVO, UserVO 다른 두 객체를 담기 위해 Map 자료구조 사용
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("room", rvoList);
		map.put("user", uvoList);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
		
	}

}
