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

public class Rooms implements AjaxCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("userId");
		
		RoomVO rvo = new RoomVO();
		rvo.setUserId(userId);
		
		DAO dao = new DAO();
		List<RoomVO> rvoList = dao.selectRooms(rvo);
		
		List<UserVO> uvoList = new ArrayList<>();
		for(int i = 0; i < rvoList.size(); i++) {
	         UserVO uvo = new UserVO();
	         if(rvoList.get(i).getUserId().equals(userId)) {
	            uvo.setUserId(rvoList.get(i).getRoomTitle());
	         } else {
	        	 uvo.setUserId(rvoList.get(i).getUserId());
	         }
	         uvoList.add(dao.selectUserInfo(uvo));
	      }
		
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
