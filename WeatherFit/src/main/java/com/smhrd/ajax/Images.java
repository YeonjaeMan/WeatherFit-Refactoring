package com.smhrd.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.FileVO;
import com.smhrd.model.UserVO;
// 이미지의 경로를 구하는 class
public class Images implements AjaxCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// postIdx, userId를 요청데이터에서 받는다.
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		String userId = request.getParameter("userId");
		
		// 각각의 vo에 set한다.
		UserVO uvo = new UserVO();
		uvo.setUserId(userId);
		FileVO fvo = new FileVO();
		fvo.setPostIdx(postIdx);

		DAO dao = new DAO();
		// dao에 선언된 event로 선언한다.
		UserVO resultUserVO = dao.selectUserInfo(uvo);
		FileVO resultFileVO = dao.selectFile(fvo);
		
		Map<String, Object> result = new HashMap<>();
		// file과 user로 매핑한다.
		result.put("file", resultFileVO);
		result.put("user", resultUserVO);

		// 응답
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(json);
		
		
	}

}
