package com.smhrd.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;

public class Searchs implements AjaxCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		String keyWord = request.getParameter("keyWord");
		
		DAO dao = new DAO();
		List<Map<String, Object>> search = dao.Search(keyWord);
		// 입력될때마다 keyWord가 새로 받아짐
		PrintWriter out = response.getWriter();
		
		
		Gson gson = new Gson();
		String json = gson.toJson(search);
		
		
		
		// 응답의 컨텐츠 타입을 "application/json"으로 설정하고, 문자 인코딩은 "UTF-8"로 설정합니다.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 변환된 JSON 문자열을 응답 바디에 작성하여 클라이언트에게 전송합니다.
        response.getWriter().write(json);
	
		
		
	}

}
