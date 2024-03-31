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

public class Posts implements AjaxCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postTemp = Integer.parseInt(request.getParameter("temp"));
		
		PostVO pvo = new PostVO();
		pvo.setPostTemp(postTemp);
		
		CrawlingVO cvo = new CrawlingVO();
		cvo.setSeason("봄");
		
		DAO dao = new DAO();
		List<PostVO> posts = dao.selectPosts(pvo);
		List<CrawlingVO> craws = dao.selectCrawling(cvo);
		
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
