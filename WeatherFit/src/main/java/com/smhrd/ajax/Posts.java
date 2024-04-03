package com.smhrd.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.CrawlingVO;
import com.smhrd.model.PostVO;
// 조건에 맞는 게시물을 색인하는 클래스
public class Posts implements AjaxCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// postTemp를 요청데이터로 받는다
		int postTemp = Integer.parseInt(request.getParameter("temp"));
		
		PostVO pvo = new PostVO();
		pvo.setPostTemp(postTemp);
		
		CrawlingVO cvo = new CrawlingVO();
		cvo.setSeason("봄");
		
		DAO dao = new DAO();
		List<PostVO> posts = dao.selectPosts(pvo);
		List<CrawlingVO> craws = dao.selectCrawling(cvo);
		// 요청데이터를 조건으로 조건에 맞는 게시물들을 가져온다.
		Map<String, Object> result = new HashMap<>();
		result.put("posts", posts);
		result.put("craws", craws);

		Gson gson = new Gson();
		String json = gson.toJson(result);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
