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

public class Images implements AjaxCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		String userId = request.getParameter("userId");
		
		UserVO uvo = new UserVO();
		uvo.setUserId(userId);

		FileVO fvo = new FileVO();
		fvo.setPostIdx(postIdx);

		DAO dao = new DAO();
		UserVO resultUserVO = dao.selectUserInfo(uvo);
		FileVO resultFileVO = dao.selectFile(fvo);
		
		Map<String, Object> result = new HashMap<>();
		result.put("file", resultFileVO);
		result.put("user", resultUserVO);

		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(json);
		
		
	}

}
