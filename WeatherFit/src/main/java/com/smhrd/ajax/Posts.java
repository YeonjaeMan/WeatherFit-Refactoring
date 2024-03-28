package com.smhrd.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;

public class Posts implements AjaxCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAO dao = new DAO();
		List<PostVO> posts = dao.Postselect();
		
		
		Gson gson = new Gson();
		String json = gson.toJson(posts);
		
		// 응답의 컨텐츠 타입을 "application/json"으로 설정하고, 문자 인코딩은 "UTF-8"로 설정합니다.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 변환된 JSON 문자열을 응답 바디에 작성하여 클라이언트에게 전송합니다.
        response.getWriter().write(json);
	}

}
