package com.smhrd.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;
// 모든 게시물들을 가져오는 클래스
public class RecentPosts implements AjaxCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO dao = new DAO();
		List<PostVO> recentPosts = dao.selectRecentPosts();
		// selectRecentPosts는 post의 모든 데이터를 가져온다.
		Gson gson = new Gson();
		String json = gson.toJson(recentPosts);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 변환된 JSON 문자열을 응답 바디에 작성하여 클라이언트에게 전송합니다.
		response.getWriter().write(json);
	}

}
