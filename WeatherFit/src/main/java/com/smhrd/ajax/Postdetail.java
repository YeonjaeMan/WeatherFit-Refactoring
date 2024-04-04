package com.smhrd.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;
// 게시물의 상세 내용데이터를 처리하는 클래스
public class Postdetail implements AjaxCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// postIdx를 요청데이터로 받는다.
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		
		PostVO pvo = new PostVO();
		pvo.setPostIdx(postIdx);
		// pvo는 postIdx를 받는다
		DAO dao = new DAO();
		// Postdetail에 pvo를 넘긴다. 반환은 posts로 받는다
		List<PostVO> posts = dao.Postdetail(pvo);
		
		// 응답한다.
		Gson gson = new Gson();
		String json = gson.toJson(posts);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
		
		
	}

}
