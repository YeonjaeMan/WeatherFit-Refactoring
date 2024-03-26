package com.smhrd.frontController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.LikeVO;

@WebServlet("/PostLikeCheck")
public class PostLikeCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("userId");
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		
		LikeVO lvo = new LikeVO();
		lvo.setUserId(userId);
		lvo.setPostIdx(postIdx);
		
		DAO dao = new DAO();
		LikeVO resultVO = dao.selectLike(lvo);
		List<LikeVO> resultList = dao.countLike(lvo);
		
		Map<String, LikeVO> map = new HashMap<String, LikeVO>();
		for(int i = 0; i < resultList.size(); i++) {
			map.put("count" + i, resultList.get(i));
		}
		map.put("select", resultVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		out.print(json);
		
	}

}
