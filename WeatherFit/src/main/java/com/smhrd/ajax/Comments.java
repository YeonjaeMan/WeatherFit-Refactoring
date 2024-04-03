package com.smhrd.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.CommentVO;
// 모든 댓글을 가져오되 해당하는 postIdx에 있는 댓글만 가져온다.
public class Comments implements AjaxCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 처리할 데이터의 타입과 인코딩 타입을 선언
		response.setContentType("text/html;charset=UTF-8");
		// postIdx를 정수로바꾸어 받는다.
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		
		CommentVO cvo = new CommentVO();
		// cvo는 postIdx를 받는다.
		cvo.setPostIdx(postIdx);
		DAO dao = new DAO();
		// cvo는 dao의 Commentselect의 매개변수로 들어간다. 반환은 commentlist에서 받는다
		List<CommentVO> commentlist = dao.Commentselect(cvo);

		Gson gson = new Gson();
		// json을 String으로 변환한다.
		String json = gson.toJson(commentlist);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		// 응답한다
		response.getWriter().write(json);
	}

}
